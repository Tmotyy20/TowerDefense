package towers;

import java.awt.Graphics;

public interface ITower {
    void upgradeDamage();
    void upgradeRange();
    int getLevel();
    int getTargetNumber();
    void setDamage(int damage);
    int getDamage();
    void setRange(int range);
    int getRange();
    void setCost(int cost);
    int getCost();
    boolean isActive();
    void draw(Graphics g); // Method to draw the tower
}
