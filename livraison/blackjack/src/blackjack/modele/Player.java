package blackjack.modele;

import cartes.modele.Paquet;
import cartes.modele.Carte;

/**
 * Représente un joueur de Blackjack générique.
 * Gère la main du joueur et le calcul du score (incluant la gestion des As).
 */
public class Player {
    protected Paquet main;
    protected String nom;

    /**
     * Initialise un joueur avec un nom et une main vide.
     * @param nom Le nom du joueur.
     */
    public Player(String nom) {
        this.nom = nom;
        this.main = new Paquet();
    }

    public Paquet getMain() {
        return main;
    }

    /**
     * Calcule le score actuel de la main du joueur selon les règles du Blackjack.
     * Gère automatiquement la valeur de l'As (11 ou 1) pour maximiser le score sans dépasser 21.
     * @return Le score entier optimal.
     */
    public int getScore() {
        int score = 0;
        int as = 0;
        for (Carte c : main.getCartes()) {
            score += c.getPoints();
            if (c.getValeur().equals("ace")) as++;
        }
        // Ajustement des As (11 -> 1) si le score dépasse 21
        while (score > 21 && as > 0) {
            score -= 10;
            as--;
        }
        return score;
    }
}