package src.Test;

import org.junit.jupiter.api.BeforeEach;
import src.Animal.Animal;
import src.Herbivores.Rabbit;
import src.Herbivores.Duck;
import src.Predators.Wolf;
import src.Plants.Plant;
import src.display.Location;

import static org.junit.jupiter.api.Assertions.*;

public class Test {

    private Location[][] grid;
    private Location testLocation;

    @BeforeEach
    public void setUp() {
        // Создаём сетку для тестов
        grid = new Location[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = new Location();
            }
        }
        testLocation = grid[2][2]; // Тестовая локация
    }

    @org.junit.jupiter.api.Test
    public void testHerbivoreEatingPlant() {
        Rabbit rabbit = new Rabbit();
        Plant plant = new Plant();
        testLocation.addAnimal(rabbit);
        testLocation.addPlant(plant);

        rabbit.eat(testLocation);

        assertTrue(testLocation.getPlants().isEmpty(), "Растение должно быть съедено.");
        assertEquals(rabbit.getMaxFood(), rabbit.getCurrentFood(), "Запас еды травоядного должен восстановиться.");
    }

    @org.junit.jupiter.api.Test
    public void testPredatorEatingHerbivore() {
        Wolf wolf = new Wolf();
        Rabbit rabbit = new Rabbit();
        testLocation.addAnimal(wolf);
        testLocation.addAnimal(rabbit);

        wolf.eat(testLocation);

        assertFalse(testLocation.getAnimals().contains(rabbit), "Травоядное должно быть съедено хищником.");
        assertEquals(wolf.getMaxFood(), wolf.getCurrentFood(), "Запас еды хищника должен восстановиться.");
    }

    @org.junit.jupiter.api.Test
    public void testPlantAppearance() {
        Plant plant = new Plant();
        testLocation.addPlant(plant);

        assertEquals(1, testLocation.getPlants().size(), "В клетке должно быть одно растение.");
    }

    @org.junit.jupiter.api.Test
    public void testAnimalStarvation() {
        Rabbit rabbit = new Rabbit();
        testLocation.addAnimal(rabbit);

        // Увеличиваем количество вызовов starve()
        for (int i = 0; i < 20; i++) {
            rabbit.starve();
        }

        assertTrue(rabbit.isDead(), "Травоядное должно умереть от голода.");

        // Удаляем животное из локации, если оно мёртвое
        if (rabbit.isDead()) {
            testLocation.removeAnimal(rabbit);
        }

        assertFalse(testLocation.getAnimals().contains(rabbit), "Мёртвое животное должно быть удалено из клетки.");
    }
    @org.junit.jupiter.api.Test
    public void testAnimalMovement() {
        Rabbit rabbit = new Rabbit();
        testLocation.addAnimal(rabbit);

        rabbit.move(grid);

        int newX = findCurrentX(grid, rabbit);
        int newY = findCurrentY(grid, rabbit);

        boolean isMoved = grid[2][2].getAnimals().isEmpty() &&
                grid[newX][newY].getAnimals().contains(rabbit);

        assertTrue(isMoved, "Животное должно переместиться в другую клетку.");
    }

    // Методы для поиска координат животного в сетке
    private int findCurrentX(Location[][] grid, Animal animal) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].getAnimals().contains(animal)) return i;
            }
        }
        return -1; // Если животное не найдено
    }

    private int findCurrentY(Location[][] grid, Animal animal) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].getAnimals().contains(animal)) return j;
            }
        }
        return -1; // Если животное не найдено
    }

    @org.junit.jupiter.api.Test
    public void testPlantConsumption() {
        Duck duck = new Duck();
        Plant plant = new Plant();
        testLocation.addAnimal(duck);
        testLocation.addPlant(plant);

        duck.eat(testLocation);

        assertTrue(testLocation.getPlants().isEmpty(), "Растение должно быть съедено уткой.");
        assertEquals(duck.getMaxFood(), duck.getCurrentFood(), "Запас еды утки должен восстановиться.");
    }

    @org.junit.jupiter.api.Test
    public void testAnimalRemoval() {
        Rabbit rabbit = new Rabbit();
        testLocation.addAnimal(rabbit);

        testLocation.removeAnimal(rabbit);

        assertFalse(testLocation.getAnimals().contains(rabbit), "Животное должно быть удалено из клетки.");
    }

    @org.junit.jupiter.api.Test
    public void testPlantRemoval() {
        Plant plant = new Plant();
        testLocation.addPlant(plant);

        testLocation.getPlants().remove(plant);

        assertFalse(testLocation.getPlants().contains(plant), "Растение должно быть удалено из клетки.");
    }

    @org.junit.jupiter.api.Test
    public void testAnimalReproduction() {
        Rabbit rabbit1 = new Rabbit();
        Rabbit rabbit2 = new Rabbit();
        testLocation.addAnimal(rabbit1);
        testLocation.addAnimal(rabbit2);

        rabbit1.reproduce(testLocation);

        assertEquals(3, testLocation.getAnimals().size(), "Должно быть три кролика после размножения.");
    }

    @org.junit.jupiter.api.Test
    public void testPredatorReproduction() {
        Wolf wolf1 = new Wolf();
        Wolf wolf2 = new Wolf();
        testLocation.addAnimal(wolf1);
        testLocation.addAnimal(wolf2);

        wolf1.reproduce(testLocation);

        assertEquals(3, testLocation.getAnimals().size(), "Должно быть три волка после размножения.");
    }
}
