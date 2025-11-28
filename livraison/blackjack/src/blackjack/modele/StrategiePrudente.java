package blackjack.modele;

/**
 * Stratégie d'IA prudente.
 * Le joueur (ou l'IA) s'arrête de tirer dès qu'il atteint ou dépasse 15 points.
 */
public class StrategiePrudente implements StrategieJeu {
    
    @Override
    public boolean doitTirer(Player joueur) {
        return joueur.getScore() < 15;
    }
}