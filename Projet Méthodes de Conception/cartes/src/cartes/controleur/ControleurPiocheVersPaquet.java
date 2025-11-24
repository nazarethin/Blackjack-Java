package cartes.controleur;

import cartes.modele.*;
import cartes.vue.*;

import java.awt.event.*;

public class ControleurPiocheVersPaquet extends MouseAdapter {
    private final Paquet source;
    private final Paquet destination;

    public ControleurPiocheVersPaquet(VuePaquet vueSource, Paquet destination) {
        this.source = vueSource.getModele();
        this.destination = destination;
        vueSource.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Carte c = source.retirerPremiere();
        if (c != null) destination.ajouter(c);
    }
}
