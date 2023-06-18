package fr.diginamic;

import fr.diginamic.entites.Fournisseur;
import fr.diginamic.mochizukiTools.Params;
import fr.diginamic.mochizukiTools.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {
        Connection db = null;
        Utils.clearConsole();
        Params.welcomePrompt();

        Utils.msgTitle("TP1 : Se connecter à une base de données locale");

        final String URL = "jdbc:mysql://localhost:3306/DecouvrirJDBC_Compta";
        final String USER = "root";
        final String PWD = "root";

        Utils.msgInfo("Connection à la base de données");
        try(Connection db1 = DriverManager.getConnection(URL,USER,PWD))
        {
            Utils.msgInfo("Connection à la base de données OK");
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }

        Utils.msgTitle("TP2 : Créer un fichier de configuration pour l'accès aux données");

        Utils.msgInfo("Import des propriétés de connection dans le fichiers properties");
        ResourceBundle project = ResourceBundle.getBundle("project");
        String URL_BY_PROP = project.getString("database.url");
        String USER_BY_PROP = project.getString("database.user");
        String PWD_BY_PROP = project.getString("database.pwd");
        Utils.msgInfo("Import des ressources OK");

        Utils.msgInfo("Connection à la base de données distante (Clever-cloud)");

        //On utilise ici le bloc finally
        try
        {
            db = DriverManager.getConnection(
                    URL_BY_PROP,
                    USER_BY_PROP,
                    PWD_BY_PROP);
            Utils.msgInfo("Connection à la base de données OK");
        }
        catch (SQLException accessError){
            LOG.error(accessError.getMessage());
            throw new RuntimeException(accessError);
        }
        finally
        {
            try {
                db.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        Utils.msgTitle("TP3: Opérations CRUD");
        Fournisseur.insert("La maison de la peinture");
        Fournisseur.updateName("La maison de la peinture", "La maison des peintures");
        Fournisseur.delete("La maison des peintures");
        ArrayList<Fournisseur> fournisseurs = Fournisseur.extraireListe();
        Utils.msgInfo("Affichage de la liste:");
        for(Fournisseur f: fournisseurs)
            Utils.msgResult(f.toString());
    }



}