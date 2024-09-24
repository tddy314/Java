import java.awt.*;

public class Button extends Rectangle {
 
    String val;
    public Button(int x, int y, int button_width, int button_height, String tex) {
        super(x, y, button_width, button_height);
        this.val = tex;
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 20));
        g.drawString(val, x, y);
    }
}
