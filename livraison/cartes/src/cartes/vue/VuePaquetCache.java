package cartes.vue;

import cartes.modele.*;
import java.awt.*;

/**
 * Vue représentant un paquet de cartes face cachée (ex: la pioche).
 * Affiche uniquement le dos des cartes empilées.
 *
 * @author Groupe Ulan
 * @version 1.0
 */
public class VuePaquetCache extends VuePaquet {

    /**
     * Construit une vue pour un paquet caché.
     *
     * @param p Le paquet modèle à représenter.
     */
    public VuePaquetCache(Paquet p) { 
        super(p); 
    }

    /**
     * Dessine le paquet.
     * Affiche un rectangle coloré pour simuler le dos des cartes.
     * Pour un effet de volume, on dessine plusieurs rectangles décalés.
     *
     * @param g Le contexte graphique.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int n = modele.taille();
        if (n > 0) {
            // Dessine jusqu'à 5 "couches" pour simuler l'épaisseur du paquet
            int maxLayers = Math.min(n, 5);
            for (int i = 0; i < maxLayers; i++) {
                g.setColor(new Color(139, 0, 0)); // Rouge foncé (dos de carte classique)
                g.fillRect(10 + i*2, 10 + i*2, 72, 96);
                g.setColor(Color.WHITE);
                g.drawRect(10 + i*2, 10 + i*2, 72, 96);
            }
        } else {
            // Dessine un emplacement vide
            g.setColor(new Color(0, 0, 0, 50)); // Ombre semi-transparente
            g.drawRect(10, 10, 72, 96);
        }
    }

    /**
     * Calcule la taille idéale du composant.
     *
     * @return Les dimensions préférées (largeur, hauteur).
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 120);
    }
}