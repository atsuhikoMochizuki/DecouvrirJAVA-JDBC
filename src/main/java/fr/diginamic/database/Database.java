package fr.diginamic.database;

import fr.diginamic.Main;
import fr.diginamic.mochizukiTools.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ResourceBundle;

public class Database {
    private static ResourceBundle db_conf = ResourceBundle.getBundle("project");
    private static String URL = db_conf.getString("database.url");
    private static String USER = db_conf.getString("database.user");
    private static String PWD = db_conf.getString("database.pwd");

    public static Connection connect() {
        Logger log = LoggerFactory.getLogger(Main.class);
        Connection db = null;

        Utils.msgInfo(String.format("Connection à la base de données %s", URL));

        try {
            db = DriverManager.getConnection(URL, USER, PWD);
            Utils.msgInfo(String.format("Connection à la base de données %s OK", URL));
        } catch (SQLException accessError) {
            log.error(accessError.getMessage());
                throw new RuntimeException();
        }

        return db;
    }

    public static Connection disconnect(Connection db) {
        Logger log = LoggerFactory.getLogger(Main.class);
        Utils.msgInfo(String.format("Fermeture de la base de données %s", URL));
        try {
            if (db.isClosed()) {
                Utils.beep();
                log.warn("Tentative de fermeture d'une base de données non référencée en mémoire");
                Utils.msgWarning("Tentative de fermeture d'une base de données non référencée en mémoire");
                return null;
            }
            db.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Utils.msgInfo(String.format("Fermeture de la base de données %s OK", URL));
        }

        return db;
    }


//    public static int insertValue(Connection db, String table, String column, String value) {
//        Statement st = null;
//        String msg = null;
//        int nbreLines = 0;
//
//        msg = String.format("Enregistrement de la valeur %s dans la table %s(%s)", value, table, column);
//        Utils.msgInfo(msg);
//
//        try {
//            st = db.createStatement();
//            msg = String.format("INSERT INTO %s(%s) VALUES('%s')", table, column, value);
//            nbreLines = st.executeUpdate(msg);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                st.close();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        msg = String.format("Insertion dans la table OK. Nbre de lignes insérées : %d", nbreLines);
//        Utils.msgResult(msg);
//        return nbreLines;
//    }
//
//    public static int listTableRows(Connection db, String table, String column) {
//        Statement st = null;
//        String msg = null;
//        int nbreLines = 0;
//        ResultSet rs = null;
//        Utils.msgInfo(String.format("Lecture des valeurs de la colonne %s dans la table %s", column, table));
//
//        try {
//            st = db.createStatement();
//            msg = String.format("SELECT * FROM %s;", table);
//            Utils.msgConsign(msg);
//            rs = st.executeQuery(msg);
//            while (rs.next()) {
//                System.out.println(rs.getInt(1) + "-" + rs.getString(column));
//            }
//            rs.close();
//            st.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return nbreLines;
//    }
//
//    public static int updateValue(Connection db, String table, String column, String old_value, String newValue) {
//        String req = "UPDATE %s SET %s = '%s' WHERE %s = '%s'";
//        String msg = null;
//        Statement st = null;
//        int nb = 0;
//        try {
//            st = db.createStatement();
//            msg = String.format(req, table, column, newValue, column, old_value);
//            Utils.msgConsign(msg);
//            nb = st.executeUpdate(msg);
//            msg = String.format("Nbre de lignes traitées: %d", nb);
//            Utils.msgConsign(msg);
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        } finally {
//            try {
//                st.close();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return nb;
//    }
}
