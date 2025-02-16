package src.Herbivores;

import src.Animal.Animal;
import src.display.Location;

public abstract class Herbivores extends Animal {
        public Herbivores(String name, double weight, int maxCountPerLocation, int speed, double maxFood) {
                super(name, weight, maxCountPerLocation, speed, maxFood);
        }

        @Override
        public void eat(Location location) {
        }
}