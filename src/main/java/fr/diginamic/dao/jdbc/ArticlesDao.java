package fr.diginamic.dao.jdbc;

import fr.diginamic.dao.IArticleDao;
import fr.diginamic.database.Database;
import fr.diginamic.entites.Articles;
import fr.diginamic.entites.Fournisseur;
import fr.diginamic.mochizukiTools.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArticlesDao implements IArticleDao {
    public static final String ARTICLES_TABLE_NAME = "ARTICLES";
    public static final String ARTICLES_ID = "ID";
    public static final String ARTICLES_REF = "REF";
    public static final String ARTICLES_DESIGNATION = "DESIGNATION";
    public static final String ARTICLES_PRIX = "PRIX";
    public static final String ARTICLES_ID_FOURNISSEUR = "ID_FOU";


    @Override
    public void insert(Articles article) {

        Logger log = LoggerFactory.getLogger(Fournisseur.class);
        Connection db = Database.connect();

        String req = String.format("INSERT INTO %s(%s,%s,%s,%s) VALUES(?,?,?,?)",
                ARTICLES_TABLE_NAME,
                ARTICLES_REF,
                ARTICLES_DESIGNATION,
                ARTICLES_PRIX,
                ARTICLES_ID_FOURNISSEUR);
        Utils.msgDebug(req);

        try (PreparedStatement pst = db.prepareStatement(req)) {
            pst.setString(1, article.getRef());
            pst.setString(2, article.getDesignation());
            pst.setDouble(3, article.getPrix());
            pst.setInt(4, article.getId_fournisseur());

            pst.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

        Database.disconnect(db);
    }

    @Override
    public ArrayList<Articles> extraire() {
        return null;
    }

    @Override
    public int update(String ancienNom, String nouveauNom) {
        return 0;
    }

    @Override
    public boolean delete(Articles article) {
        return false;
    }
}
