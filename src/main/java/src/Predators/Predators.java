package src.Predators;

import src.Animal.Animal;
import src.display.Location;

public abstract class Predators extends Animal {
    public Predators(String name, double weight, int maxCountPerLocation, int speed, double maxFood) {
        super(name, weight, maxCountPerLocation, speed, maxFood);
    }

    @Override
    public void eat(Location location) {

    }
}