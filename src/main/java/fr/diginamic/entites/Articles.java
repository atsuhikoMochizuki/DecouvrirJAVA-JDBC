package fr.diginamic.entites;

public class Articles {
    private int id;
    private String ref;
    private String designation;
    private double prix;
    private int id_fournisseur;

    public Articles(int id, String ref, String designation, double prix, int id_fournisseur) {
        this.id = id;
        this.ref = ref;
        this.designation = designation;
        this.prix = prix;
        this.id_fournisseur = id_fournisseur;
    }

    public Articles(String ref, String designation, double prix, int id_fournisseur) {
        this.ref = ref;
        this.designation = designation;
        this.prix = prix;
        this.id_fournisseur = id_fournisseur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getId_fournisseur() {
        return id_fournisseur;
    }

    public void setId_fournisseur(int id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
    }

    @Override
    public String toString() {
        return "Articles{" +
                "id=" + id +
                ", ref='" + ref + '\'' +
                ", designation='" + designation + '\'' +
                ", prix=" + prix +
                ", id_fournisseur=" + id_fournisseur +
                '}';
    }
}
