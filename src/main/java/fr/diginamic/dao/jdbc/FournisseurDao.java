package fr.diginamic.dao.jdbc;

import fr.diginamic.dao.IFournisseurDao;
import fr.diginamic.database.Database;
import fr.diginamic.entites.Fournisseur;
import fr.diginamic.mochizukiTools.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FournisseurDao implements IFournisseurDao {

    public static final String FOURNISSEUR_TABLE_NAME = "FOURNISSEURS";
    public static final String FOURNISSEUR_ID = "ID";
    public static final String FOURNISSEUR_NAME = "NOM";


    @Override
    public ArrayList<Fournisseur> extraire() {
        ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
        Logger log = LoggerFactory.getLogger(Fournisseur.class);
        Utils.msgInfo("Extraction de la liste des fournisseurs");
        Connection db = Database.connect();

        final String req = String.format("SELECT * FROM %s", FOURNISSEUR_TABLE_NAME);
        Utils.msgDebug(req);
        try (PreparedStatement pst = db.prepareStatement(req)) {
            ResultSet curseur = pst.executeQuery(req);
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

    @Override
    public void insert(Fournisseur fournisseur) {
        Database.insertEntity(FOURNISSEUR_TABLE_NAME,FOURNISSEUR_NAME, fournisseur.getName());
    }

    @Override
    public int update(String ancienNom, String nouveauNom) {
        return Database.updateEntity(FOURNISSEUR_TABLE_NAME,FOURNISSEUR_NAME,nouveauNom,ancienNom);
    }

    @Override
    public boolean delete(Fournisseur fournisseur) {
        return Database.deleteEntity(FOURNISSEUR_TABLE_NAME,FOURNISSEUR_NAME, fournisseur.getName());
    }
}