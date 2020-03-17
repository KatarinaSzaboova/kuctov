package sk.euba.fhi.gui;

import ro.pippo.core.Pippo;
import sk.euba.fhi.data.DataFactory;
import sk.euba.fhi.data.mysql.common.MockDataMySQL;
import sk.euba.fhi.model.interf.PouzivatelData;
import sk.euba.fhi.model.obj.Pouzivatel;

import java.util.*;

public class LoginPage {
    public static void init(Pippo pippo) {
        pippo.GET("/login", routeContext -> {

            // MockDataMySQL.DropTables();
            // MockDataMySQL.CreateTables();
            // MockDataMySQL.InsertInto();

            routeContext.setSession("pouzivatel_id", "0");
            Map<String, Object> model = new HashMap<>();
            model.put("meno", "");
            model.put("heslo", "");
            routeContext.render("login", model);
        });

        pippo.POST("/login", routeContext -> {
            String meno = routeContext.getParameter("meno").toString();
            String heslo = routeContext.getParameter("heslo").toString();
            PouzivatelData pouzivatelData = DataFactory.createPouzivatelData();
            Pouzivatel pouzivatel = pouzivatelData.autentifikuj(meno, heslo);
            if (null != pouzivatel) {
                routeContext.resetSession();
                routeContext.setSession("app_name", "kuctov");
                Integer pouzivatel_id = pouzivatel.getId();
                routeContext.setSession("pouzivatel_id", pouzivatel_id.toString());
                routeContext.setSession("meno", meno);
                routeContext.redirect("/");
            } else {
                routeContext.redirect("/login");
            }
        });
    }
}
