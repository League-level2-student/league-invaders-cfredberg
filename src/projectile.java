import java.awt.Color;
import java.awt.Graphics;

public class projectile extends GameObject {

	public projectile(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 10;
	}
	
	public void update() {
		y-=speed;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
	}

}
