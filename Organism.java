import java.util.Random;
import java.lang.Math;

public class Organism extends Item {
    private final int NUM_STATS = 5;
    private int maxHealth; // Max = 100
    private int currentHealth; //Max = maxHealth
    private double maxFuel; // Max = 100
    private double currentFuel; // Max = maxFuel
    private int speed; // Max = 100
    private int evasiveness; // Max = 100
    private double refuelSpeed; // Max = 100
    private boolean alive;
    private Fuel attachedFuel = null;
    private Random rand = new Random();

    public Organism() {
        maxHealth = 20;
        currentHealth = 20;
        maxFuel = 20.0;
        currentFuel = 20.0;
        speed = 20;
        evasiveness = 20;
        refuelSpeed = 20.0;
        x = 0.0;
        y = 0.0;
        xVel = 0.0;
        yVel = 0.0;
        theta = 0.0;
        radius = 1.0;
        alive = true;
    }

    public Organism(double x, double y, double maxX, double maxY, int statTotal) {
        this.x = x;
        this.y = y;
        this.maxX = maxX;
        this.maxY = maxY;
        speed = (rand.nextInt(statTotal) / NUM_STATS) + 1;
        maxHealth = (rand.nextInt(statTotal) / NUM_STATS) + 1;
        evasiveness = (rand.nextInt(statTotal) / NUM_STATS) + 1;
        refuelSpeed = (rand.nextInt(statTotal) / NUM_STATS) + 1;
        maxFuel = (rand.nextInt(statTotal) / NUM_STATS) + 1;
        alive = true;
        currentHealth = maxHealth;
        currentFuel = maxFuel;
        theta = rand.nextDouble() * (2 * Math.PI);
        this.calculateVelocities();
        radius = maxHealth;
    }

    public void calculateVelocities() {
        xVel = Math.cos(theta) * speed;
        yVel = Math.sin(theta) * speed;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public double getMaxFuel() {
        return maxFuel;
    }

    public void setMaxFuel(double maxFuel) {
        this.maxFuel = maxFuel;
    }

    public double getCurrentFuel() {
        return currentFuel;
    }

    public void setCurrentFuel(double currentFuel) {
        this.currentFuel = currentFuel;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getEvasiveness() {
        return evasiveness;
    }

    public void setEvasiveness(int evasiveness) {
        this.evasiveness = evasiveness;
    }

    public double getRefuelSpeed() {
        return refuelSpeed;
    }

    public void setRefuelSpeed(double refuelSpeed) {
        this.refuelSpeed = refuelSpeed;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean hasDied() {
        if (currentHealth <= 0) {
            alive = false;
            this.die();
            return true;
        }
        return false;
    }

    public void die() {
        xVel = 0;
        yVel = 0;
    }

    public Fuel getAttachedFuel() {
        return attachedFuel;
    }

    public void attach(Fuel toAttach) {
        attachedFuel = toAttach;
    }

    public boolean movingTowardsFuel(Item other) {
        if (other instanceof Fuel) {
            double futureX = this.x + xVel;
            double futureY = this.y + yVel;
            if (calculateDistance(other.x, other.y, futureX, futureY)
                    < calculateDistance(other.x, other.y, this.x, this.y)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkAttach(Fuel other) {
        double dist = calculateDistance(this.x, this.y, other.x, other.y);
        double circleCalc = Math.pow(this.x - other.x, 2) + (Math.pow(this.y - other.y, 2));
        if (this.movingTowardsFuel(other) && Math.pow(this.radius - other.radius,2) <= circleCalc
                && circleCalc <= Math.pow(this.radius + other.radius, 2)) {
            this.attach(other);
            return true;
        }
        return false;
    }
}
