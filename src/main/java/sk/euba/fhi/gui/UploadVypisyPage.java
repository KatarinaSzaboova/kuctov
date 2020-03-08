package sk.euba.fhi.gui;

import com.opencsv.CSVReader;
import ro.pippo.core.FileItem;
import ro.pippo.core.Pippo;
import ro.pippo.core.Session;
import sk.euba.fhi.data.DataFactory;
import sk.euba.fhi.model.interf.BankaData;
import sk.euba.fhi.model.obj.Banka;
import sk.euba.fhi.model.obj.Prihlasenie;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class UploadVypisyPage {
    public static List<List<String>> readCsvFile(String fileName) throws IOException {
        List<List<String>> records = new ArrayList<List<String>>();

        try (FileInputStream fis = new FileInputStream(fileName);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_16LE);
             CSVReader csvReader = new CSVReader(isr)) {
                String[] values = null;
                while ((values = csvReader.readNext()) != null) {
                    records.add(Arrays.asList(values));
                }
        }
        return records;
    }

    public static void init(Pippo pippo) {
        pippo.GET("/upload_vypisy", routeContext -> {
            String error = routeContext.getParameter("error").toString();
            String info = routeContext.getParameter("info").toString();

            Map<String, Object> model = new HashMap<>();
            model.put("error", error);
            model.put("info", info);
            routeContext.render("upload_vypisy", model);
        });

        pippo.POST("/upload_vypisy", routeContext -> {
            String submitter = routeContext.getParameter("submitter").toString();
            System.out.println("submitter = " + submitter);

            Session session = routeContext.getRequest().getSession(false);
            long pouzivatel_id = Long.parseLong(session.get("pouzivatel_id"));
            Prihlasenie prihlasenie = DataFactory.getPrihlasenie(pouzivatel_id);

            FileItem file = routeContext.getRequest().getFile("file");
            System.out.println("file = " + file);
            try {
                String fileName = file.getSubmittedFileName();
                String toDir = "upload/firma" + prihlasenie.getFirma_id().toString() + "/";
                Files.createDirectories(Paths.get(toDir));
                String filePath = toDir + fileName;
                File uploadedFile = new File(filePath);
                file.write(uploadedFile);

                Map<String, Object> model = new HashMap<>();
                String info = "Súbor '" + fileName + "' bol úspešne nahratý'";
                model.put("info", info);
                routeContext.render("upload_vypisy", model);

                List<List<String>> zaznamy = readCsvFile(filePath);
                for (int i = 1; i < zaznamy.size(); i++) {
                    List<String> zaznam = zaznamy.get(i);

                    String datum = zaznam.get(0);
                    Double suma = Double.parseDouble(zaznam.get(1).replace(",", "."));
                    String mena = zaznam.get(2);
                    String partner = zaznam.get(3);
                    String partner_iban = zaznam.get(4);

                    Banka banka = new Banka();
                    banka.setId(0L);
                    banka.setId_firma(prihlasenie.getFirma_id());
                    banka.setId_ucet(prihlasenie.getUcet_id());
                    banka.setRok(prihlasenie.getRok()); //****
                    banka.setDatum(datum);
                    banka.setOvplyv_zd("N"); //****
                    banka.setMena(mena);
                    banka.setSuma(suma);
                    banka.setPartner(partner);
                    banka.setPartner_iban(partner_iban);

                    BankaData bankaData = DataFactory.createDaoBanka();
                    bankaData.vloz(banka);
                }
            } catch (IOException e) {
                String error = "Chyba pri nahrávaní súboru!";
                Map<String, Object> model = new HashMap<>();
                model.put("error", error);
                routeContext.render("upload_vypisy", model);
            }
        });
    }
}
