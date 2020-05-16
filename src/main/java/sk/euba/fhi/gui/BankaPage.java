package sk.euba.fhi.gui;

import ro.pippo.core.Pippo;
import ro.pippo.core.Session;
import sk.euba.fhi.data.DataFactory;
import sk.euba.fhi.model.interf.BankUcetData;
import sk.euba.fhi.model.interf.BankaData;
import sk.euba.fhi.model.interf.FirmaData;
import sk.euba.fhi.model.obj.BankUcet;
import sk.euba.fhi.model.obj.Banka;
import sk.euba.fhi.model.obj.Prihlasenie;

import java.util.*;

public class BankaPage {
    public static void init(Pippo pippo) {
        pippo.GET("/banka", routeContext -> {
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
                // treba opravit aj aktualny ucet lebo rozne firmy maju rozne ucty
                BankUcetData ucetData = DataFactory.createBankUcetData();
                List<BankUcet> buList = ucetData.preFirmu(firma_id);
                prihlasenie.setId_ucet(buList.get(0).getId());
                prihlasenie.setUcet_nazov(buList.get(0).getNazov());
            }
            String ucet = routeContext.getParameter("selecteducet").toString();
            if (null != ucet) {
                prihlasenie.setUcet_nazov(ucet);
                BankUcetData ucetData = DataFactory.createBankUcetData();
                int ucet_id = ucetData.idUctu(prihlasenie.getId_firma(), ucet);
                prihlasenie.setId_ucet(ucet_id);
            }

            Map<String, Object> model = new HashMap<>();
            model.put("selectedyear", prihlasenie.getRok());
            model.put("selectedfirma", prihlasenie.getFirma_nazov());
            model.put("selecteducet", prihlasenie.getUcet_nazov());

            FirmaData firmaData = DataFactory.createFirmaData();
            List<String> firmy = firmaData.nazvyFiriem(pouzivatel_id);
            model.put("firmy", firmy);

            BankUcetData ucetData = DataFactory.createBankUcetData();
            List<String> ucty = ucetData.nazvyUctov(prihlasenie.getId_firma());
            model.put("ucty", ucty);

            List<Integer> roky = new ArrayList<>(Arrays.asList(2018, 2019, 2020));
            model.put("roky", roky);

            BankaData bankaData = DataFactory.createBankaData();
            List<Banka> bankaList = bankaData.vsetky(prihlasenie.getId_ucet(), prihlasenie.getRok());
            model.put("bankas", bankaList);

            routeContext.render("banka", model);
        });

        pippo.GET("/banka_edit", routeContext -> {
            Map<String, Object> model = new HashMap<>();
            model.put("selectedid", 0);
            model.put("title", "Nový bankový doklad");
            model.put("form_action", "/banka_new");

            model.put("datum", "");
            model.put("ovplyv_zd", "A");
            model.put("partner", "");
            model.put("partner_iban", "");
            model.put("suma", "0.0");

            routeContext.render("banka_edit", model);
        });

        pippo.POST("/banka_new", routeContext -> {
            String action = routeContext.getParameter("action").toString();
            if (action == null) {
                routeContext.redirect("/banka");
                return;
            }
            Session session = routeContext.getRequest().getSession(false);
            int pouzivatel_id = Integer.parseInt(session.get("pouzivatel_id"));
            Prihlasenie prihlasenie = DataFactory.getPrihlasenie(pouzivatel_id);

            String datum = routeContext.getParameter("datum").toString();
            String ovplyv_zd = routeContext.getParameter("ovplyv_zd").toString();
            String partner = routeContext.getParameter("partner").toString();
            String partner_iban = routeContext.getParameter("partner_iban").toString();
            Double suma = routeContext.getParameter("suma").toDouble();

            Banka banka = new Banka();
            banka.setId(0);
            banka.setId_ucet(prihlasenie.getId_ucet());
            banka.setRok(prihlasenie.getRok());
            banka.setDatum(datum);
            banka.setOvplyv_zd(ovplyv_zd);
            banka.setMena("EUR");
            banka.setSuma(suma);
            banka.setPartner(partner);
            banka.setPartner_iban(partner_iban);

            BankaData bankaData = DataFactory.createBankaData();
            bankaData.vloz(banka);

            if (action.equals("next")) {
                routeContext.redirect("/banka_edit");
            } else {
                routeContext.redirect("/banka");
            }
        });

        pippo.POST("/banka_delete", routeContext -> {
            String id = routeContext.getParameter("seldelid").toString();
            if (id.isEmpty()) {
                routeContext.redirect("/banka");
                return;
            }
            Integer selectedid = Integer.parseInt(id);

            BankaData data = DataFactory.createBankaData();
            data.zmaz(selectedid);

            routeContext.redirect("/banka");
        });

        pippo.GET("/banka_change", routeContext -> {
            String id = routeContext.getParameter("selectedid").toString();
            if (id.isEmpty()) {
                routeContext.redirect("/banka");
                return;
            }
            Integer selectedid = Integer.parseInt(id);

            BankaData bankaData = DataFactory.createBankaData();
            Banka banka = bankaData.getBanka(selectedid);
            Map<String, Object> model = new HashMap<>();
            model.put("selectedid", selectedid);
            model.put("title", "Zmena bankového dokladu");
            model.put("form_action", "/banka_change");

            model.put("datum", banka.getDatum());
            model.put("suma", banka.getSuma());
            model.put("ovplyv_zd", banka.getOvplyv_zd());
            model.put("partner", banka.getPartner());
            model.put("partner_iban", banka.getPartner_iban());

            routeContext.render("banka_edit", model);
        });

        pippo.POST("/banka_change", routeContext -> {
            String action = routeContext.getParameter("action").toString();
            if (action == null) {
                routeContext.redirect("/banka");
                return;
            }
            Integer selectedid = routeContext.getParameter("selectedid").toInt();

            String datum = routeContext.getParameter("datum").toString();
            String partner = routeContext.getParameter("partner").toString();
            String ovplyv_zd = routeContext.getParameter("ovplyv_zd").toString();
            String partner_iban = routeContext.getParameter("partner_iban").toString();
            Double suma = routeContext.getParameter("suma").toDouble();

            BankaData bankaData = DataFactory.createBankaData();
            Banka banka = bankaData.getBanka(selectedid);

            banka.setDatum(datum);
            banka.setSuma(suma);
            banka.setOvplyv_zd(ovplyv_zd);
            banka.setPartner(partner);
            banka.setPartner_iban(partner_iban);

            bankaData.zmen(banka);
            routeContext.redirect("/banka");
        });
    }
}
