package sk.euba.fhi.gui;

import ro.pippo.core.Pippo;
import ro.pippo.core.Session;
import sk.euba.fhi.data.DataFactory;
import sk.euba.fhi.model.interf.FirmaData;
import sk.euba.fhi.model.obj.Firma;
import sk.euba.fhi.model.obj.Prihlasenie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirmaPage {
    public static void init(Pippo pippo) {
        pippo.GET("/firma", routeContext -> {
            String strid = routeContext.getSession("pouzivatel_id");
            int pouzivatel_id = (strid != null) ? Integer.parseInt(strid) : 0;
            if (pouzivatel_id == 0) {
                routeContext.redirect("/login");
            }
            Prihlasenie prihlasenie = DataFactory.getPrihlasenie(pouzivatel_id);

            FirmaData firmaData = DataFactory.createFirmaData();
            List<Firma> firmaList = firmaData.vsetky(pouzivatel_id);
            Map<String, Object> model = new HashMap<>();
            model.put("firmas", firmaList);
            routeContext.render("firma", model);
        });

        pippo.GET("/firma_edit", routeContext -> {
            Map<String, Object> model = new HashMap<>();
            model.put("selectedid", 0);
            model.put("title", "Nová firma");
            model.put("form_action", "/firma_new");

            model.put("id", "");
            model.put("id_pouzivatel", "");
            model.put("nazov", "");
            model.put("adresa", "");
            model.put("psc", "");
            model.put("ico", "");
            model.put("dic", "");
            model.put("ic_dph", "");

            routeContext.render("firma_edit", model);
        });

        pippo.POST("/firma_new", routeContext -> {
            String action = routeContext.getParameter("action").toString();
            if (action == null) {
                routeContext.redirect("/firma");
                return;
            }
            Session session = routeContext.getRequest().getSession(false);
            int pouzivatel_id = Integer.parseInt(session.get("pouzivatel_id"));

            String nazov = routeContext.getParameter("nazov").toString();
            String adresa = routeContext.getParameter("adresa").toString();
            String psc = routeContext.getParameter("psc").toString();
            String ico = routeContext.getParameter("ico").toString();
            String dic = routeContext.getParameter("dic").toString();
            String ic_dph = routeContext.getParameter("ic_dph").toString();

            Firma firma = new Firma();
            firma.setId(0);
            firma.setId_pouzivatel(pouzivatel_id);
            firma.setNazov(nazov);
            firma.setAdresa(adresa);
            firma.setPsc(psc);
            firma.setIco(ico);
            firma.setDic(dic);
            firma.setIc_dph(ic_dph);
            firma.setPsc(psc);

            FirmaData firmaData = DataFactory.createFirmaData();
            firmaData.vloz(firma);

            if (action.equals("next")) {
                routeContext.redirect("/firma_edit");
            } else {
                routeContext.redirect("/firma");
            }
        });

        pippo.POST("/firma_delete", routeContext -> {
            String id = routeContext.getParameter("seldelid").toString();
            if (id.isEmpty()) {
                routeContext.redirect("/firma");
                return;
            }
            Integer selectedid = Integer.parseInt(id);

            FirmaData data = DataFactory.createFirmaData();
            data.zmaz(selectedid);

            routeContext.redirect("/firma");
        });

        pippo.GET("/firma_change", routeContext -> {
            String id = routeContext.getParameter("selectedid").toString();
            if (id.isEmpty()) {
                routeContext.redirect("/firma");
                return;
            }
            Integer selectedid = Integer.parseInt(id);

            FirmaData firmaData = DataFactory.createFirmaData();
            Firma firma = firmaData.getFirma(selectedid);
            Map<String, Object> model = new HashMap<>();
            model.put("selectedid", selectedid);
            model.put("title", "Zmena Firmy");
            model.put("form_action", "/firma_change");

            model.put("id_pouzivatel", firma.getId_pouzivatel());
            model.put("nazov", firma.getNazov());
            model.put("adresa", firma.getAdresa());
            model.put("psc", firma.getPsc());
            model.put("ico", firma.getIco());
            model.put("dic", firma.getDic());
            model.put("ic_dph", firma.getIc_dph());

            routeContext.render("firma_edit", model);
        });

        pippo.POST("/firma_change", routeContext -> {
            String action = routeContext.getParameter("action").toString();
            if (action == null) {
                routeContext.redirect("/firma");
                return;
            }
            Integer selectedid = routeContext.getParameter("selectedid").toInt();

            String nazov = routeContext.getParameter("nazov").toString();
            String adresa = routeContext.getParameter("adresa").toString();
            String psc = routeContext.getParameter("psc").toString();
            String ico = routeContext.getParameter("ico").toString();
            String dic = routeContext.getParameter("dic").toString();
            String ic_dph = routeContext.getParameter("ic_dph").toString();

            FirmaData firmaData = DataFactory.createFirmaData();
            Firma firma = firmaData.getFirma(selectedid);

            firma.setNazov(nazov);
            firma.setAdresa(adresa);
            firma.setPsc(psc);
            firma.setIco(ico);
            firma.setDic(dic);
            firma.setIc_dph(ic_dph);

            firmaData.zmen(firma);

            routeContext.redirect("/firma");
        });
    }
}
