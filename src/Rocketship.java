import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Rocketship extends GameObject {
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	

	public Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		
		speed = 15;
		
		if (needImage) {
		    loadImage ("rocket.png");
		}
	}
	
	public void draw(Graphics g) {
		//g.setColor(Color.BLUE);
        //g.fillRect(x, y, width, height);
        
        if (gotImage) {
        	g.drawImage(image, x, y, width, height, null);
        } else {
        	g.setColor(Color.BLUE);
        	g.fillRect(x, y, width, height);
        }
        
        super.update();
	}
	
	public void up() {
		y-=speed;
	}
	
	public void down() {
		y+=speed;
	}
	
	public void left() {
		x-=speed;
	}
	
	public void right() {
		x+=speed;
	}
	
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
	
	public projectile getProjectile() {
        return new projectile(x+width/2, y, 10, 10);
	} 
}
