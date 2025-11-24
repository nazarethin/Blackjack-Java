package cartes.vue;

import cartes.modele.*;

import java.awt.*;

public class VuePaquetVisible extends VuePaquet {

    public VuePaquetVisible(Paquet p) { super(p); }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 10;
        for (Carte c : modele.getCartes()) {
            g.setColor(Color.WHITE);
            g.fillRect(x, 10, 60, 90);
            g.setColor(Color.BLACK);
            g.drawRect(x, 10, 60, 90);
            g.drawString(c.getHauteur(), x + 10, 40);
            g.drawString(c.getCouleur(), x + 10, 60);
            x += 70;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(modele.taille() * 70 + 20, 120);
    }
}
