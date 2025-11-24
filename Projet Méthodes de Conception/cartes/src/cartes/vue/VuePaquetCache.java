package cartes.vue;

import cartes.modele.*;

import java.awt.*;

public class VuePaquetCache extends VuePaquet {

    public VuePaquetCache(Paquet p) { super(p); }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int n = modele.taille();
        g.setColor(Color.GRAY);
        for (int i = 0; i < n; i++) {
            g.fillRect(10 + i, 10 + i, 60, 90);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(80, 120);
    }
}
