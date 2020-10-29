import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	Rocketship rocket;
	ArrayList<projectile> projectiles = new ArrayList<projectile>();
	ArrayList<alien> aliens = new ArrayList<alien>();
	Random random = new Random();
	int score = 0;
	
	public ObjectManager(Rocketship r) {
		rocket = r;
	}
	
	public void addProjectile(projectile p) {
		projectiles.add(p);
	}
	
	public void addAlien() {
		aliens.add(new alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));
	}
	
	public void update() {
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).update();
			if (aliens.get(i).y < 0 || aliens.get(i).y > 650) {
				aliens.get(i).isActive = false;
			}
		}
		
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
			if (projectiles.get(i).y < 0 || projectiles.get(i).y > 650) {
				projectiles.get(i).isActive = false;
			}
		}
		
		System.out.println("print 1");
		
		checkCollision();
		
		purgeObjects();
		
	}
	
	public void draw(Graphics g) {
		rocket.draw(g);
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).draw(g);
		}
		
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).draw(g);
		}
	}
	
	public void purgeObjects() {
		for (int i = 0; i < aliens.size(); i++) {
			if (aliens.get(i).isActive == false) {
				aliens.remove(i);
			}
		}
		
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isActive == false) {
				projectiles.remove(i);
			}
		}
	}
	
	public int getScore() {
		return score;
	}
	
	public void checkCollision() {
		
		System.out.println("print 2");
		
		for (int i = 0; i < aliens.size(); i++) {
			System.out.println("print 4");
			if (rocket.collisionBox.intersects(aliens.get(i).collisionBox) == true) {
				aliens.get(i).isActive = false;
				rocket.isActive = false;
				
				System.out.println("print 3");
			}
			
			for (int p = 0; p < projectiles.size(); p++) {
				if (projectiles.get(p).collisionBox.intersects(aliens.get(i).collisionBox) == true) {
					aliens.get(i).isActive = false;
					projectiles.get(p).isActive = false;
					
					score++;
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addAlien();
	}
}
