package src.Predators;

import src.Animal.Animal;
import src.Herbivores.Herbivores;
import src.Herbivores.Rabbit;
import src.display.Location;

public class Fox extends Predators {
    public Fox() {
        super("Fox", 8.0, 30, 2, 2.0);
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
            location.addAnimal(new Fox());
            System.out.println("Лиса размножился в клетке.");
        }
    }
}