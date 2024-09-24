import java.awt.*;
import java.util.Random;
import javax.swing.ImageIcon;

public class Enemy extends Rectangle {
    int xVelocity;
    int yVelocity;
    int speed  = 2;
    Random random;
    Image image;

    public Enemy(int x, int y, int width, int height) {
        super(x, y, width, height);
        setXDirection(-speed);
        image = new ImageIcon(getClass().getResource("enemy.png")).getImage();
    }
    public void setXDirection(int XDirection) {
        xVelocity = XDirection;
    }

    public void setYDirection(int setYDirection) {
        yVelocity = setYDirection;
    }

    public void move() {
        x += xVelocity;
        y += yVelocity;
    }

    public void draw(Graphics g) {
        //g.setColor(Color.blue);
        //g.fillOval(x, y, width, height);
        g.drawImage(image, x, y, width, height, null);
    }
}
