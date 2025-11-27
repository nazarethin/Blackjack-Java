package cartes.modele;

/**
 * Classe utilitaire (Fabrique) pour la création de paquets de cartes pré-configurés.
 */
public class FabriquePaquet {
    
    /**
     * Crée un paquet standard de 52 cartes.
     * Les points sont attribués selon les règles classiques du Blackjack :
     * <ul>
     * <li>Les figures (Valet, Dame, Roi) valent 10 points.</li>
     * <li>L'As vaut 11 points par défaut (la logique 1/11 est souvent gérée par le joueur).</li>
     * <li>Les cartes numériques valent leur valeur faciale.</li>
     * </ul>
     *
     * @return Un objet Paquet rempli avec 52 cartes mélangées ou ordonnées selon l'implémentation de Paquet.
     */
    public static Paquet creerPaquet52() {
        Paquet p = new Paquet();
        // Utilisation des noms anglais pour correspondre aux fichiers images
        String[] couleurs = {"hearts", "spades", "diamonds", "clubs"}; 
        String[] valeurs = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};
        
        for (String c : couleurs) {
            for (String v : valeurs) {
                int pts;
                try {
                    pts = Integer.parseInt(v);
                } catch (NumberFormatException e) {
                    if (v.equals("ace")) pts = 11;
                    else pts = 10; // jack, queen, king
                }
                p.ajouter(new Carte(v, c, pts));
            }
        }
        return p;
    }
}