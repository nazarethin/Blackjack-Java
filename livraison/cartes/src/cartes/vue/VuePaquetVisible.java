package cartes.vue;

import cartes.modele.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.HashMap;
import java.util.Map;

/**
 * Vue affichant les cartes face visible, disposées horizontalement.
 * Charge les images des cartes depuis les ressources.
 */
public class VuePaquetVisible extends VuePaquet {
    
    // Cache pour ne pas recharger les images à chaque rafraîchissement
    private static Map<String, Image> imageCache = new HashMap<>();

    /**
     * Constructeur.
     * @param p Le paquet à afficher.
     */
    public VuePaquetVisible(Paquet p) { 
        super(p); 
        this.setOpaque(false); // Fond transparent
    }

    /**
     * Dessine les cartes du paquet.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 10;
        int y = 10;
        
        for (Carte c : modele.getCartes()) {
            Image img = chargerImageCarte(c);
            if (img != null) {
                g.drawImage(img, x, y, 72, 96, this); // Dessine l'image redimensionnée
            } else {
                // Fallback si l'image manque
                g.setColor(Color.WHITE);
                g.fillRect(x, y, 72, 96);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, 72, 96);
                g.drawString(c.toString(), x + 5, y + 50);
            }
            x += 30; // Décalage pour la carte suivante
        }
    }

    /**
     * Charge l'image correspondant à une carte.
     * Protected pour permettre la surcharge par VueDealer.
     * @param c La carte à afficher.
     * @return L'image de la carte.
     */
    protected Image chargerImageCarte(Carte c) {
        String rank = translateRank(c.getValeur());
        String suit = translateSuit(c.getCouleur());
        String filename = rank + "_of_" + suit + ".png";
        
        if (!imageCache.containsKey(filename)) {
            try {
                var resource = getClass().getResource("/ressources/cards/" + filename);
                if (resource != null) {
                    imageCache.put(filename, ImageIO.read(resource));
                } else {
                    // Pas d'erreur critique, juste un log
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return imageCache.get(filename);
    }

    // Utilitaires de traduction (Français -> Anglais pour les fichiers)
    private String translateRank(String v) {
        switch(v) {
            case "As": return "ace";
            case "Roi": return "king";
            case "Dame": return "queen";
            case "Valet": return "jack";
            default: return v;
        }
    }

    private String translateSuit(String c) {
        if (c.contains("Coeur") || c.equals("♥")) return "hearts";
        if (c.contains("Pique") || c.equals("♠")) return "spades";
        if (c.contains("Carreau") || c.equals("♦")) return "diamonds";
        if (c.contains("Trèfle") || c.equals("♣")) return "clubs";
        return c;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Math.max(300, modele.taille() * 30 + 80), 120);
    }
}