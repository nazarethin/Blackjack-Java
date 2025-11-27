package blackjack.modele;

/**
 * Interface du Pattern Strategy.
 * Permet de définir différents comportements pour les joueurs (IA).
 */
public interface StrategieJeu {
    /**
     * Décide si le joueur doit tirer une carte ou s'arrêter.
     * @param main La main du joueur
     * @param score Le score actuel
     * @return true si le joueur veut tirer, false s'il s'arrête
     */
    boolean doitTirer(Player joueur);
}