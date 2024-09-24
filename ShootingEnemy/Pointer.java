import java.awt.*;
import java.awt.event.KeyEvent;

public class Pointer extends Rectangle {
    String tex = ">";
    int id;
    int yadd;
    int yVelocity;
    int idVelocity;
    int sup, inf;
    public Pointer(int x, int y , int Pointer_wid, int Pointer_hei, int id, int yv, int ght, int ghd) {
        super(x, y, Pointer_wid, Pointer_hei);
        this.id = id;
        this.yadd = yv;
        this.sup = ght;
        this.inf = ghd;
    } 

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            yVelocity = -yadd;
            idVelocity = -1;
            move();
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            yVelocity = yadd;
            idVelocity = 1;
            move();
        }
    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            yVelocity = 0;
            idVelocity = 0;
            move();
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            yVelocity = 0;
            idVelocity = 0;
            move();
        }
    }

    public void move() {
        if(idVelocity == 0) return;
        if(idVelocity == 1) {
            id = (id + 1) % 2;
            if(y == inf) y = sup;
            else y = y + yVelocity;
        }
        else  {
            id = ((id - 1) % 2 + 2) % 2;
            if(y == sup) y = inf;
            else y = y + yVelocity;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 20));
        g.drawString(String.valueOf(tex), x, y);
    }
}
