package sk.euba.fhi;

import ro.pippo.core.Application;
import ro.pippo.core.Pippo;
import ro.pippo.core.RequestResponseFactory;
import sk.euba.fhi.gui.*;
import java.util.HashMap;
import java.util.Map;

import ro.pippo.session.SessionDataStorage;
import ro.pippo.session.SessionManager;
import ro.pippo.session.SessionRequestResponseFactory;
import ro.pippo.session.cookie.CookieSessionDataStorage;

public class AppClass extends Application {
    static Pippo pippo = null;

    @Override
    protected void onInit() {
        this.addPublicResourceRoute(); // "/public/..."

        this.GET("/", routeContext -> {
            String strid = routeContext.getSession("pouzivatel_id");
            long pouzivatel_id = (strid != null) ? Long.parseLong(strid) : 0;
            if (pouzivatel_id == 0) {
                routeContext.redirect("/login");
            }
            Map<String, Object> model = new HashMap<>();
            routeContext.render("index", model);
        });

        LoginPage.init(pippo);
        VFPage.init(pippo);
        DFPage.init(pippo);
        PokladnaPage.init(pippo);
        UploadVypisyPage.init(pippo);
        PouzivatelPage.init(pippo);
        BankaPage.init(pippo);
        BankUcetPage.init(pippo);
        FirmaPage.init(pippo);
    }

    public static void main(String[] args) {
        pippo = new Pippo(new AppClass());
        pippo.start();
    }

    @Override
    protected RequestResponseFactory createRequestResponseFactory() {
        SessionDataStorage sessionDataStorage = new CookieSessionDataStorage(this.getPippoSettings());
        SessionManager sessionManager = new SessionManager(sessionDataStorage);
        return new SessionRequestResponseFactory(this, sessionManager);
    }
}
