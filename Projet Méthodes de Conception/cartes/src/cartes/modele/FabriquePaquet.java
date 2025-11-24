package cartes.modele;

import java.util.*;

public class FabriquePaquet {

    private static final String[] COULEURS = {"Trèfle", "Pique", "Carreau", "Cœur"};
    private static final String[] HAUTEURS_52 = 
        {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Valet", "Dame", "Roi"};
    private static final String[] HAUTEURS_32 = 
        {"7", "8", "9", "10", "Valet", "Dame", "Roi", "As"};

    public static Paquet creerJeu52() {
        Paquet p = new Paquet();
        for (String c : COULEURS)
            for (String h : HAUTEURS_52)
                p.ajouter(new Carte(c, h));
        return p;
    }

    public static Paquet creerJeu32() {
        Paquet p = new Paquet();
        for (String c : COULEURS)
            for (String h : HAUTEURS_32)
                p.ajouter(new Carte(c, h));
        return p;
    }
}
