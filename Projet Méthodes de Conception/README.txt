POUR DEMARRER LE JEU TAPEZ:
ant run
------------------------------------
POUR DEMARRER LES TESTS TAPEZ:
ant test


<<<DEMARRAGE AVEC LGINES DE COMMANDES:

Compialtion librarie:
javac -d build/classes cartes/src/cartes/*.java cartes/src/cartes/modele/*.java cartes/src/cartes/vue/*.java cartes/src/cartes/controleur/*.java


Compilation blackjack:
javac -d build/classes cartes/src/cartes/*.java cartes/src/cartes/modele/*.java cartes/src/cartes/vue/*.java cartes/src/cartes/controleur/*.java

Passer les tests:
java -cp build/classes cartes.TestJeuDeCartes

Execution du jeu:
java -cp build/classes blackjack.vue.SwingBlackjack
