package sk.euba.fhi.gui;

import ro.pippo.core.Pippo;
import sk.euba.fhi.data.DataFactory;
import sk.euba.fhi.model.interf.PouzivatelData;
import sk.euba.fhi.model.obj.Pouzivatel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PouzivatelPage {
    public static void init(Pippo pippo) {
        pippo.GET("/pouzivatel", routeContext -> {
            String strid = routeContext.getSession("pouzivatel_id");
            int pouzivatel_id = (strid != null) ? Integer.parseInt(strid) : 0;
            if (pouzivatel_id == 0) {
                routeContext.redirect("/login");
            }
            PouzivatelData pouzivatelData = DataFactory.createPouzivatelData();
            List<Pouzivatel> pouzivatelList = pouzivatelData.vsetky();
            Map<String, Object> model = new HashMap<>();
            model.put("pouzivatels", pouzivatelList);
            routeContext.render("pouzivatel", model);
        });

        pippo.GET("/pouzivatel_edit", routeContext -> {
            Map<String, Object> model = new HashMap<>();
            model.put("selectedid", 0);
            model.put("title", "Nový používateľ");
            model.put("form_action", "/pouzivatel_new");

            model.put("login_meno", "");
            model.put("login_heslo", "");
            model.put("meno", "");
            model.put("priezvisko", "");
            model.put("email", "");
            model.put("adresa", "");
            model.put("psc", "");

            routeContext.render("pouzivatel_edit", model);
        });

        pippo.POST("/pouzivatel_new", routeContext -> {
            String action = routeContext.getParameter("action").toString();
            if (action == null) {
                routeContext.redirect("/pouzivatel");
                return;
            }

            String login_meno = routeContext.getParameter("login_meno").toString();
            String login_heslo = routeContext.getParameter("login_heslo").toString();
            String meno = routeContext.getParameter("meno").toString();
            String priezvisko = routeContext.getParameter("priezvisko").toString();
            String email = routeContext.getParameter("email").toString();
            String adresa = routeContext.getParameter("adresa").toString();
            String psc = routeContext.getParameter("psc").toString();

            Pouzivatel pouzivatel = new Pouzivatel();
            pouzivatel.setId(0);
            pouzivatel.setLogin_meno(login_meno);
            pouzivatel.setLogin_heslo(login_heslo);
            pouzivatel.setMeno(meno);
            pouzivatel.setPriezvisko(priezvisko);
            pouzivatel.setEmail(email);
            pouzivatel.setAdresa(adresa);
            pouzivatel.setPsc(psc);

            PouzivatelData pouzivatelData = DataFactory.createPouzivatelData();
            pouzivatelData.vloz(pouzivatel);

            if (action.equals("next")) {
                routeContext.redirect("/pouzivatel_edit");
            } else {
                routeContext.redirect("/pouzivatel");
            }
        });

        pippo.POST("/pouzivatel_delete", routeContext -> {
            String id = routeContext.getParameter("seldelid").toString();
            if (id.isEmpty()) {
                routeContext.redirect("/pouzivatel");
                return;
            }
            Integer selectedid = Integer.parseInt(id);

            PouzivatelData data = DataFactory.createPouzivatelData();
            data.zmaz(selectedid);

            routeContext.redirect("/pouzivatel");
        });

        pippo.GET("/pouzivatel_change", routeContext -> {
            String id = routeContext.getParameter("selectedid").toString();
            if (id.isEmpty()) {
                routeContext.redirect("/pouzivatel");
                return;
            }
            Integer selectedid = Integer.parseInt(id);

            PouzivatelData pouzivatelData = DataFactory.createPouzivatelData();
            Pouzivatel pouzivatel = pouzivatelData.getPouzivatel(selectedid);
            Map<String, Object> model = new HashMap<>();
            model.put("selectedid", selectedid);
            model.put("title", "Zmena používateľa");
            model.put("form_action", "/pouzivatel_change");

            model.put("login_meno", pouzivatel.getLogin_meno());
            model.put("login_heslo", pouzivatel.getLogin_heslo());
            model.put("meno", pouzivatel.getMeno());
            model.put("priezvisko", pouzivatel.getPriezvisko());
            model.put("email", pouzivatel.getEmail());
            model.put("adresa", pouzivatel.getAdresa());
            model.put("psc", pouzivatel.getPsc());

            routeContext.render("pouzivatel_edit", model);
        });

        pippo.POST("/pouzivatel_change", routeContext -> {
            String action = routeContext.getParameter("action").toString();
            if (action == null) {
                routeContext.redirect("/pouzivatel");
                return;
            }
            Integer selectedid = routeContext.getParameter("selectedid").toInt();

            String login_meno = routeContext.getParameter("login_meno").toString();
            String login_heslo = routeContext.getParameter("login_heslo").toString();
            String meno = routeContext.getParameter("meno").toString();
            String priezvisko = routeContext.getParameter("priezvisko").toString();
            String email = routeContext.getParameter("email").toString();
            String adresa = routeContext.getParameter("adresa").toString();
            String psc = routeContext.getParameter("psc").toString();

            PouzivatelData pouzivatelData = DataFactory.createPouzivatelData();
            Pouzivatel pouzivatel = pouzivatelData.getPouzivatel(selectedid);

            pouzivatel.setLogin_meno(login_meno);
            pouzivatel.setLogin_heslo(login_heslo);
            pouzivatel.setMeno(meno);
            pouzivatel.setPriezvisko(priezvisko);
            pouzivatel.setEmail(email);
            pouzivatel.setAdresa(adresa);
            pouzivatel.setPsc(psc);

            pouzivatelData.zmen(pouzivatel);

            routeContext.redirect("/pouzivatel");
        });
    }
}
