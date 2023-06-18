package fr.diginamic.entites;

import fr.diginamic.database.Database;
import fr.diginamic.mochizukiTools.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Fournisseur {
    public static final String FOURNISSEUR_TABLE_NAME = "FOURNISSEURS";
    public static final String FOURNISSEUR_ID = "ID";
    public static final String FOURNISSEUR_NAME = "NOM";

    private int id;
    private String name;


    public Fournisseur(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Fournisseur{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static void insert(String name) {
        Logger log = LoggerFactory.getLogger(Fournisseur.class);
        Connection db = Database.connect();

        String req = String.format("INSERT INTO %s(%s) VALUES('%s')", FOURNISSEUR_TABLE_NAME, FOURNISSEUR_NAME, name);
        Utils.msgDebug(req);

        try (Statement st = db.createStatement()) {
            st.executeUpdate(req);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

        Database.disconnect(db);
    }

    public static void updateName(String old_name, String new_name) {
        Logger log = LoggerFactory.getLogger(Fournisseur.class);

        Connection db = Database.connect();

        String req = String.format("UPDATE %s SET %s='%s' WHERE %s='%s'",
                FOURNISSEUR_TABLE_NAME,
                FOURNISSEUR_NAME,
                new_name,
                FOURNISSEUR_NAME,
                old_name);
        Utils.msgDebug(req);
        try (Statement st = db.createStatement()) {
            st.executeUpdate(req);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

        Database.disconnect(db);
    }

    public static void delete(String name) {
        Logger log = LoggerFactory.getLogger(Fournisseur.class);

        Connection db = Database.connect();

        String req = String.format("DELETE FROM %s WHERE %s='%s'",
                FOURNISSEUR_TABLE_NAME,
                FOURNISSEUR_NAME,
                name);
        Utils.msgDebug(req);
        try (Statement st = db.createStatement()) {
            st.executeUpdate(req);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

        Database.disconnect(db);
    }

    public static ArrayList extraireListe() {
        ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
        Logger log = LoggerFactory.getLogger(Fournisseur.class);
        Utils.msgInfo("Extraction de la liste des fournisseurs");
        Connection db = Database.connect();

        String req = String.format("SELECT * FROM %s", FOURNISSEUR_TABLE_NAME);
        Utils.msgDebug(req);
        try (Statement st = db.createStatement()) {
            ResultSet curseur = st.executeQuery(req);
            while(curseur.next())
            {
                int id = curseur.getInt(FOURNISSEUR_ID);
                String name = curseur.getString(FOURNISSEUR_NAME);
                Fournisseur currentFournisseur = new Fournisseur(id,name);
                fournisseurs.add(currentFournisseur);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        Utils.msgInfo("Extraction terminée");

        Database.disconnect(db);

        return fournisseurs;
    }
}
