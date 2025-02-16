package src.Herbivores;

import src.display.Location;

public class Sheep extends Herbivores {
    public Sheep() {
        super("Sheep", 70.0, 140, 3, 15.0);
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
            location.addAnimal(new Sheep());
            System.out.println("Овца размножился в клетке.");
        }
    }
}
