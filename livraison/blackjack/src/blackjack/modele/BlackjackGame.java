package blackjack.modele;

import cartes.modele.*;

/**
 * Classe principale gérant la logique du jeu de Blackjack (Le Modèle MVC).
 * Gère le paquet principal, les joueurs, les tours de jeu et les conditions de victoire.
 * Notifie les vues via le mécanisme ModeleEcoutable.
 */
public class BlackjackGame extends AbstractModeleEcoutable {
    private Paquet pioche;
    private final Player joueur; 
    private final Dealer dealer; 
    private boolean partieTerminee;
    private String messageEtat;

    /**
     * Constructeur. Initialise les objets joueurs et lance une première initialisation du jeu.
     */
    public BlackjackGame() {
        // Initialisation unique des objets pour que la Vue puisse conserver ses références
        this.pioche = FabriquePaquet.creerPaquet52();
        this.joueur = new Player("Joueur");
        this.dealer = new Dealer();
        
        initialiserJeu();
    }

    /**
     * Réinitialise le jeu pour une nouvelle manche.
     * Vide les mains, recrée et mélange la pioche, distribue les cartes initiales
     * et vérifie s'il y a un Blackjack d'entrée.
     */
    public void initialiserJeu() {
        // 1. Réinitialisation de l'état
        partieTerminee = false;
        messageEtat = "A vous de jouer";
        
        // 2. Remplissage et mélange de la pioche
        pioche = FabriquePaquet.creerPaquet52();
        pioche.melanger();
        
        // 3. Vidage des mains (On garde les mêmes instances de Paquet !)
        joueur.getMain().vider();
        dealer.getMain().vider();

        // 4. Distribution initiale (2 cartes chacun)
        joueur.getMain().ajouter(pioche.piocher());
        dealer.getMain().ajouter(pioche.piocher());
        joueur.getMain().ajouter(pioche.piocher());
        dealer.getMain().ajouter(pioche.piocher());
        
        checkBlackjack();
        fireChangement();
    }
    
    /**
     * Helper pour la Vue, synonyme de initialiserJeu().
     */
    public void initialDeal() {
        initialiserJeu();
    }

    /**
     * Vérifie si le joueur a obtenu un Blackjack (21 points) dès la distribution.
     */
    private void checkBlackjack() {
        if (joueur.getScore() == 21) {
            messageEtat = "Blackjack ! Vous gagnez !";
            partieTerminee = true;
        }
    }

    /**
     * Action "Tirer" (Hit). Le joueur prend une carte.
     * Si le score dépasse 21, la partie est perdue (Bust).
     */
    public void hit() {
        if (!partieTerminee) {
            Carte c = pioche.piocher();
            if(c != null) {
                joueur.getMain().ajouter(c);
                if (joueur.getScore() > 21) {
                    messageEtat = "Perdu (Bust) !";
                    partieTerminee = true;
                }
            }
            fireChangement();
        }
    }

    /**
     * Action "Rester" (Stand). Le joueur s'arrête.
     * Le dealer joue alors son tour selon sa stratégie, puis le gagnant est déterminé.
     */
    public void stand() {
        if (!partieTerminee) {
            // Le Dealer joue
            while (dealer.doitPiocher()) {
                dealer.getMain().ajouter(pioche.piocher());
            }
            determinerGagnant();
            partieTerminee = true;
            fireChangement();
        }
    }

    /**
     * Compare les scores pour déterminer le vainqueur et met à jour le message d'état.
     */
    private void determinerGagnant() {
        int scJ = joueur.getScore();
        int scD = dealer.getScore();

        if (scD > 21) messageEtat = "Dealer Bust ! Vous gagnez !";
        else if (scJ > scD) messageEtat = "Vous gagnez !";
        else if (scD > scJ) messageEtat = "Le Dealer gagne.";
        else messageEtat = "Egalité.";
    }

    // Getters
    public Player getPlayer() { return joueur; }
    public Player getJoueur() { return joueur; }
    public Dealer getDealer() { return dealer; }
    public boolean isTerminee() { return partieTerminee; }
    public String getMessage() { return messageEtat; }
}