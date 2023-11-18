package enemies;

import java.awt.Graphics;

public interface IEnemy {
    void setPV(int pv);
    int getPV();
    void setSpeed(int speed);
    int getSpeed();
    void setAlive(boolean isAlive);
    boolean isAlive();
    void setHit(boolean isHit);
    boolean isHit();
    boolean hasReachedEnd(int[][] lvl);
    void draw(Graphics g);
}
