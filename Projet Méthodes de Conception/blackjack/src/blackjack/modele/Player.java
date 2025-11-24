package blackjack.modele;

import java.util.*;
import cartes.modele.Carte;
import cartes.modele.Paquet;

/**
 * Représente un joueur (ou la main d'un joueur).
 * Stocke son paquet (sa main) et un nom.
 */
public class Player {
    private final String name;
    private final Paquet main;

    public Player(String name) {
        this.name = name;
        this.main = new Paquet();
    }

    public String getName() { return name; }
    public Paquet getMain() { return main; }

    public void receive(Carte c) { main.ajouter(c); }

    public List<Carte> getCartes() { return main.getCartes(); }

    public void clearHand() {
        // retirer toutes les cartes : on reconstruit un Paquet vide
        // (Paquet doit fournir des méthodes ; si non, on peut retirer par index)
        // Ici on recrée un new Paquet() en réassignant n'est pas possible => retirer toutes
        while (main.taille() > 0) {
            main.retirerPremiere();
        }
    }
}
