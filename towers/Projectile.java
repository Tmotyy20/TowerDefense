package towers;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import enemies.AEnemy;
import main.Coordinate;

public class Projectile {
    private Coordinate position; // Current position of the projectile
    private AEnemy target; // Target that the projectile is trying to hit
    private int speed; // Speed of the projectile
    private int damage; // Damage that the projectile can inflict
    private boolean hasHitTarget; // Indicator if the projectile has hit the target
    protected Rectangle hitbox; // Hitbox for collision detection
    private BufferedImage sprite; // Image/sprite of the projectile

    public Projectile(Coordinate position, AEnemy target, int speed, int damage, BufferedImage sprite) {
        this.position = position;
        this.target = target;
        this.speed = speed;
        this.damage = damage;
        this.hitbox = new Rectangle(position.x, position.y, 5, 5);
        this.sprite = sprite;
    }

    // Check if the projectile has hit the target
    public void CheckingCollision() {
        hasHitTarget = hitbox.intersects(target.getHitbox());
    }

    public boolean hasHitTarget() {
        return hasHitTarget;
    }

    public void move() {
        // Calculate direction from projectile to target
        int deltaX = target.getPosition().x - position.x; // Difference between projectile and target position
        int deltaY = target.getPosition().y - position.y;
        double distanceToTarget = Math.hypot(deltaX, deltaY); // Calculate distance between projectile and target using Pythagorean theorem
    
        // Normalize direction vector and multiply by speed
        if (distanceToTarget > 0) { // Check if the projectile is not yet at the target
            // The division by distanceToTarget allow a Straight direction
            double normalizedDeltaX = deltaX / distanceToTarget; // Normalize the x direction
            double normalizedDeltaY = deltaY / distanceToTarget; // Normalize the y direction
    
            position.x += (int)Math.round(speed * normalizedDeltaX); // Move the projectile towards the target
            position.y += (int)Math.round(speed * normalizedDeltaY); 
        }
    
        // Update hitbox location
        hitbox.setLocation(position.x, position.y); // The hitbox moves with the projectile
    }
 

    public void draw(Graphics g) {
        if (sprite != null) {
            g.drawImage(sprite, position.x, position.y, null);
        }
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public AEnemy getTarget() {
        return target;
    }

    public void setTarget(AEnemy target) {
        this.target = target;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}
