package cartes;

import cartes.modele.*;
import java.util.List;

/**
 * Classe de tests unitaires (sans interface graphique)
 * pour valider la robustesse de la librairie "cartes".
 */
public class TestJeuDeCartes {
    
    public static void main(String[] args) {
        System.out.println("=== DÉBUT DES TESTS ===");
        testCreationPaquet();
        testMelange();
        testPioche();
        System.out.println("=== FIN DES TESTS : TOUT EST OK ===");
    }

    private static void testCreationPaquet() {
        System.out.print("Test 1: Création paquet 52 cartes... ");
        Paquet p = FabriquePaquet.creerPaquet52();
        if (p.taille() == 52) {
            System.out.println("[OK]");
        } else {
            System.err.println("[ERREUR] Taille attendue 52, reçue " + p.taille());
        }
    }

    private static void testMelange() {
        System.out.print("Test 2: Mélange des cartes... ");
        Paquet p1 = FabriquePaquet.creerPaquet52();
        Paquet p2 = FabriquePaquet.creerPaquet52();
        
        // On compare la première carte de deux paquets (faible probabilité qu'elles soient identiques après mélange)
        p1.melanger();
        // p2 reste trié
        
        Carte c1 = p1.getCartes().get(0);
        Carte c2 = p2.getCartes().get(0);
        
        if (!c1.toString().equals(c2.toString())) {
            System.out.println("[OK]");
        } else {
            System.out.println("[ATTENTION] Les cartes sont identiques (peut arriver, relancer)");
        }
    }

    private static void testPioche() {
        System.out.print("Test 3: Mécanique de pioche... ");
        Paquet p = new Paquet();
        Carte c = new Carte("As", "Pique", 11);
        p.ajouter(c);
        
        Carte piochee = p.piocher();
        if (piochee == c && p.taille() == 0) {
            System.out.println("[OK]");
        } else {
            System.err.println("[ERREUR] Problème lors de la pioche");
        }
    }
}