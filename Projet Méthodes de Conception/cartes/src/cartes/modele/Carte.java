package cartes.modele;

public class Carte {
    private final String hauteur;
    private final String couleur;

    public Carte(String hauteur, String couleur) {
        this.hauteur = hauteur;
        this.couleur = couleur;
    }

    public String getHauteur() {
        return hauteur;
    }

    public String getCouleur() {
        return couleur;
    }

    @Override
    public String toString() {
        return hauteur + " de " + couleur;
    }
}

