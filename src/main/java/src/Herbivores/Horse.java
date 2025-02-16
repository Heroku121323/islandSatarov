package src.Herbivores;

import src.display.Location;

public class Horse extends Herbivores {
    public Horse() {
        super("Horse", 400.0, 20, 4, 60.0);
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
            location.addAnimal(new Horse());
            System.out.println("Лошадь размножился в клетке.");
        }
    }
}
