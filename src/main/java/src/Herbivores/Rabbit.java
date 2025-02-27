package src.Herbivores;

import src.Animal.Animal;
import src.display.Location;

public class Rabbit extends Herbivores {
    public Rabbit() {
        super("Rabbit", 2.0, 150, 2, 0.45);
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
            location.addAnimal(new Rabbit());
            System.out.println("Кролик размножился в клетке.");
        }
    }
}
