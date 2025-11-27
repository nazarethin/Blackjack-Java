package cartes.vue;

import cartes.modele.*;
import javax.swing.*;
import java.awt.*;

/**
 * Classe abstraite de base pour toutes les vues représentant un paquet de cartes.
 * S'abonne au modèle pour recevoir les mises à jour.
 */
public abstract class VuePaquet extends JPanel implements EcouteurModele {
    /** Le modèle (paquet) observé par cette vue. */
    protected Paquet modele;

    /**
     * Constructeur.
     * @param p Le paquet à afficher.
     */
    public VuePaquet(Paquet p) {
        this.modele = p;
        p.ajoutEcouteur(this);
    }

    /**
     * Retourne le modèle associé à cette vue.
     * @return Le paquet.
     */
    public Paquet getModele() {
        return modele;
    }

    /**
     * Appelé lors d'un changement dans le paquet.
     * Provoque un redessin du composant.
     */
    @Override
    public void modeleMisAJour(Object source) {
        repaint();
    }
}