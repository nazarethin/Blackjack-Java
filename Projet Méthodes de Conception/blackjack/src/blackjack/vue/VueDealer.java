package blackjack.vue;

import blackjack.modele.BlackjackGame;
import cartes.modele.Carte;
import cartes.modele.Paquet;
import cartes.vue.VuePaquetVisible;
import java.awt.*;

/**
 * Vue spécifique pour afficher la main du Dealer (Croupier).
 * Cette vue gère l'affichage de la "carte cachée" (Hole Card) tant que la partie n'est pas terminée.
 */
public class VueDealer extends VuePaquetVisible {
    private BlackjackGame game;

    /**
     * Constructeur de la vue du Dealer.
     * @param p Le paquet de cartes du dealer (sa main).
     * @param game L'instance du jeu pour vérifier l'état de la partie.
     */
    public VueDealer(Paquet p, BlackjackGame game) {
        super(p);
        this.game = game;
    }

    /**
     * Dessine les composants graphiques (les cartes).
     * Surcharge la méthode pour masquer la deuxième carte si la partie est en cours.
     * @param g Le contexte graphique.
     */
    @Override
    protected void paintComponent(Graphics g) {
        // Logique personnalisée d'affichage sans appel à super.paintComponent pour la boucle de rendu
        
        int x = 10;
        int y = 10;
        int index = 0;

        for (Carte c : modele.getCartes()) {
            // Logique : Masquer la 2ème carte (index 1) si le jeu n'est PAS terminé
            boolean isHoleCard = (index == 1) && !game.isTerminee();

            if (isHoleCard) {
                // Dessine la carte face cachée (Dos rouge)
                g.setColor(new Color(139, 0, 0)); // Rouge foncé
                g.fillRect(x, y, 72, 96);
                g.setColor(Color.WHITE);
                g.drawRect(x, y, 72, 96);
                
                // Dessine un motif simple au dos
                g.drawLine(x, y, x+72, y+96);
                g.drawLine(x+72, y, x, y+96);
            } else {
                // Dessine la carte normale en utilisant la méthode de la classe parente
                Image img = chargerImageCarte(c);
                if (img != null) {
                    g.drawImage(img, x, y, 72, 96, this);
                } else {
                    // Affichage de secours (texte)
                    g.setColor(Color.WHITE);
                    g.fillRect(x, y, 72, 96);
                    g.setColor(Color.BLACK);
                    g.drawString(c.toString(), x + 5, y + 50);
                }
            }
            x += 30;
            index++;
        }
    }
}