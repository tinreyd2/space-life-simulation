public class Fuel extends Item {
    private double maxFuel;
    private double currentFuel;

    private final double FUELING_CONSTANT = 0.5;

    public Fuel() {
        x = 0.0;
        y = 0.0;
        maxX = 100.0;
        maxY = 100.0;
        theta = 0.0;
        maxFuel = 20.0;
        currentFuel = 20.0;
    }

    public Fuel(double x, double y, double maxX, double maxY, int maxFuel) {
        this.x = x;
        this.y = y;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxFuel = maxFuel;
        currentFuel = maxFuel;
        radius = currentFuel;
    }

    public double getCurrentFuel() {
        return currentFuel;
    }

    public void setCurrentFuel(double currentFuel) {
        this.currentFuel = currentFuel;
    }

    public double siphonFuel(int refuelSpeed) {
        if (currentFuel > 0) {
            if (currentFuel - (refuelSpeed * FUELING_CONSTANT) <= 0) {
                double temp = currentFuel;
                currentFuel = 0;
                return temp;

            } else {
                currentFuel -= (refuelSpeed * FUELING_CONSTANT);
                return (refuelSpeed * FUELING_CONSTANT);
            }
        } else {
            return 0;
        }
    }
}
