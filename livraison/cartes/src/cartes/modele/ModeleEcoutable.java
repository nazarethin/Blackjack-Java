package cartes.modele;

/**
 * Interface définissant le comportement d'un modèle observable (Design Pattern Observer).
 * Permet aux écouteurs de s'abonner pour recevoir des notifications de mise à jour.
 */
public interface ModeleEcoutable {
    /**
     * Ajoute un écouteur à la liste des abonnés.
     * @param e L'écouteur à ajouter.
     */
    void ajoutEcouteur(EcouteurModele e);

    /**
     * Retire un écouteur de la liste des abonnés.
     * @param e L'écouteur à retirer.
     */
    void retraitEcouteur(EcouteurModele e);
}