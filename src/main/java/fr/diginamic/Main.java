package fr.diginamic;

import ch.qos.logback.core.encoder.JsonEscapeUtil;
import fr.diginamic.database.Database;
import fr.diginamic.mochizukiTools.Params;
import fr.diginamic.mochizukiTools.Utils;
import fr.diginamic.service.LogAppender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
        db = Database.disconnect (db);
        db = Database.disconnect (db);


    }




    public static int addition(int a, int b) {
        return a + b;
    }
}