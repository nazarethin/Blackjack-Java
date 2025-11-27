package cartes.modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Représente un paquet de cartes.
 * Cette classe peut modéliser une pioche, la main d'un joueur ou une défausse.
 * Elle hérite de {@link AbstractModeleEcoutable} pour permettre aux vues d'être notifiées
 * lors de l'ajout, du retrait ou du mélange des cartes.
 *
 * @author Groupe Ulan
 * @version 1.0
 */
public class Paquet extends AbstractModeleEcoutable {
    
    /** Liste interne des cartes contenues dans ce paquet. */
    private List<Carte> cartes;

    /**
     * Construit un nouveau paquet vide.
     */
    public Paquet() {
        this.cartes = new ArrayList<>();
    }

    /**
     * Ajoute une carte à la fin du paquet.
     * Déclenche une notification aux écouteurs (Mise à jour de la vue).
     *
     * @param c La carte à ajouter.
     */
    public void ajouter(Carte c) {
        cartes.add(c);
        fireChangement();
    }

    /**
     * Retire et retourne la première carte du paquet.
     * Cette méthode est un alias pour {@link #piocher()}.
     *
     * @return La carte retirée, ou null si le paquet est vide.
     */
    public Carte retirerPremiere() {
        return piocher();
    }
    
    /**
     * Retire et retourne la carte située à l'index spécifié.
     *
     * @param i L'index de la carte à retirer (0 est la première carte).
     * @return La carte retirée, ou null si l'index est invalide.
     */
    public Carte retirerIndex(int i) {
        if (i >= 0 && i < cartes.size()) {
            Carte c = cartes.remove(i);
            fireChangement();
            return c;
        }
        return null;
    }

    /**
     * Pioche la première carte du paquet (le dessus de la pile).
     * Retire la carte de la liste et notifie les écouteurs.
     *
     * @return La carte piochée, ou null si le paquet est vide.
     */
    public Carte piocher() {
        if (cartes.isEmpty()) return null;
        Carte c = cartes.remove(0);
        fireChangement();
        return c;
    }

    /**
     * Mélange les cartes du paquet de manière aléatoire.
     * Déclenche une notification aux écouteurs pour mettre à jour l'affichage.
     */
    public void melanger() {
        Collections.shuffle(cartes);
        fireChangement();
    }

    /**
     * Retourne une copie de la liste des cartes contenues dans le paquet.
     * Renvoie une copie pour protéger l'intégrité de la liste interne.
     *
     * @return Une nouvelle liste contenant les cartes.
     */
    public List<Carte> getCartes() {
        return new ArrayList<>(cartes);
    }
    
    /**
     * Retourne le nombre de cartes actuellement dans le paquet.
     *
     * @return La taille du paquet.
     */
    public int taille() {
        return cartes.size();
    }
    
    /**
     * Retire toutes les cartes du paquet.
     * Le paquet devient vide. Déclenche une notification.
     */
    public void vider() {
        cartes.clear();
        fireChangement();
    }
    
    /**
     * Réinitialise le paquet.
     * Actuellement un alias pour {@link #vider()}.
     * Utile sémantiquement lors du redémarrage d'une partie.
     */
    public void reset() {
        vider();
    }
}