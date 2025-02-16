package src.display;

import src.Animal.Animal;
import src.Plants.Plant;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private final List<Animal> animals = new ArrayList<>();
    private final List<Plant> plants = new ArrayList<>();

    // Возвращает список животных в клетке
    public List<Animal> getAnimals() {
        return animals;
    }

    // Добавляет животное в клетку с проверкой ограничения
    public void addAnimal(Animal animal) {
        long count = animals.stream().filter(a -> a.getClass().equals(animal.getClass())).count();
        if (count < animal.getMaxCountPerLocation()) {
            animals.add(animal);
        } else {
            System.out.println("Превышено количество животных в клетке для " + animal.getName());
        }
    }

    // Удаляет животное из клетки
    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    // Возвращает список растений в клетке
    public List<Plant> getPlants() {
        return plants;
    }

    // Добавляет растение в клетку
    public void addPlant(Plant plant) {
        plants.add(plant);
    }
}
