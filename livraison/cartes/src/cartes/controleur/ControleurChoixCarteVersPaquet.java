package cartes.controleur;

import cartes.modele.*;
import cartes.vue.*;
import java.awt.event.*;

/**
 * Contrôleur gérant le déplacement d'une carte depuis une vue source vers un paquet destination
 * via un clic de souris.
 */
public class ControleurChoixCarteVersPaquet extends MouseAdapter {
    private final VuePaquetVisible vueSource;
    private final Paquet destination;

    /**
     * Initialise le contrôleur et s'abonne aux clics de souris de la vue source.
     * @param vueSource La vue affichant les cartes (doit permettre de récupérer le modèle).
     * @param destination Le paquet où la carte sera ajoutée.
     */
    public ControleurChoixCarteVersPaquet(VuePaquetVisible vueSource, Paquet destination) {
        this.vueSource = vueSource;
        this.destination = destination;
        vueSource.addMouseListener(this);
    }

    /**
     * Gère le clic souris. Calcule l'index de la carte cliquée en fonction de la position X,
     * la retire du modèle source et l'ajoute à la destination.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // Suppose une largeur de carte visible de 70px par décalage
        int index = e.getX() / 70; 
        Carte c = vueSource.getModele().retirerIndex(index);
        if (c != null) destination.ajouter(c);
    }
}