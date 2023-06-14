package fr.diginamic;

import fr.diginamic.database.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public class Main {
    private static final Logger LOG = LoggerFactory.getLogger (Main.class);
//    public static final String URL = "jdbc:mysql://localhost:3306/";
//    public static final String USER = "root";
//    public static final String PWD = "root";

    public static void main(String[] args) {
//        Utils.clearConsole ();
//        Params.welcomePrompt ();
//        LOG.info ("Bonjour Slf4J !");
//        LOG.info ("Implementation Logback");
        //LogAppender.executer ("Démarrage de l'application");
        Connection db = Database.connect ();
        Database.listTableRows (db,"FOURNISSEUR","NOM");
        Database.updateValue (db,"FOURNISSEUR","NOM","FDM SA","TOTOLITOTO");
        Database.listTableRows (db,"FOURNISSEUR","NOM");

        db = Database.disconnect (db);



    }




    public static int addition(int a, int b) {
        return a + b;
    }
}