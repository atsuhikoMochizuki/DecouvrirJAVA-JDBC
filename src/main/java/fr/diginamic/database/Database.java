package fr.diginamic.database;

import fr.diginamic.mochizukiTools.Utils;

import java.sql.*;
import java.util.ResourceBundle;

public class Database {
    public static Connection connect() {
        String URL, USER, PWD, msg = null;
        Connection db = null;

        ResourceBundle db_conf = ResourceBundle.getBundle ("project");
        URL = db_conf.getString ("database.url");
        USER = db_conf.getString ("database.user");
        PWD = db_conf.getString ("database.pwd");

        msg = String.format ("Connection à la base de données %s", URL);
        Utils.msgInfo (msg);

        try {
            db = DriverManager.getConnection (URL, USER, PWD);
        } catch (SQLException e) {
            System.err.println (e.getMessage ());
        }
        Utils.msgResult ("OK");
        return db;
    }

    public static Connection disconnect(Connection db) {
        String msg = null;
        Utils.msgInfo ("Fermeture de la base de données");
        if (db == null) {
            Utils.beep ();
            Utils.msgWarning ("Tentative de fermeture d'une base de données non référencée en mémoire");
            return null;
        }
        try {
            db.close ();
        } catch (SQLException e) {
            throw new RuntimeException (e);
        }
        Utils.msgResult ("Fermeture OK");
        return null;
    }


    public static int insertValue(Connection db, String table, String column, String value) {
        Statement st = null;
        String msg = null;
        int nbreLines = 0;

        msg = String.format ("Enregistrement de la valeur %s dans la table %s(%s)", value, table, column);
        Utils.msgInfo (msg);

        try {
            st = db.createStatement ();
            msg = String.format ("INSERT INTO %s(%s) VALUES('%s')", table, column, value);
            nbreLines = st.executeUpdate (msg);
        } catch (SQLException e) {
            throw new RuntimeException (e);
        } finally {
            try {
                st.close ();
            } catch (SQLException e) {
                throw new RuntimeException (e);
            }
        }
        msg = String.format ("Insertion dans la table OK. Nbre de lignes insérées : %d", nbreLines);
        Utils.msgResult (msg);
        return nbreLines;
    }

    public static int listTableRows(Connection db, String table, String column) {
        Statement st = null;
        String msg = null;
        int nbreLines = 0;
        ResultSet rs = null;

        msg = String.format ("Lecture des valeurs de la colonne %s dans la table %s", column, table);
        Utils.msgInfo (msg);
        try {
            st = db.createStatement ();
        } catch (SQLException e) {
            throw new RuntimeException (e);
        }
        try {
            msg = String.format ("SELECT * FROM %s;", table);
            Utils.msgConsign (msg);
            rs = st.executeQuery (msg);
        } catch (SQLException e) {
            throw new RuntimeException (e);
        }
        try {
            while (rs.next ()) {
                System.out.println (rs.getInt (1) + "-" + rs.getString (column));
            }
        } catch (SQLException e) {
            throw new RuntimeException (e);
        }
        try {
            rs.close ();
        } catch (SQLException e) {
            throw new RuntimeException (e);
        }
        try {
            st.close ();
        } catch (SQLException e) {
            throw new RuntimeException (e);
        }

        return nbreLines;
    }

    public static int updateValue(Connection db, String table, String column, String old_value, String newValue) {
        String req = "UPDATE %s SET %s = '%s' WHERE %s = '%s'";
        String msg = null;
        Statement st = null;
        int nb = 0;
        try{
            st = db.createStatement ();
            msg = String.format (req, table, column, newValue, column,old_value);
            Utils.msgConsign (msg);
            nb = st.executeUpdate (msg);
            msg = String.format ("Nbre de lignes traitées: %d", nb);
            Utils.msgConsign (msg);
        } catch (SQLException e) {
            System.err.println (e.getMessage ());
        }
        finally {
            try {
                st.close();
            } catch (SQLException e) {
                throw new RuntimeException (e);
            }
        }
        return nb;
    }
}
