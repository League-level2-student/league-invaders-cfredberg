import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Rocketship rocket;
	ArrayList<projectile> projectiles = new ArrayList<projectile>();
	ArrayList<alien> aliens = new ArrayList<alien>();
	Random random = new Random();
	
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
	}
}
