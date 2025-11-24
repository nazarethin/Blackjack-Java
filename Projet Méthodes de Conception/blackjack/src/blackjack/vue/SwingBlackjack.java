package blackjack.vue;

import cartes.modele.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import cartes.modele.Carte;
import blackjack.modele.*;

public class SwingBlackjack extends JFrame {

    private final BlackjackGame game;
    private final JPanel playerPanel;
    private final JPanel dealerPanel;
    private final JLabel statusLabel;
    private final JButton hitBtn;
    private final JButton standBtn;
    private final JButton newGameBtn;

    public SwingBlackjack() {
        super("Blackjack");
        game = new BlackjackGame();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // North: dealer
        dealerPanel = new JPanel();
        dealerPanel.setBorder(BorderFactory.createTitledBorder("Croupier"));
        add(dealerPanel, BorderLayout.NORTH);

        // Center: player
        playerPanel = new JPanel();
        playerPanel.setBorder(BorderFactory.createTitledBorder("Joueur"));
        add(playerPanel, BorderLayout.CENTER);

        // South: controls
        JPanel control = new JPanel();
        hitBtn = new JButton("Hit");
        standBtn = new JButton("Stand");
        newGameBtn = new JButton("Nouvelle partie");
        statusLabel = new JLabel("Prêt");

        control.add(hitBtn);
        control.add(standBtn);
        control.add(newGameBtn);
        control.add(statusLabel);
        add(control, BorderLayout.SOUTH);

        // Listeners
        hitBtn.addActionListener(e -> doHit());
        standBtn.addActionListener(e -> doStand());
        newGameBtn.addActionListener(e -> startGame());

        pack();
        setSize(800, 600);
        setLocationRelativeTo(null);
        startGame();
        setVisible(true);
    }

    private void startGame() {
        game.reset();
        game.initialDeal();
        refreshUI();
        statusLabel.setText("Votre tour");
        hitBtn.setEnabled(true);
        standBtn.setEnabled(true);
    }

    private void doHit() {
        game.hit(game.getPlayer());
        refreshUI();
        if (BlackjackGame.isBust(game.getPlayer().getCartes())) {
            statusLabel.setText("Vous avez dépassé 21 — Perdu");
            hitBtn.setEnabled(false);
            standBtn.setEnabled(false);
        }
    }

    private void doStand() {
        hitBtn.setEnabled(false);
        standBtn.setEnabled(false);
        // tour du croupier simple
        while (game.getDealer().shouldHit(BlackjackGame.bestTotal(game.getDealer().getCartes()))) {
            game.hit(game.getDealer());
            refreshUI();
            try { Thread.sleep(600); } catch (InterruptedException ignored) {}
        }
        int playerTotal = BlackjackGame.bestTotal(game.getPlayer().getCartes());
        int dealerTotal = BlackjackGame.bestTotal(game.getDealer().getCartes());
        String result;
        if (BlackjackGame.isBust(game.getDealer().getCartes())) {
            result = "Croupier a dépassé 21 — Vous gagnez !";
        } else if (playerTotal > dealerTotal) {
            result = "Vous gagnez (" + playerTotal + " vs " + dealerTotal + ")";
        } else if (playerTotal < dealerTotal) {
            result = "Vous perdez (" + playerTotal + " vs " + dealerTotal + ")";
        } else {
            result = "Égalité (" + playerTotal + " vs " + dealerTotal + ")";
        }
        statusLabel.setText(result);
    }

    /** Actualise l'affichage des mains en chargeant des images si présentes. */
    private void refreshUI() {
        updatePanel(dealerPanel, game.getDealer().getCartes(), true);
        updatePanel(playerPanel, game.getPlayer().getCartes(), false);
        revalidate();
        repaint();
    }

    /** helper: affiche une liste de cartes dans un panel. Si hideFirst true => cache la 1ère carte du croupier */
    private void updatePanel(JPanel panel, List<Carte> cartes, boolean hideFirst) {
        panel.removeAll();
        for (int i = 0; i < cartes.size(); i++) {
            Carte c = cartes.get(i);
            JLabel lbl = new JLabel();
            ImageIcon icon = loadCardImage(c);
            if (icon != null) {
                lbl.setIcon(icon);
            } else {
                String text = "[" + c.getHauteur() + " " + c.getCouleur() + "]";
                lbl.setText(text);
            }
            // cacher la première du croupier si demandé
            if (hideFirst && i == 0) {
                lbl.setIcon(null);
                lbl.setText("[cachee]");
            }
            panel.add(lbl);
        }
    }

    /** charge une image depuis resources/cards/nom.png selon la carte.
     * Nommage attendu: hauteur_couleur.png ex: as_coeur.png, 10_pique.png, roi_coeur.png
     */
    private ImageIcon loadCardImage(Carte c) {
        String h = c.getHauteur().toLowerCase().replaceAll("é","e").replaceAll("ê","e");
        String col = c.getCouleur().toLowerCase().replaceAll("é","e");
        String name = h + "_" + col + ".png";
        String path = "/cards/" + name; // sera cherché dans classpath resources
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            ImageIcon ic = new ImageIcon(imgURL);
            Image scaled = ic.getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingBlackjack::new);
    }
}
