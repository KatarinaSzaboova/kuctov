package sk.euba.fhi.gui;

import ro.pippo.core.Pippo;
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
            String strid = routeContext.getSession("pouzivatel_id");
            int pouzivatel_id = (strid != null) ? Integer.parseInt(strid) : 0;
            if (pouzivatel_id == 0) {
                routeContext.redirect("/login");
            }
            Prihlasenie prihlasenie = DataFactory.getPrihlasenie(pouzivatel_id);

            BankUcetData bank_ucetData = DataFactory.createBankUcetData();
            List<BankUcet> bank_ucetList = bank_ucetData.preFirmu(prihlasenie.getId_firma());
            Map<String, Object> model = new HashMap<>();
            model.put("bank_ucets", bank_ucetList);
            routeContext.render("bank_ucet", model);
        });

        pippo.GET("/bank_ucet_edit", routeContext -> {
            Map<String, Object> model = new HashMap<>();
            model.put("selectedid", 0);
            model.put("title", "Nový bankový účet");
            model.put("form_action", "/bank_ucet_new");

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
            String strid = routeContext.getSession("pouzivatel_id");
            int pouzivatel_id = (strid != null) ? Integer.parseInt(strid) : 0;
            Prihlasenie prihlasenie = DataFactory.getPrihlasenie(pouzivatel_id);


            String nazov = routeContext.getParameter("nazov").toString();
            String bic = routeContext.getParameter("bic").toString();
            String iban = routeContext.getParameter("iban").toString();
            String mena = routeContext.getParameter("mena").toString();

            BankUcet bank_ucet = new BankUcet();
            bank_ucet.setId(0);
            bank_ucet.setId_firma(prihlasenie.getId_firma());
            bank_ucet.setNazov(nazov);
            bank_ucet.setBic(bic);
            bank_ucet.setIban(iban);
            bank_ucet.setMena(mena);

            BankUcetData bank_ucetData = DataFactory.createBankUcetData();
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
            Integer selectedid = Integer.parseInt(id);

            BankUcetData data = DataFactory.createBankUcetData();
            data.zmaz(selectedid);

            routeContext.redirect("/bank_ucet");
        });

        pippo.GET("/bank_ucet_change", routeContext -> {
            String id = routeContext.getParameter("selectedid").toString();
            if (id.isEmpty()) {
                routeContext.redirect("/bank_ucet");
                return;
            }
            Integer selectedid = Integer.parseInt(id);

            BankUcetData bank_ucetData = DataFactory.createBankUcetData();
            BankUcet bank_ucet = bank_ucetData.getBankUcet(selectedid);
            Map<String, Object> model = new HashMap<>();
            model.put("selectedid", selectedid);
            model.put("title", "Zmena bankového účtu");
            model.put("form_action", "/bank_ucet_change");

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
            Integer selectedid = routeContext.getParameter("selectedid").toInt();

            String nazov = routeContext.getParameter("nazov").toString();
            String bic = routeContext.getParameter("bic").toString();
            String iban = routeContext.getParameter("iban").toString();
            String mena = routeContext.getParameter("mena").toString();

            BankUcetData bank_ucetData = DataFactory.createBankUcetData();
            BankUcet bank_ucet = bank_ucetData.getBankUcet(selectedid);

            bank_ucet.setNazov(nazov);
            bank_ucet.setBic(bic);
            bank_ucet.setIban(iban);
            bank_ucet.setMena(mena);

            bank_ucetData.zmen(bank_ucet);

            routeContext.redirect("/bank_ucet");
        });
    }
}
