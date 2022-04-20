import java.util.ArrayList;
import java.util.Random;

public class World {
    private final int STAT_TOTAL = 100;
    private ArrayList<Fuel> fuels = new ArrayList<Fuel>();
    private ArrayList<Organism> organisms = new ArrayList<Organism>();
    private int maxX;
    private int maxY;
    private Random rand = new Random();

    public World() {
        maxX = 100;
        maxY = 100;
    }

    public World(int maxX, int maxY, int startingFuels, int startingOrganisms) {
        this.maxX = maxX;
        this.maxY = maxY;
        for (int i = 0; i < startingFuels; i++) {
            Fuel fuel = new Fuel(rand.nextDouble() * maxX, rand.nextDouble() * maxY, maxX, maxY, rand.nextInt(50) + 1);
            fuels.add(fuel);
        }
        for (int i = 0; i < startingOrganisms; i++) {
            Organism organism = new Organism(rand.nextDouble() * maxX, rand.nextDouble() * maxY, maxX, maxY, STAT_TOTAL);
            organisms.add(organism);
        }
    }

    public void advanceFrame() {
        if (organisms.size() > 0) {
            for (Organism organism : organisms) {
                organism.collideWithWall(0, maxX, 0, maxY);
                organism.updatePosition();
                for (Fuel fuel : fuels) {
                    organism.checkAttach(fuel);
                }
            }
        }
    }


    public ArrayList<Fuel> getFuels() {
        return fuels;
    }
    public ArrayList<Organism> getOrganisms() {
        return organisms;
    }
}
