import java.awt.Dimension;

import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame frame;
	GamePanel panel;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 650;
	
	public LeagueInvaders(){
		frame = new JFrame("League Invaders");
		panel = new GamePanel();
	}
	
	public static void main(String[] args) {
		LeagueInvaders l = new LeagueInvaders();
		l.setup();
	}
	
	public void setup() {
		frame.add(panel);
		frame.addKeyListener(panel);
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}
}
