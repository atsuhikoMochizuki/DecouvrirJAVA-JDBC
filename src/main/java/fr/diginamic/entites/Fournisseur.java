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

    public Fournisseur(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Fournisseur{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
