package cartes.modele;

/**
 * Représente une carte à jouer standard avec une valeur, une couleur et un nombre de points.
 * Cette classe est immuable pour garantir l'intégrité des données de la carte.
 */
public class Carte {
    private final String valeur;
    private final String couleur;
    private final int points;

    /**
     * Constructeur complet.
     * @param valeur La valeur faciale (ex: "ace", "10", "king").
     * @param couleur La couleur (ex: "hearts", "spades").
     * @param points La valeur en points de la carte pour le jeu.
     */
    public Carte(String valeur, String couleur, int points) {
        this.valeur = valeur;
        this.couleur = couleur;
        this.points = points;
    }

    /**
     * Constructeur simplifié (points à 0 par défaut).
     * @param valeur La valeur faciale.
     * @param couleur La couleur.
     */
    public Carte(String valeur, String couleur) {
        this(valeur, couleur, 0);
    }

    public String getValeur() { return valeur; }
    public String getCouleur() { return couleur; }
    public int getPoints() { return points; }

    /**
     * Retourne une représentation textuelle de la carte.
     * @return Une chaîne de type "valeur couleur" (ex: "ace spades").
     */
    @Override
    public String toString() {
        return valeur + " " + couleur;
    }
}