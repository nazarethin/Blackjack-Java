========================================================================
PROJET MÉTHODES DE CONCEPTION : BIBLIOTHÈQUE CARTES & JEU BLACKJACK
========================================================================
Auteur : Nazar ULAN (num.etu: 22111766 )
Groupe: TP2B
Enseignant: Guillaume LETELLIER

DESCRIPTION
-----------
Ce projet implémente une librairie générique de jeux de cartes (MVC) et 
une application de Blackjack utilisant cette librairie.

PRÉREQUIS
---------
- Java JDK (Version 11 ou supérieure recommandée)
- Apache Ant

COMMANDES DE COMPILATION ET D'EXÉCUTION (VIA ANT)
-------------------------------------------------
Placez-vous à la racine du dossier (là où se trouve le fichier build.xml).

1. COMPILATION TOTALE (Génère les JARs dans /dist)
   Commande : ant
   (Cette commande compile la librairie, l'application, et copie les ressources)

2. LANCER LE JEU
   Commande : ant run

3. LANCER LES TESTS UNITAIRES (Librairie Cartes)
   Commande : ant test
   (Vérifie la création de paquet, le mélange et la pioche)

4. GÉNÉRER LA JAVADOC
   Commande : ant javadoc
   (La documentation sera générée dans le dossier /doc)

5. NETTOYER LE PROJET
   Commande : ant clean

EXÉCUTION MANUELLE (SANS ANT)
-----------------------------
Si vous souhaitez lancer le JAR directement après compilation :
   java -jar dist/blackjack.jar

CONTENU DE L'ARCHIVE
--------------------
/src        : Codes sources (blackjack et cartes)
/dist       : Exécutables (.jar)
/doc        : Documentation technique (Javadoc)
/rapport    : Rapport de conception (PDF)
/ressources : Images des cartes
build.xml   : Script d'automatisation de build
