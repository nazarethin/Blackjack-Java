package blackjack.modele;

/**
 * Représente le Dealer (Croupier).
 * Hérite de Player et utilise le Design Pattern Strategy pour déterminer s'il doit piocher.
 */
public class Dealer extends Player {
    private StrategieJeu strategie;

    /**
     * Initialise le Dealer avec la stratégie de base du casino (tirer jusqu'à 17).
     */
    public Dealer() {
        super("Dealer");
        // Par défaut, on utilise la stratégie classique
        this.strategie = new StrategieDealerBase();
    }
    
    /**
     * Permet de changer l'intelligence artificielle du Dealer dynamiquement.
     * C'est la force du Pattern Strategy.
     * @param nouvelleStrategie La nouvelle stratégie à appliquer.
     */
    public void setStrategie(StrategieJeu nouvelleStrategie) {
        this.strategie = nouvelleStrategie;
    }
    
    /**
     * Détermine si le Dealer doit piocher une nouvelle carte.
     * @return true si la stratégie actuelle dicte de tirer, false sinon.
     */
    public boolean doitPiocher() {
        // Délègue la décision à l'objet stratégie
        return strategie.doitTirer(this);
    }
}