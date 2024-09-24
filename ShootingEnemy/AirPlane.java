import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class AirPlane extends  Rectangle {

    int yVelocity;
    int xVelocity;
    int speed = 5;
    Image image;

    public AirPlane(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.image = new ImageIcon(getClass().getResource("plane.png")).getImage();
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

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            setYDirection(-speed);
            move();
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            setYDirection(speed);
            move();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            setXDirection(speed);
            move();
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            setXDirection(-speed);
            move();
        }
    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            setYDirection(0);
            move();
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            setYDirection(0);
            move();
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            setXDirection(0);
            move();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            setXDirection(0);
            move();
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        //g.fillOval(x, y, width, height);
        g.drawImage(image, x, y, width, height, null);
    }

}
