package sk.euba.fhi.gui;

import ro.pippo.core.Pippo;
import ro.pippo.core.Session;
import sk.euba.fhi.data.DataFactory;
import sk.euba.fhi.model.interf.DFData;
import sk.euba.fhi.model.interf.FirmaData;
import sk.euba.fhi.model.obj.DF;
import sk.euba.fhi.model.obj.Prihlasenie;

import java.util.*;

public class DFPage {
    public static void init(Pippo pippo) {
        pippo.GET("/df", routeContext -> {
            Session session = routeContext.getRequest().getSession(false);
            if (null == session) {
                routeContext.redirect("/");
            }
            long pouzivatel_id = Long.parseLong(session.get("pouzivatel_id"));
            Prihlasenie prihlasenie = DataFactory.getPrihlasenie(pouzivatel_id);
            Integer year = routeContext.getParameter("selectedyear").toInt();
            if(0 != year) {
                prihlasenie.setRok(year);
            }
            String firma = routeContext.getParameter("selectedfirma").toString();
            if(null != firma) {
                prihlasenie.setFirma_nazov(firma);
                FirmaData firmaData = DataFactory.createDaoFirma();
                long firma_id = firmaData.idFirmy(pouzivatel_id, firma);
                prihlasenie.setFirma_id(firma_id);
            }

            Map<String, Object> model = new HashMap<>();
            model.put("selectedyear", prihlasenie.getRok());
            model.put("selectedfirma", prihlasenie.getFirma_nazov());

            FirmaData firmaData = DataFactory.createDaoFirma();
            List<String> firmy = firmaData.nazvyFiriem(pouzivatel_id);
            model.put( "firmy", firmy);

            List<Integer> roky = new ArrayList<>(Arrays.asList(2018, 2019, 2020));
            model.put("roky", roky);

            DFData dfData = DataFactory.createDaoDF();
            List<DF> dfList = dfData.vsetky(prihlasenie.getFirma_id(), prihlasenie.getRok());
            model.put("dfs", dfList);

            routeContext.render("df", model);
        });

        pippo.GET("/df_edit", routeContext -> {
            Map<String, Object> model = new HashMap<>();
            model.put("selectedid", 0);
            model.put("title", "Nová došlá faktúra");
            model.put("form_action", "/df_new");

            model.put("cislo_faktury", "");
            model.put("dodavatel", "");
            model.put("suma_bez_dph", "");
            model.put("datum", "");
            routeContext.render("df_edit", model);
        });

        pippo.POST("/df_new", routeContext -> {
            String action = routeContext.getParameter("action").toString();
            if (action == null) {
                routeContext.redirect("/df");
                return;
            }
            Session session = routeContext.getRequest().getSession(false);
            long pouzivatel_id = Long.parseLong(session.get("pouzivatel_id"));
            Prihlasenie prihlasenie = DataFactory.getPrihlasenie(pouzivatel_id);

            Long cislo_faktury = routeContext.getParameter("cislo_faktury").toLong();
            String dodavatel = routeContext.getParameter("dodavatel").toString();
            String datum = routeContext.getParameter("datum").toString();
            Double sumabez = routeContext.getParameter("suma_bez_dph").toDouble();

            DF df = new DF();
            df.setId(0L);
            df.setId_firma(prihlasenie.getFirma_id());
            df.setCislo_faktury(cislo_faktury);
            df.setRok(prihlasenie.getRok());
            df.setDodavatel(dodavatel);
            df.setDatum(datum);
            df.setDatum_dodania(datum);
            df.setDatum_splatnosti(datum);
            df.setMena("EUR");
            df.setSuma_bez_dph(sumabez);
            df.setSuma_s_dph(sumabez * 1.2);
            DFData dfData = DataFactory.createDaoDF();
            dfData.vloz(df);

            if (action.equals("next")) {
                routeContext.redirect("/df_edit");
            } else {
                routeContext.redirect("/df");
            }
        });

        pippo.POST("/df_delete", routeContext -> {
            String id = routeContext.getParameter("seldelid").toString();
            if (id.isEmpty()) {
                routeContext.redirect("/df");
                return;
            }
            Long selectedid = Long.parseLong(id);

            DFData data = DataFactory.createDaoDF();
            data.zmaz(selectedid);

            routeContext.redirect("/df");
        });

        pippo.GET("/df_change", routeContext -> {
            String id = routeContext.getParameter("selectedid").toString();
            if (id.isEmpty()) {
                routeContext.redirect("/df");
                return;
            }
            Long selectedid = Long.parseLong(id);

            DFData dfData = DataFactory.createDaoDF();
            DF df = dfData.getDF(selectedid);
            Map<String, Object> model = new HashMap<>();
            model.put("selectedid", selectedid);
            model.put("title", "Zmena došlej faktúry");
            model.put("form_action", "/df_change");

            model.put("cislo_faktury", df.getCislo_faktury());
            model.put("dodavatel", df.getDodavatel());
            model.put("suma_bez_dph", df.getSuma_bez_dph());
            model.put("datum", df.getDatum());
            routeContext.render("df_edit", model);
        });

        pippo.POST("/df_change", routeContext -> {
            String action = routeContext.getParameter("action").toString();
            if (action == null) {
                routeContext.redirect("/df");
                return;
            }
            Long selectedid = routeContext.getParameter("selectedid").toLong();

            Long cislo_faktury = routeContext.getParameter("cislo_faktury").toLong();
            String dodavatel = routeContext.getParameter("dodavatel").toString();
            String datum = routeContext.getParameter("datum").toString();
            Double sumabez = routeContext.getParameter("suma_bez_dph").toDouble();

            DFData dfData = DataFactory.createDaoDF();
            DF df = dfData.getDF(selectedid);
            df.setCislo_faktury(cislo_faktury);
            df.setDodavatel(dodavatel);
            df.setDatum(datum);
            df.setDatum_dodania(datum);
            df.setDatum_splatnosti(datum);
            df.setSuma_bez_dph(sumabez);
            df.setSuma_s_dph(sumabez * 1.2);
            dfData.zmen(df);

            routeContext.redirect("/df");
        });
    }
}