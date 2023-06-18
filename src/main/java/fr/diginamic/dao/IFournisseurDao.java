package fr.diginamic.dao;

import fr.diginamic.entites.Fournisseur;

import java.util.ArrayList;
import java.util.List;

public interface IFournisseurDao {
    ArrayList<Fournisseur> extraire();
    void insert(Fournisseur fournisseur);
    int update(String ancienNom, String nouveauNom);
    boolean delete(Fournisseur fournisseur);
}