package cartes.vue;

import cartes.modele.*;

import javax.swing.*;
import java.awt.*;

public abstract class VuePaquet extends JPanel implements EcouteurModele {
    protected Paquet modele;

    public VuePaquet(Paquet p) {
        this.modele = p;
        p.ajoutEcouteur(this);
    }
    public Paquet getModele() {
    return modele;
    }


    @Override
    public void modeleMisAJour(Object source) {
        repaint();
    }
}
