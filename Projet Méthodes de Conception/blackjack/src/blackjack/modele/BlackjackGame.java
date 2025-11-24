package blackjack.modele;

import java.util.*;
import cartes.modele.Carte;
import cartes.modele.Paquet;
import cartes.modele.FabriquePaquet;

/**
 * Logique minimale du blackjack :
 * - distribution initiale
 * - hit pour un joueur
 * - stand (on passe)
 * - calcul des totaux en traitant les As (1 ou 11)
 */
public class BlackjackGame {

    private final Paquet deck; // paquet principal (52) : on pioche depuis ici
    private final Player player;
    private final Dealer dealer;

    public BlackjackGame() {
        this.deck = FabriquePaquet.creerJeu52();
        this.deck.melanger();
        this.player = new Player("Joueur");
        this.dealer = new Dealer();
    }

    public Player getPlayer() { return player; }
    public Dealer getDealer() { return dealer; }

    /** distribution initiale : 2 cartes chacun (dealer a une carte face cachée conceptuellement) */
    public void initialDeal() {
        player.getMain().ajouter(deck.retirerPremiere());
        dealer.getMain().ajouter(deck.retirerPremiere());
        player.getMain().ajouter(deck.retirerPremiere());
        dealer.getMain().ajouter(deck.retirerPremiere());
    }

    public Carte hit(Player p) {
        Carte c = deck.retirerPremiere();
        p.receive(c);
        return c;
    }

    /** calcule le meilleur total <=21 sinon le minimal >21 */
    public static int bestTotal(List<Carte> cartes) {
        int total = 0;
        int aces = 0;
        for (Carte c : cartes) {
            String h = c.getHauteur().toLowerCase(); // "as","roi","dame","valet" ou "2".."10"
            if (h.equals("as") || h.equals("ace")) {
                aces++;
                total += 1; // compté initialement comme 1
            } else if (h.equals("roi") || h.equals("dame") || h.equals("valet")
                    || h.equals("king") || h.equals("queen") || h.equals("jack")) {
                total += 10;
            } else {
                // essayer de parser un nombre
                try {
                    total += Integer.parseInt(h);
                } catch (NumberFormatException e) {
                    // fallback : 10
                    total += 10;
                }
            }
        }
        // tenter de promouvoir certains As de 1 à 11 si possible
        for (int i = 0; i < aces; i++) {
            if (total + 10 <= 21) total += 10;
        }
        return total;
    }

    /** indique si bust */
    public static boolean isBust(List<Carte> cartes) {
        return bestTotal(cartes) > 21;
    }

    /** reset game (remet main et recrée deck mélangé) */
    public void reset() {
        // vider mains
        player.clearHand();
        dealer.clearHand();
        // recréer et mélanger le deck
        Paquet newDeck = FabriquePaquet.creerJeu52();
        newDeck.melanger();
        // remplacer cartes du deck : si Paquet n'autorise pas de réassignation,
        // on copie le contenu de newDeck dans deck (simplifié ici supposer méthode)
        // Mais si deck est immutable, on peut simplement réaffecter via réflexion :
        // pour simplicité, on utilisera le champ deck pour ajouter toutes les cartes :
        while (deck.taille() > 0) deck.retirerPremiere();
        for (Carte c : newDeck.getCartes()) deck.ajouter(c);
    }
}
