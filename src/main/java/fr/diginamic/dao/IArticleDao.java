package fr.diginamic.dao;

import fr.diginamic.entites.Articles;

import java.util.ArrayList;

public interface IArticleDao {
    void insert (Articles article);
    ArrayList<Articles> extraire();
    int update(String ancienNom, String nouveauNom);
    boolean delete (Articles article);
}
