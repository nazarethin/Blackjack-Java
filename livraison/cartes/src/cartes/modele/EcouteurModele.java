package cartes.modele;

/**
 * Interface pour les écouteurs du modèle (Design Pattern Observer).
 * Doit être implémentée par les vues ou les classes souhaitant réagir aux changements d'état du modèle.
 */
public interface EcouteurModele {
    
    /**
     * Méthode appelée lorsque le modèle notifie un changement.
     * @param source L'objet modèle qui a déclenché la mise à jour.
     */
    void modeleMisAJour(Object source);
}