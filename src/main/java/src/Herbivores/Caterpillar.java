package src.Herbivores;

import src.display.Location;

public class Caterpillar extends Herbivores {
    public Caterpillar() {
        super("Caterpillar", 0.01, 1000, 0, 0.0);
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
            location.addAnimal(new Caterpillar());
            System.out.println("Гусеница размножился в клетке.");
        }
    }
}


