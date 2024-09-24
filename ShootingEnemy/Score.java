import java.awt.*;

public class Score extends Rectangle {
    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player = 0;
    
    public Score(int gwidth, int gheight) {
        Score.GAME_WIDTH = gwidth;
        Score.GAME_HEIGHT = gheight;
        player = 0;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 30));
        g.drawString(String.valueOf(player / 10) + String.valueOf(player % 10), GAME_WIDTH / 2 - 15, 20);
    }
}
