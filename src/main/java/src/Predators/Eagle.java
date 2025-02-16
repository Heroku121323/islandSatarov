package src.Predators;

import src.Animal.Animal;
import src.Herbivores.Caterpillar;
import src.Herbivores.Mouse;
import src.Herbivores.Rabbit;
import src.display.Location;

public class Eagle extends Predators {
    public Eagle() {
        super("Eagle", 6.0, 20, 3, 1.0);
    }

    @Override
    public void eat(Location location) {
        Animal prey = location.getAnimals().stream()
                .filter(a -> a instanceof Caterpillar || a instanceof Mouse || a instanceof Rabbit) // Фильтруем только подходящую добычу
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
            location.addAnimal(new Eagle());
            System.out.println("Орел размножился в клетке.");
        }
    }
}