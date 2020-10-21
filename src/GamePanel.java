import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    int currentState = MENU;
    Font titleFont;
    Font desFont;
    Timer frameDraw;
    Timer alienSpawn;
    Rocketship rocket = new Rocketship(250, 550, 50, 50);
    ObjectManager objm = new ObjectManager(rocket);
    
    public GamePanel() {
    	titleFont = new Font("Arial", Font.PLAIN, 48);
    	desFont = new Font("Arial", Font.PLAIN, 20);
    	frameDraw = new Timer(1000/60, this);
    	frameDraw.start();
    	
    	if (needImage) {
    	    loadImage ("space.png");
    	}
    }
    
    
    
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		}
	}
	
	public void updateMenuState() {
		
	}
	public void updateGameState() {
		objm.update();
	}
	public void updateEndState()  {
		
	}
	
	public void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("League Invaders", 50, 100);
		g.setFont(desFont);
		g.drawString("Press ENTER to start", 150, 300);
		g.drawString("Press SPACE for instructions", 100, 500);
	}
	public void drawGameState(Graphics g) {
		
		
		if (gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0,  0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
		
		objm.draw(g);
	}
	public void drawEndState(Graphics g)  {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("GAME OVER", 100, 100);
		g.setFont(desFont);
		g.drawString("You killed 2 enemies", 150, 300);
		g.drawString("Press ENTER to restart", 150, 500);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		System.out.println("Action");
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		    } else {
		        currentState++;
		    }
		    
		    if (currentState == GAME) {
		    	startGame();
		    }else if (currentState == END) {
		    	alienSpawn.stop();
		    }
		}
		
		if (currentState == GAME) {
			if (e.getKeyCode()==KeyEvent.VK_UP) {
			    System.out.println("UP");
			    if (rocket.y > 0) {
			    	rocket.up();
			    }
			}
			
			if (e.getKeyCode()==KeyEvent.VK_DOWN) {
				if (rocket.y < 560) {
					System.out.println("DOWN");
			    	rocket.down();
				}
			}
			
			if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			    if (rocket.x > 0) {
			    	System.out.println("LEFT");
			    	rocket.left();
			    }
			}
			
			if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
				if (rocket.x < 450) {
					System.out.println("RIGHT");
					rocket.right();
				}
			}
			
			if ((e.getKeyCode()==KeyEvent.VK_SPACE)) {
				objm.addProjectile(rocket.getProjectile());
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
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
	
	public void startGame() {
		alienSpawn = new Timer(1000 , objm);
	    alienSpawn.start();
	}
}
