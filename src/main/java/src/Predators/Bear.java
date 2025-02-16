package src.Predators;

import src.display.Location;
import src.Herbivores.Herbivores;
import src.Animal.Animal;

public class Bear extends Predators {
    public Bear() {
        super("Bear", 500.0, 5, 2, 80.0);
    }

    @Override
    public void eat(Location location) {
        Animal prey = location.getAnimals().stream()
                .filter(a -> a instanceof Herbivores) // Убедимся, что это травоядное
                .findFirst()
                .orElse(null);

        if (prey != null) {
            location.removeAnimal(prey); // Убираем добычу из клетки
            currentFood = maxFood;      // Восстанавливаем запас еды
            System.out.println(getName() + " съел " + prey.getName() + ".");
        }
    }


    @Override
    public void reproduce(Location location) {
        long sameSpeciesCount = location.getAnimals().stream()
                .filter(a -> a.getClass().equals(this.getClass()))
                .count();

        if (sameSpeciesCount > 1) { // Если есть пара
            location.addAnimal(new Bear());
            System.out.println("Медведь размножился в клетке.");
        }
    }
}