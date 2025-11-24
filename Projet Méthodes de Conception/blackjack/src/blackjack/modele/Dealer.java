package blackjack.modele;

import cartes.modele.Carte;

/** Croupier avec stratégie simple (tirer < 17). */
public class Dealer extends Player {

    public Dealer() { super("Croupier"); }

    /** stratégie simple : tirer si total < 17 */
    public boolean shouldHit(int total) {
        return total < 17;
    }

    /** carte visible: on suppose la première (index 0) cachée, on peut afficher les autres */
}
