package sk.euba.fhi.gui;

import ro.pippo.core.Pippo;
import ro.pippo.core.Session;
import sk.euba.fhi.data.DataFactory;
import sk.euba.fhi.model.interf.FirmaData;
import sk.euba.fhi.model.interf.VFData;
import sk.euba.fhi.model.obj.Prihlasenie;
import sk.euba.fhi.model.obj.VF;

import java.util.*;

public class VFPage {
    public static void init(Pippo pippo) {
        pippo.GET("/vf", routeContext -> {
            String strid = routeContext.getSession("pouzivatel_id");
            int pouzivatel_id = (strid != null) ? Integer.parseInt(strid) : 0;
            if (pouzivatel_id == 0) {
                routeContext.redirect("/login");
            }
            Prihlasenie prihlasenie = DataFactory.getPrihlasenie(pouzivatel_id);
            Integer year = routeContext.getParameter("selectedyear").toInt();
            if(0 != year) {
                prihlasenie.setRok(year);
            }
            String firma = routeContext.getParameter("selectedfirma").toString();
            if(null != firma) {
                prihlasenie.setFirma_nazov(firma);
                FirmaData firmaData = DataFactory.createFirmaData();
                int firma_id = firmaData.idFirmy(pouzivatel_id, firma);
                prihlasenie.setId_firma(firma_id);
            }

            Map<String, Object> model = new HashMap<>();
            model.put("selectedyear", prihlasenie.getRok());
            model.put("selectedfirma", prihlasenie.getFirma_nazov());

            FirmaData firmaData = DataFactory.createFirmaData();
            List<String> firmy = firmaData.nazvyFiriem(pouzivatel_id);
            model.put( "firmy", firmy);

            List<Integer> roky = new ArrayList<>(Arrays.asList(2018, 2019, 2020));
            model.put("roky", roky);

            VFData vfData = DataFactory.createVFData();
            List<VF> vfList = vfData.vsetky(prihlasenie.getId_firma(), prihlasenie.getRok());
            model.put("vfs", vfList);

            routeContext.render("vf", model);
        });

        pippo.GET("/vf_edit", routeContext -> {
            Map<String, Object> model = new HashMap<>();
            model.put("selectedid", 0);
            model.put("title", "Nová vyšlá faktúra");
            model.put("form_action", "/vf_new");

            model.put("cislo_faktury", "");
            model.put("odberatel", "");
            model.put("suma_bez_dph", "");
            model.put("datum", "");

            routeContext.render("vf_edit", model);
        });

        pippo.POST("/vf_new", routeContext -> {
            String action = routeContext.getParameter("action").toString();
            if (action == null) {
                routeContext.redirect("/vf");
                return;
            }
            Session session = routeContext.getRequest().getSession(false);
            int pouzivatel_id = Integer.parseInt(session.get("pouzivatel_id"));
            Prihlasenie prihlasenie = DataFactory.getPrihlasenie(pouzivatel_id);

            Integer cislo_faktury = routeContext.getParameter("cislo_faktury").toInt();
            String odberatel = routeContext.getParameter("odberatel").toString();
            String datum = routeContext.getParameter("datum").toString();
            Double sumabez = routeContext.getParameter("suma_bez_dph").toDouble();

            VF vf = new VF();
            vf.setId(0);
            vf.setId_firma(prihlasenie.getId_firma());
            vf.setCislo_faktury(cislo_faktury);
            vf.setRok(prihlasenie.getRok());
            vf.setOdberatel(odberatel);
            vf.setDatum(datum);
            vf.setDatum_dodania(datum);
            vf.setDatum_splatnosti(datum);
            vf.setMena("EUR");
            vf.setSuma_bez_dph(sumabez);
            vf.setSuma_s_dph(sumabez * 1.2);
            VFData vfData = DataFactory.createVFData();
            vfData.vloz(vf);

            if (action.equals("next")) {
                routeContext.redirect("/vf_edit");
            } else {
                routeContext.redirect("/vf");
            }
        });

        pippo.POST("/vf_delete", routeContext -> {
            String id = routeContext.getParameter("seldelid").toString();
            if (id.isEmpty()) {
                routeContext.redirect("/vf");
                return;
            }
            Integer selectedid = Integer.parseInt(id);

            VFData bankaData = DataFactory.createVFData();
            bankaData.zmaz(selectedid);

            routeContext.redirect("/vf");
        });

        pippo.GET("/vf_change", routeContext -> {
            String id = routeContext.getParameter("selectedid").toString();
            if (id.isEmpty()) {
                routeContext.redirect("/vf");
                return;
            }
            Integer selectedid = Integer.parseInt(id);

            VFData vfData = DataFactory.createVFData();
            VF vf = vfData.getVF(selectedid);
            Map<String, Object> model = new HashMap<>();
            model.put("selectedid", selectedid);
            model.put("title", "Zmena vyšlej faktúry");
            model.put("form_action", "/vf_change");

            model.put("cislo_faktury", vf.getCislo_faktury());
            model.put("odberatel", vf.getOdberatel());
            model.put("suma_bez_dph", vf.getSuma_bez_dph());
            model.put("datum", vf.getDatum());
            routeContext.render("vf_edit", model);
        });

        pippo.POST("/vf_change", routeContext -> {
            String action = routeContext.getParameter("action").toString();
            if (action == null) {
                routeContext.redirect("/vf");
                return;
            }
            Integer selectedid = routeContext.getParameter("selectedid").toInt();

            Integer cislo_faktury = routeContext.getParameter("cislo_faktury").toInt();
            String odberatel = routeContext.getParameter("odberatel").toString();
            String datum = routeContext.getParameter("datum").toString();
            Double sumabez = routeContext.getParameter("suma_bez_dph").toDouble();

            VFData vfData = DataFactory.createVFData();
            VF vf = vfData.getVF(selectedid);
            vf.setCislo_faktury(cislo_faktury);
            vf.setOdberatel(odberatel);
            vf.setDatum(datum);
            vf.setDatum_dodania(datum);
            vf.setDatum_splatnosti(datum);
            vf.setSuma_bez_dph(sumabez);
            vf.setSuma_s_dph(sumabez * 1.2);
            vfData.zmen(vf);

            routeContext.redirect("/vf");
        });
    }
}
