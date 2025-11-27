package blackjack.modele;

/**
 * Stratégie de base du Dealer (Règles officielles du Casino).
 * Le Dealer doit tirer tant que son score est strictement inférieur à 17.
 */
public class StrategieDealerBase implements StrategieJeu {
    
    @Override
    public boolean doitTirer(Player joueur) {
        return joueur.getScore() < 17;
    }
    // ...
}