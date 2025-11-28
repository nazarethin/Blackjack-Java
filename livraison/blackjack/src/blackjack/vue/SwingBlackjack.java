package blackjack.vue;

import blackjack.modele.BlackjackGame;
import cartes.modele.EcouteurModele;
import cartes.vue.VuePaquetVisible;

import javax.swing.*;
import java.awt.*;

/**
 * Fenêtre principale de l'application Blackjack (Vue principale).
 * Implémente EcouteurModele pour se mettre à jour lors des changements du jeu.
 *
 * @author Groupe Ulan
 * @version 1.0
 */
public class SwingBlackjack extends JFrame implements EcouteurModele {
    private BlackjackGame game;
    private VuePaquetVisible vueJoueur;
    private VueDealer vueDealer;
    private JLabel lblStatus;
    private JButton btnHit, btnStand, btnReset;

    /**
     * Constructeur : Initialise l'interface graphique et le jeu.
     */
    public SwingBlackjack() {
        super("Blackjack MVC");
        game = new BlackjackGame();
        game.ajoutEcouteur(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700);
        this.setLayout(new BorderLayout());

        // --- Zone de jeu (Table) ---
        JPanel pnlTable = new JPanel(new GridLayout(2, 1));
        pnlTable.setBackground(new Color(35, 100, 50)); // Tapis vert
        
        // Zone Dealer
        JPanel pnlDealer = new JPanel(new BorderLayout());
        pnlDealer.setOpaque(false);
        JLabel lblDealer = new JLabel("CROUPIER", SwingConstants.CENTER);
        lblDealer.setForeground(Color.WHITE);
        lblDealer.setFont(new Font("Arial", Font.BOLD, 20));
        pnlDealer.add(lblDealer, BorderLayout.NORTH);
        
        vueDealer = new VueDealer(game.getDealer().getMain(), game);
        pnlDealer.add(vueDealer, BorderLayout.CENTER);
        
        // Zone Joueur
        JPanel pnlJoueur = new JPanel(new BorderLayout());
        pnlJoueur.setOpaque(false);
        JLabel lblJoueur = new JLabel("JOUEUR", SwingConstants.CENTER);
        lblJoueur.setForeground(Color.WHITE);
        lblJoueur.setFont(new Font("Arial", Font.BOLD, 20));
        pnlJoueur.add(lblJoueur, BorderLayout.NORTH);
        
        vueJoueur = new VuePaquetVisible(game.getPlayer().getMain());
        pnlJoueur.add(vueJoueur, BorderLayout.CENTER);

        pnlTable.add(pnlDealer);
        pnlTable.add(pnlJoueur);
        this.add(pnlTable, BorderLayout.CENTER);

        // --- Zone de contrôles ---
        JPanel pnlControls = new JPanel();
        pnlControls.setBackground(new Color(50, 50, 50));
        
        btnHit = new JButton("TIRER");
        btnStand = new JButton("RESTER");
        btnReset = new JButton("NOUVELLE PARTIE");
        
        lblStatus = new JLabel(game.getMessage());
        lblStatus.setForeground(Color.WHITE);
        lblStatus.setFont(new Font("Arial", Font.BOLD, 18));

        btnHit.addActionListener(e -> game.hit());
        btnStand.addActionListener(e -> game.stand());
        btnReset.addActionListener(e -> game.initialiserJeu());

        pnlControls.add(lblStatus);
        pnlControls.add(Box.createHorizontalStrut(20));
        pnlControls.add(btnHit);
        pnlControls.add(btnStand);
        pnlControls.add(btnReset);

        this.add(pnlControls, BorderLayout.SOUTH);

        this.setVisible(true);
        modeleMisAJour(game); // Mise à jour initiale
    }

    /**
     * Méthode appelée par le modèle quand l'état du jeu change.
     * Met à jour les scores, les boutons et redessine les cartes.
     * @param source Le modèle ayant déclenché la mise à jour.
     */
    @Override
    public void modeleMisAJour(Object source) {
        lblStatus.setText(game.getMessage() + " (Score: " + game.getPlayer().getScore() + ")");
        
        if (game.isTerminee()) {
            btnHit.setEnabled(false);
            btnStand.setEnabled(false);
            btnReset.setEnabled(true);
        } else {
            btnHit.setEnabled(true);
            btnStand.setEnabled(true);
            btnReset.setEnabled(false);
        }
        
        vueJoueur.repaint();
        vueDealer.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SwingBlackjack());
    }
}