package src.Plants;

public class Plant {
    private double weight;

    public Plant() {
        this.weight = 1.0;
    }

    public double getWeight() {
        return weight;
    }

    public void grow() {
        weight += 0.1;
    }

    public String getSymbol() {
        return "🌱";
    }
}
