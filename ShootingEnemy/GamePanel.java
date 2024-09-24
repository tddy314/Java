import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class GamePanel extends JPanel implements Runnable {
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int AIR_PLANE = 30;
    static final int BULLET_DIAMETER = 10;
    Thread gameThread;
    Image image, background;
    Random random;
    static AirPlane player;
    Enemy[] enemy = new Enemy[5];
    Score score;
    static Graphics graphics;
    Bullet[] bullet = new Bullet[5];
    Weapon[] weapon = new Weapon[55];
    static final int numbers_of_bullet = 50;
    static int IsInGame = 0;
    Pointer pointer;
    Button PlayGame;
    Button Exit;


    public GamePanel() {
      
        newPlane();
        newEnemy();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        PlayGame = new Button(GAME_WIDTH / 2 - 10, GAME_HEIGHT / 2 - 10, 10, 10, "PLAY");
        Exit = new Button(GAME_WIDTH / 2 - 10, GAME_HEIGHT / 2 + 10, 10, 10, "EXIT");
        pointer = new Pointer(GAME_WIDTH / 2 - 25, GAME_HEIGHT / 2 - 10, 10, 10, 0, 20, GAME_HEIGHT / 2 - 10, GAME_HEIGHT / 2 + 10);
        
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);
        this.addKeyListener(new AL());
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newEnemy() {
        
        for(int i = 0; i < 5; i++) {
        random = new Random();
        int y_new = random.nextInt(GAME_HEIGHT - AIR_PLANE);
        enemy[i] = new Enemy(GAME_WIDTH + i * 200, y_new, AIR_PLANE, AIR_PLANE);
        bullet[i] = new Bullet(GAME_WIDTH + i * 200, y_new + BULLET_DIAMETER / 2, BULLET_DIAMETER, BULLET_DIAMETER);
        }
    }

    public void newPlane() {
        player = new AirPlane(0, 0, AIR_PLANE, AIR_PLANE);
        for(int i = 0; i < 50; i++)
        weapon[i] = new Weapon(0, 0, BULLET_DIAMETER, BULLET_DIAMETER);
    }

    public void paint(Graphics g) {
        background = new ImageIcon(getClass().getResource("background.png")).getImage(); 
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        //image = createImage(getWidth(), getHeight());
        //graphics = image.getGraphics();
        draw(g);
       
        //g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {
        if(IsInGame == 1) {
        player.draw(g);
        score.draw(g);
        for(int i = 0; i < 5; i++) {
        enemy[i].draw(g);
        bullet[i].draw(g);
        }
        for(int i = 0; i < 50; i++)
        weapon[i].draw(g);
       }
       else {
        pointer.draw(g);
        PlayGame.draw(g);
        Exit.draw(g);
       }
    }

    public void move() {
        if(IsInGame == 1) {
        player.move();
        for(int i = 0; i < 5; i++) {
        enemy[i].move();
        bullet[i].move();
        }
        for(int i = 0; i < 50; i++)
        weapon[i].move();
        }
        //else pointer.move();
       
    }

    public void repost(int i) {
        random = new Random();
        int ynew = random.nextInt(GAME_HEIGHT - AIR_PLANE);
        enemy[i].x = GAME_WIDTH + i * 200;
        enemy[i].y = ynew;
        bullet[i].x = enemy[i].x;
        bullet[i].y = ynew + BULLET_DIAMETER / 2;
    }

    public void restart() {
        player.x = 0;
        player.y = 0;
        score.player = 0;
        for(int i = 0; i < 50; i++)
        weapon[i].isFlying = 0;
        for(int i = 0; i < 5; i++) repost(i);
    }

    public void checkCollision() {
        if(IsInGame == 0) return;
        for(int i = 0; i < 5; i++) {
        if(player.intersects(bullet[i]) || player.intersects(enemy[i])) restart(); 
        }

        for(int i = 0; i < 50; i++) {
            if(weapon[i].isFlying == 1) {
                for(int j = 0; j < 5; j++) {
                    if(weapon[i].intersects(enemy[j])) {
                        weapon[i].isFlying = 0;
                        repost(j);
                        score.player++;
                        break;
                    }
                }
            }
        }
        
        if(player.x <= 0) {
            player.x = 0;
        }   
        if(player.x >= GAME_WIDTH - AIR_PLANE) {
            player.x = GAME_WIDTH - AIR_PLANE;
        }
        if(player.y <= 0) {
            player.y = 0;
        }
        if(player.y >= GAME_HEIGHT - AIR_PLANE) {
            player.y = GAME_HEIGHT - AIR_PLANE;
        }
        for(int i = 0; i < 5; i++) {

        if(enemy[i].x <= 55) {
            repost(i);
        }
        else 
        if(bullet[i].x <= 0 && enemy[i].x >= 75) {
            bullet[i].x = enemy[i].x;
        }

    }
        for(int i = 0; i < 50; i++)
        if(weapon[i].isFlying == 1)
        if(weapon[i].x >= GAME_WIDTH - BULLET_DIAMETER) {
            weapon[i].isFlying = 0;
            weapon[i].xVelocity = 0;
       }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    public class AL extends KeyAdapter { 
        public void keyPressed(KeyEvent e) {
            if(IsInGame == 1) {
                player.keyPressed(e);
            if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                for(int i = 0; i < 50; i++) if(weapon[i].isFlying == 0) {
                weapon[i].x = player.x;
                weapon[i].y = player.y;
                weapon[i].keyPressed(e);
                break;
                }
            }
            }
            else {
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if(pointer.id == 0) {
                        IsInGame = 1;
                    }
                }
                else 
                pointer.keyPressed(e);
            }
        
        }

        public void keyReleased(KeyEvent e) {
            if(IsInGame == 1)
            player.keyReleased(e);
            else pointer.keyReleased(e);
        }
    }
}
