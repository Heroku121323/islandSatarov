package src.Herbivores;

import src.display.Location;

public class Deer extends Herbivores {
    public Deer() {
        super("Deer", 300.0, 20, 4, 50.0);
    }

    @Override
    public void eat(Location location) {
        if (!location.getPlants().isEmpty()) {
            location.getPlants().remove(0); // Убираем растение из клетки
            currentFood = maxFood; // Восстанавливаем запас пищи
            System.out.println(getName() + " съел растение.");
        }
    }

    @Override
    public void reproduce(Location location) {
        long sameSpeciesCount = location.getAnimals().stream()
                .filter(a -> a.getClass().equals(this.getClass()))
                .count();

        if (sameSpeciesCount > 1) { // Если есть пара
            location.addAnimal(new Deer());
            System.out.println("Олень размножился в клетке.");
        }
    }
}