import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    int currentState = MENU;
    Font titleFont;
    Font desFont;
    Timer frameDraw;
    Rocketship rocket = new Rocketship(250, 550, 50, 50);
    
    public GamePanel() {
    	titleFont = new Font("Arial", Font.PLAIN, 48);
    	desFont = new Font("Arial", Font.PLAIN, 20);
    	frameDraw = new Timer(1000/60, this);
    	frameDraw.start();
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
		g.setColor(Color.BLACK);
		g.fillRect(0,  0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		rocket.draw(g);
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
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
