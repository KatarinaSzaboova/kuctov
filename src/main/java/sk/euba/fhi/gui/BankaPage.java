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
            Session session = routeContext.getRequest().getSession(false);
            if (null == session) {
                routeContext.redirect("/");
            }
            long pouzivatel_id = Long.parseLong(session.get("pouzivatel_id"));
            Prihlasenie prihlasenie = DataFactory.getPrihlasenie(pouzivatel_id);
            Integer year = routeContext.getParameter("selectedyear").toInt();
            if (0 != year) {
                prihlasenie.setRok(year);
            }
            String firma = routeContext.getParameter("selectedfirma").toString();
            if (null != firma) {
                prihlasenie.setFirma_nazov(firma);
                FirmaData firmaData = DataFactory.createDaoFirma();
                long firma_id = firmaData.idFirmy(pouzivatel_id, firma);
                prihlasenie.setFirma_id(firma_id);
                // treba opravit aj aktualny ucet lebo rozne firmy maju rozne ucty
                BankUcetData ucetData = DataFactory.createDaoBankUcet();
                List<BankUcet> buList = ucetData.preFirmu(firma_id);
                prihlasenie.setUcet_id(buList.get(0).getId());
                prihlasenie.setUcet_nazov(buList.get(0).getNazov());
            }
            String ucet = routeContext.getParameter("selecteducet").toString();
            if (null != ucet) {
                prihlasenie.setUcet_nazov(ucet);
                BankUcetData ucetData = DataFactory.createDaoBankUcet();
                long ucet_id = ucetData.idUctu(prihlasenie.getFirma_id(), ucet);
                prihlasenie.setUcet_id(ucet_id);
            }

            Map<String, Object> model = new HashMap<>();
            model.put("selectedyear", prihlasenie.getRok());
            model.put("selectedfirma", prihlasenie.getFirma_nazov());
            model.put("selecteducet", prihlasenie.getUcet_nazov());

            FirmaData firmaData = DataFactory.createDaoFirma();
            List<String> firmy = firmaData.nazvyFiriem(pouzivatel_id);
            model.put("firmy", firmy);

            BankUcetData ucetData = DataFactory.createDaoBankUcet();
            List<String> ucty = ucetData.nazvyUctov(prihlasenie.getFirma_id());
            model.put("ucty", ucty);

            List<Integer> roky = new ArrayList<>(Arrays.asList(2018, 2019, 2020));
            model.put("roky", roky);

            BankaData bankaData = DataFactory.createDaoBanka();
            List<Banka> bankaList = bankaData.vsetky(prihlasenie.getFirma_id(), prihlasenie.getUcet_id(), prihlasenie.getRok());
            model.put("bankas", bankaList);

            routeContext.render("banka", model);
        });

        pippo.GET("/banka_edit", routeContext -> {
            Map<String, Object> model = new HashMap<>();
            model.put("selectedid", 0);
            model.put("title", "Nový bankový doklad");
            model.put("form_action", "/banka_new");

            model.put("datum", "");
            model.put("prijem_vydaj", "P");
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
            long pouzivatel_id = Long.parseLong(session.get("pouzivatel_id"));
            Prihlasenie prihlasenie = DataFactory.getPrihlasenie(pouzivatel_id);

            String datum = routeContext.getParameter("datum").toString();
            String prijem_vydaj = routeContext.getParameter("prijem_vydaj").toString();
            String ovplyv_zd = routeContext.getParameter("ovplyv_zd").toString();
            String partner = routeContext.getParameter("partner").toString();
            String partner_iban = routeContext.getParameter("partner_iban").toString();
            Double suma = routeContext.getParameter("suma").toDouble();

            Banka banka = new Banka();
            banka.setId(0L);
            banka.setId_firma(prihlasenie.getFirma_id());
            banka.setId_ucet(prihlasenie.getUcet_id());
            banka.setRok(prihlasenie.getRok());
            banka.setDatum(datum);
            banka.setOvplyv_zd(ovplyv_zd);
            banka.setMena("EUR");
            banka.setSuma(suma);
            banka.setPartner(partner);
            banka.setPartner_iban(partner_iban);

            BankaData bankaData = DataFactory.createDaoBanka();
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
            Long selectedid = Long.parseLong(id);

            BankaData data = DataFactory.createDaoBanka();
            data.zmaz(selectedid);

            routeContext.redirect("/banka");
        });

        pippo.GET("/banka_change", routeContext -> {
            String id = routeContext.getParameter("selectedid").toString();
            if (id.isEmpty()) {
                routeContext.redirect("/banka");
                return;
            }
            Long selectedid = Long.parseLong(id);

            BankaData bankaData = DataFactory.createDaoBanka();
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
            Long selectedid = routeContext.getParameter("selectedid").toLong();

            String datum = routeContext.getParameter("datum").toString();
            String partner = routeContext.getParameter("partner").toString();
            String prijem_vydaj = routeContext.getParameter("prijem_vydaj").toString();
            String ovplyv_zd = routeContext.getParameter("ovplyv_zd").toString();
            String partner_iban = routeContext.getParameter("partner_iban").toString();
            Double suma = routeContext.getParameter("suma").toDouble();

            BankaData bankaData = DataFactory.createDaoBanka();
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
