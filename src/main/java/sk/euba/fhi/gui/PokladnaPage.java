package sk.euba.fhi.gui;

import ro.pippo.core.Pippo;
import ro.pippo.core.Session;
import sk.euba.fhi.data.DataFactory;
import sk.euba.fhi.model.interf.FirmaData;
import sk.euba.fhi.model.interf.PokladnaData;
import sk.euba.fhi.model.obj.Pokladna;
import sk.euba.fhi.model.obj.Prihlasenie;

import java.util.*;

public class PokladnaPage {
    public static void init(Pippo pippo) {
        pippo.GET("/pokladna", routeContext -> {
            String strid = routeContext.getSession("pouzivatel_id");
            int pouzivatel_id = (strid != null) ? Integer.parseInt(strid) : 0;
            if (pouzivatel_id == 0) {
                routeContext.redirect("/login");
            }
            Prihlasenie prihlasenie = DataFactory.getPrihlasenie(pouzivatel_id);
            Integer year = routeContext.getParameter("selectedyear").toInt();
            if (0 != year) {
                prihlasenie.setRok(year);
            }
            String firma = routeContext.getParameter("selectedfirma").toString();
            if (null != firma) {
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
            model.put("firmy", firmy);

            List<Integer> roky = new ArrayList<>(Arrays.asList(2018, 2019, 2020));
            model.put("roky", roky);

            PokladnaData pokladnaData = DataFactory.createPokladnaData();
            List<Pokladna> pokladnaList = pokladnaData.vsetky(prihlasenie.getId_firma(), prihlasenie.getRok());
            model.put("pokladnas", pokladnaList);

            routeContext.render("pokladna", model);
        });

        pippo.GET("/pokladna_edit", routeContext -> {
            Map<String, Object> model = new HashMap<>();
            model.put("selectedid", 0);
            model.put("title", "Nový pokladničný doklad");
            model.put("form_action", "/pokladna_new");

            model.put("vzor", "");
            model.put("cislo_dokladu", "");
            model.put("suma_bez_dph", "");
            model.put("datum", "");
            model.put("ovplyv_zd", "A");

            routeContext.render("pokladna_edit", model);
        });

        pippo.POST("/pokladna_new", routeContext -> {
            String action = routeContext.getParameter("action").toString();
            if (action == null) {
                routeContext.redirect("/pokladna");
                return;
            }
            Session session = routeContext.getRequest().getSession(false);
            int pouzivatel_id = Integer.parseInt(session.get("pouzivatel_id"));
            Prihlasenie prihlasenie = DataFactory.getPrihlasenie(pouzivatel_id);

            Integer cislo_dokladu = routeContext.getParameter("cislo_dokladu").toInt();
            String vzor = routeContext.getParameter("vzor").toString();
            String datum = routeContext.getParameter("datum").toString();
            String ovplyv_zd = routeContext.getParameter("ovplyv_zd").toString();
            Double sumabez = routeContext.getParameter("suma_bez_dph").toDouble();

            Pokladna pokladna = new Pokladna();
            pokladna.setId(0);
            pokladna.setId_firma(prihlasenie.getId_firma());
            pokladna.setCislo_dokladu(cislo_dokladu);
            pokladna.setRok(prihlasenie.getRok());
            pokladna.setVzor(vzor);
            pokladna.setDatum(datum);
            pokladna.setMena("EUR");
            pokladna.setSuma_bez_dph(sumabez);
            pokladna.setSuma_s_dph(sumabez * 1.2);
            pokladna.setOvplyv_zd(ovplyv_zd);

            PokladnaData pokladnaData = DataFactory.createPokladnaData();
            pokladnaData.vloz(pokladna);

            if (action.equals("next")) {
                routeContext.redirect("/pokladna_edit");
            } else {
                routeContext.redirect("/pokladna");
            }
        });

        pippo.POST("/pokladna_delete", routeContext -> {
            String id = routeContext.getParameter("seldelid").toString();
            if (id.isEmpty()) {
                routeContext.redirect("/pokladna");
                return;
            }
            Integer selectedid = Integer.parseInt(id);

            PokladnaData data = DataFactory.createPokladnaData();
            data.zmaz(selectedid);

            routeContext.redirect("/pokladna");
        });

        pippo.GET("/pokladna_change", routeContext -> {
            String id = routeContext.getParameter("selectedid").toString();
            if (id.isEmpty()) {
                routeContext.redirect("/pokladna");
                return;
            }
            Integer selectedid = Integer.parseInt(id);

            PokladnaData pokladnaData = DataFactory.createPokladnaData();
            Pokladna pokladna = pokladnaData.getPokladna(selectedid);
            Map<String, Object> model = new HashMap<>();
            model.put("selectedid", selectedid);
            model.put("title", "Zmena pokladničného dokladu");
            model.put("form_action", "/pokladna_change");

            model.put("cislo_dokladu", pokladna.getCislo_dokladu());
            model.put("vzor", pokladna.getVzor());
            model.put("suma_bez_dph", pokladna.getSuma_bez_dph());
            model.put("datum", pokladna.getDatum());
            model.put("ovplyv_zd", pokladna.getOvplyv_zd());
            routeContext.render("pokladna_edit", model);
        });

        pippo.POST("/pokladna_change", routeContext -> {
            String action = routeContext.getParameter("action").toString();
            if (action == null) {
                routeContext.redirect("/pokladna");
                return;
            }
            Integer selectedid = routeContext.getParameter("selectedid").toInt();

            Integer cislo_dokladu = routeContext.getParameter("cislo_dokladu").toInt();
            String vzor = routeContext.getParameter("vzor").toString();
            String datum = routeContext.getParameter("datum").toString();
            Double sumabez = routeContext.getParameter("suma_bez_dph").toDouble();
            String ovplyv_zd = routeContext.getParameter("ovplyv_zd").toString();

            PokladnaData pokladnaData = DataFactory.createPokladnaData();

            Pokladna pokladna = pokladnaData.getPokladna(selectedid);
            pokladna.setCislo_dokladu(cislo_dokladu);
            pokladna.setVzor(vzor);
            pokladna.setDatum(datum);
            pokladna.setSuma_bez_dph(sumabez);
            pokladna.setSuma_s_dph(sumabez * 1.2);
            pokladna.setOvplyv_zd(ovplyv_zd);

            pokladnaData.zmen(pokladna);

            routeContext.redirect("/pokladna");
        });
    }
}
