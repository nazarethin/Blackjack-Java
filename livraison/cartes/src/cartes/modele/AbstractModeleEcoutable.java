package cartes.modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite fournissant une implémentation de base pour la gestion des écouteurs.
 * Simplifie l'implémentation du Design Pattern Observer dans les modèles concrets.
 */
public abstract class AbstractModeleEcoutable implements ModeleEcoutable {
    private List<EcouteurModele> ecouteurs = new ArrayList<>();

    @Override
    public void ajoutEcouteur(EcouteurModele e) {
        if (!ecouteurs.contains(e)) ecouteurs.add(e);
    }

    @Override
    public void retraitEcouteur(EcouteurModele e) {
        ecouteurs.remove(e);
    }

    /**
     * Notifie tous les écouteurs enregistrés qu'un changement a eu lieu.
     * Appelle la méthode {@link EcouteurModele#modeleMisAJour(Object)} sur chaque écouteur.
     */
    protected void fireChangement() {
        for (EcouteurModele e : ecouteurs) {
            e.modeleMisAJour(this);
        }
    }
}