package fr.diginamic.database;

import fr.diginamic.Main;
import fr.diginamic.entites.Fournisseur;
import fr.diginamic.mochizukiTools.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ResourceBundle;

public class Database {
    private static ResourceBundle db_conf;
    private static String URL, USER, PWD;

    static {
        db_conf = ResourceBundle.getBundle("project");
        URL = db_conf.getString("database.url");
        USER = db_conf.getString("database.user");
        PWD = db_conf.getString("database.pwd");
    }

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

    public static void insertEntity(String tableName, String Column, String value) {
        Logger log = LoggerFactory.getLogger(Fournisseur.class);
        Connection db = Database.connect();

        String req = String.format("INSERT INTO %s(%s) VALUES(?)", tableName, Column);
        Utils.msgDebug(req);

        try (PreparedStatement pst = db.prepareStatement(req)) {
            pst.setString(1,value);
            pst.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

        Database.disconnect(db);
    }

    public static int updateEntity(String tableName, String column, String new_value, String old_value) {
        int nb_lignes = 0;
        Logger log = LoggerFactory.getLogger(Fournisseur.class);

        Connection db = Database.connect();

        String req = String.format("UPDATE %s SET %s=? WHERE %s=?", tableName, column, column);
        Utils.msgDebug(req);

        try (PreparedStatement pst = db.prepareStatement(req)) {
            pst.setString(1,new_value);
            pst.setString(2,old_value);
            nb_lignes = pst.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

        Database.disconnect(db);
        return nb_lignes;
    }

    public static boolean deleteEntity(String tableName, String column, String value) {
        boolean returnValue = false;
        Logger log = LoggerFactory.getLogger(Fournisseur.class);

        Connection db = Database.connect();

        String req = String.format("DELETE FROM %s WHERE %s=?", tableName, column);
        Utils.msgDebug(req);
        try (PreparedStatement pst = db.prepareStatement(req)) {
            pst.setString(1,value);
            pst.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

        Database.disconnect(db);
        return returnValue;
    }
}
