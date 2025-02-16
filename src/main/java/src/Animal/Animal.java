package src.Animal;

import src.display.Location;
import java.util.Random;

public abstract class Animal {
    protected String name;                   // Имя животного
    protected double weight;                 // Вес
    protected int maxCountPerLocation;       // Максимальное количество в одной локации
    protected int speed;                     // Скорость передвижения
    protected double maxFood;                // Максимальное количество пищи, которое может съесть
    protected double currentFood;            // Текущее состояние пищи
    protected boolean isDead = false;        // Состояние животного
    protected Random random = new Random();  // Для генерации случайных событий

    // Конструктор
    public Animal(String name, double weight, int maxCountPerLocation, int speed, double maxFood) {
        this.name = name;
        this.weight = weight;
        this.maxCountPerLocation = maxCountPerLocation;
        this.speed = speed;
        this.maxFood = maxFood;
        this.currentFood = maxFood; // Животное начинает с максимальным запасом пищи
    }

    // Метод поедания (реализация будет у наследников)
    public abstract void eat(Location location);

    // Метод размножения (реализация будет у наследников)
    public abstract void reproduce(Location location);

    // Метод движения животного
    public void move(Location[][] grid) {
        if (isDead) return; // Мёртвое животное не двигается

        int newX, newY;
        int gridSizeX = grid.length;
        int gridSizeY = grid[0].length;

        // Генерация новых координат в пределах скорости
        do {
            newX = random.nextInt((speed * 2) + 1) - speed + findCurrentX(grid, this);
            newY = random.nextInt((speed * 2) + 1) - speed + findCurrentY(grid, this);
        } while (!isValidMove(newX, newY, gridSizeX, gridSizeY));

        // Перемещаем животное
        Location currentLocation = grid[findCurrentX(grid, this)][findCurrentY(grid, this)];
        Location newLocation = grid[newX][newY];

        currentLocation.removeAnimal(this);
        newLocation.addAnimal(this);

        System.out.println(name + " переместился в (" + newX + ", " + newY + ")");
    }

    // Проверка валидности передвижения
    private boolean isValidMove(int x, int y, int maxX, int maxY) {
        return x >= 0 && x < maxX && y >= 0 && y < maxY;
    }

    // Находит текущую координату X животного
    private int findCurrentX(Location[][] grid, Animal animal) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].getAnimals().contains(animal)) return i;
            }
        }
        return -1; // Если животное не найдено
    }

    // Находит текущую координату Y животного
    private int findCurrentY(Location[][] grid, Animal animal) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].getAnimals().contains(animal)) return j;
            }
        }
        return -1; // Если животное не найдено
    }

    // Голодает: уменьшает уровень пищи
    public void starve() {
        currentFood -= maxFood * 0.05; // Потеря еды со временем
        if (currentFood <= 0) {
            isDead = true;
            System.out.println(name + " умер от голода.");
        }
    }

    // Проверка, живо ли животное
    public boolean isDead() {
        return isDead;
    }

    // Геттер для максимального количества животных на клетке
    public int getMaxCountPerLocation() {
        return maxCountPerLocation;
    }

    // Возвращает символ Юникода для отображения
    public String getSymbol() {
        switch (name.toLowerCase()) {
            case "wolf": return "🐺";
            case "rabbit": return "🐇";
            case "fox": return "🦊";
            case "deer": return "🦌";
            case "hog": return "🐗";
            case "bear": return "🐻";
            case "duck": return "🦆";
            case "mouse": return "🐁";
            case "snake": return "🐍";
            case "goat": return "🐐";
            case "sheep": return "🐑";
            case "bull": return "🐃";
            case "horse": return "🐎";
            case "eagle": return "🦅";
            case "caterpillar": return "🐛";
            default: return "";
        }
    }

    // Возвращает имя животного
    public String getName() {
        return name;
    }
}
