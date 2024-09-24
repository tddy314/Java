import java.awt.*;

public class Bullet extends Rectangle {
    int xVelocity;
    int yVelocity;
    int speed = 4;
    public Bullet(int x, int y, int width, int height) {
        super(x, y, width, height);
        xVelocity = -speed;
        yVelocity = 0;
    }
    public void move() {
        x += xVelocity;
        y += yVelocity;
    }

    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.fillOval(x, y, width, height);
    }
}
