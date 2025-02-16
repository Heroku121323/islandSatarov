package src.manager;

import java.util.HashMap;
import java.util.Map;

public class SimulationConfig {

    // Размер острова
    private int islandWidth = 10;
    private int islandHeight = 10;

    // Длительность такта симуляции (в миллисекундах)
    private int tickDuration = 1000;

    // Количество животных каждого вида на старте
    private Map<String, Integer> initialAnimalCounts = new HashMap<>();

    // Максимальное количество растений/животных в одной клетке
    private int maxPlantsPerCell = 5;
    private int maxAnimalsPerCell = 10;

    // Условие остановки симуляции (по умолчанию, если все животные умерли)
    private boolean stopWhenNoAnimalsLeft = true;

    // Количество детёнышей у каждого вида животных
    private Map<String, Integer> offspringCounts = new HashMap<>();

    // Вероятность передвижения животного (в процентах, от 0 до 100)
    private Map<String, Integer> movementProbabilities = new HashMap<>();

    public SimulationConfig() {
        // Устанавливаем параметры по умолчанию

        // Количество животных на старте
        initialAnimalCounts.put("Wolf", 5);
        initialAnimalCounts.put("Rabbit", 10);
        initialAnimalCounts.put("Deer", 10);
        initialAnimalCounts.put("Bear", 2);
        initialAnimalCounts.put("Fox", 3);
        initialAnimalCounts.put("Duck", 1);
        initialAnimalCounts.put("Eagle", 2);
        initialAnimalCounts.put("Mouse", 1);
        initialAnimalCounts.put("Snake", 1);
        initialAnimalCounts.put("Goat", 1);
        initialAnimalCounts.put("Sheep", 1);
        initialAnimalCounts.put("Horse", 1);
        initialAnimalCounts.put("Bull", 1);
        initialAnimalCounts.put("Hog", 2);
        initialAnimalCounts.put("Cattepillar", 6);


        // Количество детёнышей
        offspringCounts.put("Wolf", 2);
        offspringCounts.put("Rabbit", 5);
        offspringCounts.put("Deer", 2);
        offspringCounts.put("Bear", 1);
        offspringCounts.put("Fox", 2);

        // Вероятность передвижения
        movementProbabilities.put("Wolf", 80); // 80%
        movementProbabilities.put("Rabbit", 50);
        movementProbabilities.put("Deer", 60);
        movementProbabilities.put("Bear", 30);
        movementProbabilities.put("Fox", 70);
    }

    // Геттеры и сеттеры для изменения параметров
    public int getIslandWidth() {
        return islandWidth;
    }

    public void setIslandWidth(int islandWidth) {
        this.islandWidth = islandWidth;
    }

    public int getIslandHeight() {
        return islandHeight;
    }

    public void setIslandHeight(int islandHeight) {
        this.islandHeight = islandHeight;
    }

    public int getTickDuration() {
        return tickDuration;
    }

    public void setTickDuration(int tickDuration) {
        this.tickDuration = tickDuration;
    }

    public Map<String, Integer> getInitialAnimalCounts() {
        return initialAnimalCounts;
    }

    public void setInitialAnimalCounts(Map<String, Integer> initialAnimalCounts) {
        this.initialAnimalCounts = initialAnimalCounts;
    }

    public int getMaxPlantsPerCell() {
        return maxPlantsPerCell;
    }

    public void setMaxPlantsPerCell(int maxPlantsPerCell) {
        this.maxPlantsPerCell = maxPlantsPerCell;
    }

    public int getMaxAnimalsPerCell() {
        return maxAnimalsPerCell;
    }

    public void setMaxAnimalsPerCell(int maxAnimalsPerCell) {
        this.maxAnimalsPerCell = maxAnimalsPerCell;
    }

    public boolean isStopWhenNoAnimalsLeft() {
        return stopWhenNoAnimalsLeft;
    }

    public void setStopWhenNoAnimalsLeft(boolean stopWhenNoAnimalsLeft) {
        this.stopWhenNoAnimalsLeft = stopWhenNoAnimalsLeft;
    }

    public Map<String, Integer> getOffspringCounts() {
        return offspringCounts;
    }

    public void setOffspringCounts(Map<String, Integer> offspringCounts) {
        this.offspringCounts = offspringCounts;
    }

    public Map<String, Integer> getMovementProbabilities() {
        return movementProbabilities;
    }

    public void setMovementProbabilities(Map<String, Integer> movementProbabilities) {
        this.movementProbabilities = movementProbabilities;
    }
}
