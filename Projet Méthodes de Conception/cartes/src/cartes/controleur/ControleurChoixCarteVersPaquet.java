package cartes.controleur;

import cartes.modele.*;
import cartes.vue.*;

import java.awt.event.*;

public class ControleurChoixCarteVersPaquet extends MouseAdapter {
    private final VuePaquetVisible vueSource;
    private final Paquet destination;

    public ControleurChoixCarteVersPaquet(VuePaquetVisible vueSource, Paquet destination) {
        this.vueSource = vueSource;
        this.destination = destination;
        vueSource.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int index = e.getX() / 70;
        Carte c = vueSource.getModele().retirerIndex(index);
        if (c != null) destination.ajouter(c);
    }
}
