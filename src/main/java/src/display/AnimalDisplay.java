package src.display;

import src.Animal.Animal;
import src.Herbivores.*;
import src.Predators.*;
import src.Plants.Plant;
import src.manager.SimulationConfig;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnimalDisplay extends Application {

    private GridPane gridPane;
    private Location[][] grid;
    private final List<Animal> animals = new ArrayList<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(20); // Потоки для животных
    private final Random random = new Random();
    private final SimulationConfig config = new SimulationConfig(); // Конфигурация симуляции

    @Override
    public void start(Stage primaryStage) {
        gridPane = new GridPane();

        VBox root = new VBox();
        root.setStyle("-fx-background-color: lightgreen; -fx-padding: 10px;");
        root.getChildren().add(gridPane);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Island Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Создание начальной сетки
        grid = createExampleGrid();

        // Инициализация животных
        createAnimals();

        // Обновляем интерфейс для отображения начального состояния
        updateDisplay();

        // Запуск потоков для каждого животного
        startAnimalThreads();
    }

    /**
     * Создание начальной сетки.
     */
    private Location[][] createExampleGrid() {
        Location[][] newGrid = new Location[config.getIslandHeight()][config.getIslandWidth()];
        for (int i = 0; i < newGrid.length; i++) {
            for (int j = 0; j < newGrid[i].length; j++) {
                newGrid[i][j] = new Location();
                if (random.nextDouble() < 0.2) { // 20% вероятность появления растения
                    newGrid[i][j].addPlant(new Plant());
                }
            }
        }
        return newGrid;
    }

    /**
     * Создание животных на основе конфигурации.
     */
    private void createAnimals() {
        config.getInitialAnimalCounts().forEach((animalType, count) -> {
            for (int i = 0; i < count; i++) {
                switch (animalType) {
                    case "Wolf" -> addAnimal(new Wolf());
                    case "Deer" -> addAnimal(new Deer());
                    case "Rabbit" -> addAnimal(new Rabbit());
                    case "Bear" -> addAnimal(new Bear());
                    case "Fox" -> addAnimal(new Fox());
                    case "Duck" -> addAnimal(new Duck());
                    case "Mouse" -> addAnimal(new Mouse());
                    case "Snake" -> addAnimal(new Snake());
                    case "Goat" -> addAnimal(new Goat());
                    case "Sheep" -> addAnimal(new Sheep());
                    case "Horse" -> addAnimal(new Horse());
                    case "Bull" -> addAnimal(new Bull());
                    case "Hog" -> addAnimal(new Hog());
                    case "Cattepillar" -> addAnimal(new Caterpillar());

                }
            }
        });
    }

    /**
     * Добавление животного на карту.
     */
    private void addAnimal(Animal animal) {
        int x = random.nextInt(grid.length);
        int y = random.nextInt(grid[0].length);
        grid[x][y].addAnimal(animal);
        animals.add(animal);
    }

    /**
     * Запуск потоков для управления животными.
     */
    private void startAnimalThreads() {
        for (Animal animal : animals) {
            executor.submit(() -> {
                while (!animal.isDead()) {
                    try {
                        // Задержка между действиями животных
                        Thread.sleep(config.getTickDuration() + random.nextInt(500));

                        Platform.runLater(() -> {
                            int x = findCurrentX(grid, animal);
                            int y = findCurrentY(grid, animal);

                            if (x == -1 || y == -1) {
                                return; // Животное не найдено на карте
                            }

                            animal.move(grid);
                            animal.eat(grid[x][y]);
                            if (random.nextDouble() < 0.15) { // 15% шанс на размножение
                                animal.reproduce(grid[x][y]);
                            }
                            updateDisplay();

                            System.out.println(animal.getName() + " сейчас в клетке (" + x + ", " + y + ").");

                            if (config.isStopWhenNoAnimalsLeft() && animals.stream().noneMatch(a -> !a.isDead())) {
                                System.out.println("Все животные вымерли. Симуляция остановлена.");
                                executor.shutdownNow();
                            }
                        });
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }
    }

    /**
     * Обновление интерфейса.
     */
    public void updateDisplay() {
        gridPane.getChildren().clear();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                VBox cell = new VBox();

                // Фон клетки
                Rectangle background = new Rectangle(80, 80);
                background.setFill(Color.LIGHTGREEN);

                // Содержимое клетки (Юникод-смайлы)
                Label content = new Label();
                StringBuilder symbols = new StringBuilder();

                // Добавляем животных
                for (Animal animal : grid[i][j].getAnimals()) {
                    symbols.append(animal.getSymbol()).append(" ");
                }

                // Добавляем растения
                for (Plant plant : grid[i][j].getPlants()) {
                    symbols.append(plant.getSymbol()).append(" ");
                }

                content.setText(symbols.toString());
                cell.getChildren().addAll(background, content);
                gridPane.add(cell, j, i);
            }
        }
    }

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

    @Override
    public void stop() throws Exception {
        super.stop();
        executor.shutdownNow(); // Останавливаем потоки при закрытии приложения
    }

    public static void main(String[] args) {
        launch(args);
    }
}
