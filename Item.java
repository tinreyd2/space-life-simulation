public class Item {
    protected double x;
    protected double y;

    protected double maxX;
    protected double maxY;

    protected double radius;
    protected double xVel;
    protected double yVel;
    protected double theta; //0 = straight to the right, max of 2

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public double getxVel() {
        return xVel;
    }

    public void setxVel(double xVel) {
        this.xVel = xVel;
    }

    public double getyVel() {
        return yVel;
    }

    public void setyVel(double yVel) {
        this.yVel = yVel;
    }

    public double getMaxX() {
        return maxX;
    }

    public void setMaxX(double maxX) {
        this.maxX = maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    void collideWithWall(int minX, int maxX, int minY, int maxY) {
        if (((x + xVel >= maxX) && (xVel > 0))
                || ((x + xVel <= minX) && (xVel < 0))) {
            xVel = xVel * -1;
        }
        if (((y + yVel >= maxY) && (yVel > 0))
                || ((y + yVel <= minY) && (yVel < 0))) {
            yVel = yVel * -1;
        }
    }

    /*
    public boolean movingTowards(Item other) {
        if ((this.xVel * other.xVel) + (this.yVel * other.yVel) > 0) {

        }
    }
    */


    protected static double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    void updatePosition() {
        x += xVel;
        y += yVel;
    }
}
