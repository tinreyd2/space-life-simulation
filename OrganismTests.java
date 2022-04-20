import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrganismTests {
    private Organism myOrganism;
    private Organism organism2;
    private Fuel fuel;

    @Before
    public void setUp() {
        myOrganism = new Organism();
        organism2 = new Organism(5, 5, 100, 100, 100);
        fuel = new Fuel(10, 10, 100, 100, 5);
    }

    @Test
    public void distanceCalc() {
        assertEquals(28.178, Item.calculateDistance(2, 3, 15, 28), 0.001);
    }

    @Test
    public void testSettersAndGetters() {
        myOrganism.setCurrentFuel(30);
        myOrganism.setAlive(false);
        myOrganism.setX(3.0);
        assertEquals(30, myOrganism.getCurrentFuel(), 0.001);
        assertEquals(false, myOrganism.isAlive());
        assertEquals(3.0, myOrganism.getX(), 0.001);
    }

    @Test
    public void testVelocity() {
        organism2.setTheta(Math.PI);
        organism2.setSpeed(5);
        organism2.calculateVelocities();
        assertEquals(-5, organism2.xVel, 0.001);
        assertEquals(0, organism2.yVel, 0.001);
        organism2.setTheta(1.5);
        organism2.setSpeed(55);
        organism2.calculateVelocities();
        assertEquals(54.86, organism2.yVel, 0.005);
        assertEquals(3.890, organism2.xVel, 0.005);
    }

    @Test
    public void attachmentTest() {
        fuel.setX(15);
        fuel.setY(15);
        fuel.setRadius(3);
        organism2.setX(2);
        organism2.setxVel(1);
        organism2.setyVel(0);
        organism2.setY(12);
        organism2.setRadius(2);
        assertTrue(organism2.movingTowardsFuel(fuel));
        assertFalse(organism2.checkAttach(fuel));
        organism2.setX(10);
        assertFalse(organism2.checkAttach(fuel));
    }
}
