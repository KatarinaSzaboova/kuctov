package sk.euba.fhi.gui;

import ro.pippo.core.Pippo;
import ro.pippo.core.Session;
import sk.euba.fhi.data.DataFactory;
import sk.euba.fhi.model.interf.BankUcetData;
import sk.euba.fhi.model.obj.BankUcet;
import sk.euba.fhi.model.obj.Prihlasenie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankUcetPage {
    public static void init(Pippo pippo) {
        pippo.GET("/bank_ucet", routeContext -> {
            Session session = routeContext.getRequest().getSession(false);
            if (null == session) {
                routeContext.redirect("/");
            }
            long pouzivatel_id = Long.parseLong(session.get("pouzivatel_id"));
            Prihlasenie prihlasenie = DataFactory.getPrihlasenie(pouzivatel_id);

            BankUcetData bank_ucetData = DataFactory.createDaoBankUcet();
            List<BankUcet> bank_ucetList = bank_ucetData.vsetky();
            Map<String, Object> model = new HashMap<>();
            model.put("bank_ucets", bank_ucetList);
            routeContext.render("bank_ucet", model);
        });

        pippo.GET("/bank_ucet_edit", routeContext -> {
            Map<String, Object> model = new HashMap<>();
            model.put("selectedid", 0);
            model.put("title", "Nový bankový účet");
            model.put("form_action", "/bank_ucet_new");

            model.put("id_firmy", "");
            model.put("nazov", "");
            model.put("bic", "");
            model.put("iban", "");
            model.put("mena", "EUR");

            routeContext.render("bank_ucet_edit", model);
        });

        pippo.POST("/bank_ucet_new", routeContext -> {
            String action = routeContext.getParameter("action").toString();
            if (action == null) {
                routeContext.redirect("/bank_ucet");
                return;
            }

            String nazov = routeContext.getParameter("nazov").toString();
            String bic = routeContext.getParameter("bic").toString();
            String iban = routeContext.getParameter("iban").toString();
            String mena = routeContext.getParameter("mena").toString();

            BankUcet bank_ucet = new BankUcet();
            bank_ucet.setId(0);
            bank_ucet.setId_firma(0);
            bank_ucet.setNazov(nazov);
            bank_ucet.setBic(bic);
            bank_ucet.setIban(iban);
            bank_ucet.setMena(mena);

            BankUcetData bank_ucetData = DataFactory.createDaoBankUcet();
            bank_ucetData.vloz(bank_ucet);

            if (action.equals("next")) {
                routeContext.redirect("/bank_ucet_edit");
            } else {
                routeContext.redirect("/bank_ucet");
            }
        });

        pippo.POST("/bank_ucet_delete", routeContext -> {
            String id = routeContext.getParameter("seldelid").toString();
            if (id.isEmpty()) {
                routeContext.redirect("/bank_ucet");
                return;
            }
            Long selectedid = Long.parseLong(id);

            BankUcetData data = DataFactory.createDaoBankUcet();
            data.zmaz(selectedid);

            routeContext.redirect("/bank_ucet");
        });

        pippo.GET("/bank_ucet_change", routeContext -> {
            String id = routeContext.getParameter("selectedid").toString();
            if (id.isEmpty()) {
                routeContext.redirect("/bank_ucet");
                return;
            }
            Long selectedid = Long.parseLong(id);

            BankUcetData bank_ucetData = DataFactory.createDaoBankUcet();
            BankUcet bank_ucet = bank_ucetData.getBankUcet(selectedid);
            Map<String, Object> model = new HashMap<>();
            model.put("selectedid", selectedid);
            model.put("title", "Zmena bankového účtu");
            model.put("form_action", "/bank_ucet_change");

            model.put("id_firmy", bank_ucet.getId_firma());
            model.put("nazov", bank_ucet.getNazov());
            model.put("bic", bank_ucet.getBic());
            model.put("iban", bank_ucet.getIban());
            model.put("mena", bank_ucet.getMena());

            routeContext.render("bank_ucet_edit", model);
        });

        pippo.POST("/bank_ucet_change", routeContext -> {
            String action = routeContext.getParameter("action").toString();
            if (action == null) {
                routeContext.redirect("/bank_ucet");
                return;
            }
            Long selectedid = routeContext.getParameter("selectedid").toLong();

            Long id_firmy = routeContext.getParameter("id_firmy").toLong();
            String nazov = routeContext.getParameter("nazov").toString();
            String bic = routeContext.getParameter("bic").toString();
            String iban = routeContext.getParameter("iban").toString();
            String mena = routeContext.getParameter("mena").toString();

            BankUcetData bank_ucetData = DataFactory.createDaoBankUcet();
            BankUcet bank_ucet = bank_ucetData.getBankUcet(selectedid);

            bank_ucet.setId_firma(id_firmy);
            bank_ucet.setNazov(nazov);
            bank_ucet.setBic(bic);
            bank_ucet.setIban(iban);
            bank_ucet.setMena(mena);

            bank_ucetData.zmen(bank_ucet);

            routeContext.redirect("/bank_ucet");
        });
    }
}
