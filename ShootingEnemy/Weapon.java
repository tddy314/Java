import java.awt.*;
import java.awt.event.KeyEvent;

public class Weapon extends Rectangle {
    int xVelocity = 0;
    int speed = 8;
    int isFlying = 0;
    public Weapon(int x, int y, int width, int height) {
        super(x, y, width, height);
        isFlying = 0;
        xVelocity = 0;
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            xVelocity = speed;
            isFlying = 1;
            move();
        }
    }

    public void move() {
        x = x + xVelocity;
    }

    public void draw(Graphics g) {
        if(isFlying == 1) {
        g.setColor(Color.red);
        g.fillOval(x, y, width, height);
        }
    }

}
