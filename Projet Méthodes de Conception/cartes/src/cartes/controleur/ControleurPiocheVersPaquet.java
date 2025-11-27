package cartes.controleur;

import cartes.modele.*;
import cartes.vue.*;
import java.awt.event.*;

/**
 * Contrôleur gérant le déplacement d'une carte d'une pioche vers un autre paquet.
 * S'active lors d'un clic souris.
 */
public class ControleurPiocheVersPaquet extends MouseAdapter {
    private final Paquet source;
    private final Paquet destination;

    /**
     * Constructeur.
     * @param vueSource La vue sur laquelle on clique.
     * @param destination Le paquet où la carte sera ajoutée.
     */
    public ControleurPiocheVersPaquet(VuePaquet vueSource, Paquet destination) {
        this.source = vueSource.getModele();
        this.destination = destination;
        vueSource.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Carte c = source.retirerPremiere();
        if (c != null) {
            destination.ajouter(c);
        }
    }
}