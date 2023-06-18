package fr.diginamic;

import fr.diginamic.dao.IFournisseurDao;
import fr.diginamic.dao.jdbc.FournisseurDao;
import fr.diginamic.entites.Fournisseur;
import fr.diginamic.mochizukiTools.Params;
import fr.diginamic.mochizukiTools.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.ArrayList;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {
        Connection db = null;
        Utils.clearConsole();
        Params.welcomePrompt();

//        Utils.msgTitle("TP1 : Se connecter à une base de données locale");
//
//        final String URL = "jdbc:mysql://localhost:3306/DecouvrirJDBC_Compta";
//        final String USER = "root";
//        final String PWD = "root";
//
//        Utils.msgInfo("Connection à la base de données");
//        try(Connection db1 = DriverManager.getConnection(URL,USER,PWD))
//        {
//            Utils.msgInfo("Connection à la base de données OK");
//        } catch (SQLException e) {
//            LOG.error(e.getMessage());
//            throw new RuntimeException(e);
//        }
//
//        Utils.msgTitle("TP2 : Créer un fichier de configuration pour l'accès aux données");
//
//        Utils.msgInfo("Import des propriétés de connection dans le fichiers properties");
//        ResourceBundle project = ResourceBundle.getBundle("project");
//        String URL_BY_PROP = project.getString("database.url");
//        String USER_BY_PROP = project.getString("database.user");
//        String PWD_BY_PROP = project.getString("database.pwd");
//        Utils.msgInfo("Import des ressources OK");
//
//        Utils.msgInfo("Connection à la base de données distante (Clever-cloud)");
//
//        //On utilise ici le bloc finally
//        try
//        {
//            db = DriverManager.getConnection(
//                    URL_BY_PROP,
//                    USER_BY_PROP,
//                    PWD_BY_PROP);
//            Utils.msgInfo("Connection à la base de données OK");
//        }
//        catch (SQLException accessError){
//            LOG.error(accessError.getMessage());
//            throw new RuntimeException(accessError);
//        }
//        finally
//        {
//            try {
//                db.close();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }

//        Utils.msgTitle("TP3: Opérations CRUD");
//        Database.insertEntity(Fournisseur.FOURNISSEUR_TABLE_NAME, Fournisseur.FOURNISSEUR_NAME, "La maison de la peinture");
//        Database.updateEntity(Fournisseur.FOURNISSEUR_TABLE_NAME, Fournisseur.FOURNISSEUR_NAME, "La maison des peintures", "La maison de la peinture");
//        Database.deleteEntity(Fournisseur.FOURNISSEUR_TABLE_NAME,Fournisseur.FOURNISSEUR_NAME,"La maison des peintures");
//
//        ArrayList<Fournisseur> fournisseurs = Fournisseur.extraireListe();
//        Utils.msgInfo("Affichage de la liste:");
//        for(Fournisseur f: fournisseurs)
//            Utils.msgResult(f.toString());

        Utils.msgTitle("TP4: Mise en place de DAO");
        IFournisseurDao dao= new FournisseurDao();
        Utils.msgConsign("Extraction de la liste des fournisseeurs");
        ArrayList<Fournisseur> fournisseurs =  dao.extraire();
        Utils.msgInfo("Rafraichissement de la listes des fournisseurs");
        for(Fournisseur fournisseur:fournisseurs)
            System.out.println(fournisseur.toString());

        Utils.msgConsign("Insertion d'un nouveau fournisseur");
        Fournisseur fournisseurAinserer = new Fournisseur("CASTORAMA");
        dao.insert(fournisseurAinserer);
        fournisseurs = dao.extraire();
        Utils.msgInfo("Rafraichissement de la liste:");
        for(Fournisseur fournisseur:fournisseurs)
            System.out.println(fournisseur.toString());

        Utils.msgConsign("Modification d'un fournisseur");
        dao.update("CASTORAMA", "LEROY MERLIN");
        fournisseurs = dao.extraire();
        Utils.msgInfo("Rafraichissement de la liste:");
        for(Fournisseur fournisseur:fournisseurs)
            System.out.println(fournisseur.toString());

        Utils.msgConsign("Suppression d'un fournisseur");
        Fournisseur fournisseurAsupprimer = new Fournisseur("BUT");
        dao.insert(fournisseurAsupprimer);
        dao.delete(fournisseurAsupprimer);

        Utils.msgConsign("Insertion d'un fournisseur dont le nom contient un simple quote");
        Fournisseur fournisseurChelou = new Fournisseur("L''espace Création");
        dao.insert(fournisseurChelou);
        fournisseurs = dao.extraire();
        Utils.msgInfo("Rafraichissement de la liste:");
        for(Fournisseur fournisseur:fournisseurs)
            System.out.println(fournisseur.toString());

        Utils.msgTitle("TP5 : Utilisation des PreraredStatements");
        Utils.msgConsign("Extraction de la liste des fournisseurs");
        fournisseurs = dao.extraire();
        Utils.msgInfo("Rafraichissement de la liste:");
        for(Fournisseur fournisseur:fournisseurs)
            System.out.println(fournisseur.toString());

        Utils.msgConsign("Insertion d'un nouveau fournisseur");
        Fournisseur fournisseur3 = new Fournisseur("AUCHAN");
        dao.insert(fournisseur3);
        Utils.msgInfo("Rafraichissement de la liste:");
        fournisseurs = dao.extraire();
        for(Fournisseur fournisseur:fournisseurs)
            System.out.println(fournisseur.toString());

        Utils.msgConsign("Modification d'un fournisseur");
        dao.update("AUCHAN","CARREFOUR");
        Utils.msgInfo("Rafraichissement de la liste:");
        fournisseurs = dao.extraire();
        for(Fournisseur fournisseur:fournisseurs)
            System.out.println(fournisseur.toString());

        Utils.msgConsign("Suppression d'un fournisseur");
        Fournisseur fournisseur4 = new Fournisseur("SUPER U");
        dao.insert(fournisseur4);
        Utils.msgInfo("Rafraichissement de la liste:");
        fournisseurs = dao.extraire();
        for(Fournisseur fournisseur:fournisseurs)
            System.out.println(fournisseur.toString());
        dao.delete(fournisseur4);
        Utils.msgInfo("Rafraichissement de la liste:");
        fournisseurs = dao.extraire();
        for(Fournisseur fournisseur:fournisseurs)
            System.out.println(fournisseur.toString());


        Utils.msgConsign("Insertion d'un fournisseur avec un simple quote");
        Fournisseur fournisseur5 = new Fournisseur("L'espace Création");
        dao.insert(fournisseur5);
        Utils.msgInfo("Rafraichissement de la liste:");
        fournisseurs = dao.extraire();
        for(Fournisseur fournisseur:fournisseurs)
            System.out.println(fournisseur.toString());


    }
}