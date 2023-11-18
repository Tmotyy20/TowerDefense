package towers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.time.Instant;


import enemies.AEnemy;
import main.Coordinate;

public abstract class ATower implements ITower {
    protected int level; // Level of the tower
    protected int damage; // Damage capability of the tower
    protected int range; // Range within which the tower can attack enemies
    protected int cost; // Cost of the tower
    protected Coordinate position; // Position of the tower on the map
    protected boolean isActive; // Flag to check if the tower is active
    private List<Projectile> projectiles; // List to store projectiles fired by the tower
    private Instant lastAttackTime; // Timestamp of the last attack to manage fire rate


    public ATower(int level, int damage, int range, int cost, BufferedImage sprite, int animationSpeed) {
     
        this.level = level;
        this.damage = damage;
        this.range = range;
        this.cost = cost;
        this.position = new Coordinate(0, 0); // Default position
        this.isActive = true;
        projectiles = new ArrayList<>();
        this.lastAttackTime = Instant.now(); // Set the initial last attack time

    }
    public abstract void updateAnimations();

    // Method to check if an enemy is within the tower's range
    private boolean isEnemyInRange(AEnemy enemy) {
        double distance = Math.hypot(enemy.getPosition().x - this.position.x, enemy.getPosition().y - this.position.y); // used to calculate the distance between two points
        return distance <= this.range; //  checks if the calculated distance is less than or equal to the tower's range
    }

    // Method to detect enemies and fire projectiles
    public void detection(List<AEnemy> enemies, BufferedImage projectileSprite) {
        if (Instant.now().isAfter(lastAttackTime.plusSeconds(1))) { // Check if one second last
            for (AEnemy enemy : enemies) {
                if (isEnemyInRange(enemy)) {
                    Projectile projectile = new Projectile(new Coordinate(this.position.x, this.position.y), enemy, 6, 1, projectileSprite);
                    projectiles.add(projectile);
                    lastAttackTime = Instant.now(); // update last attack time
                    break;
                }
            }
        }
    }

     // Method to move the projectiles and check for hits
     public void projectileMovement() {
        Iterator<Projectile> projectileIterator = projectiles.iterator(); // Creating an iterator to loop through all projectiles in the list
        while (projectileIterator.hasNext()) { // Loop continues as long as there are more projectiles to process
            Projectile projectile = projectileIterator.next(); // Fetch the next projectile from the iterator for processing
            
            projectile.move(); // Call the move method of Projectile class to update its position based on its speed and direction
    
            projectile.CheckingCollision(); // Call the CheckingCollision method of Projectile class to check if it has hit the target
    
            if (projectile.hasHitTarget()) { // Check if the current projectile has hit its target
                projectile.getTarget().receiveDamage(projectile.getDamage()); // If it hit the target, apply damage to the target enemy
                
                System.out.println("hit");
                projectileIterator.remove(); // Remove the projectile once it hits
            }
        }
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }


    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public int getRange() {
        return range;
    }

    @Override
    public void setRange(int range) {
        this.range = range;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    // abstract methods here cause each methods will be differents for each tower
    @Override
    public abstract void upgradeDamage();

    @Override
    public abstract void upgradeRange();

    @Override
    public void draw(Graphics g) {
    }

}