package cartes.modele;

import java.util.*;

public class Paquet extends AbstractModeleEcoutable {
    private final List<Carte> cartes;

    public Paquet() {
        this.cartes = new ArrayList<>();
    }

    public void ajouter(Carte c) {
        cartes.add(c);
        fireChangement();
    }

    public Carte retirerPremiere() {
        if (cartes.isEmpty()) return null;
        Carte c = cartes.remove(0);
        fireChangement();
        return c;
    }

    public Carte retirerIndex(int index) {
        if (index < 0 || index >= cartes.size()) return null;
        Carte c = cartes.remove(index);
        fireChangement();
        return c;
    }

    public void melanger() {
        Collections.shuffle(cartes);
        fireChangement();
    }

    public void couper() {
        if (cartes.size() <= 2) return;
        int cut = new Random().nextInt(cartes.size() - 2) + 1;
        List<Carte> haut = new ArrayList<>(cartes.subList(0, cut));
        cartes.subList(0, cut).clear();
        cartes.addAll(haut);
        fireChangement();
    }

    public int taille() { return cartes.size(); }
    public List<Carte> getCartes() { return Collections.unmodifiableList(cartes); }
}
