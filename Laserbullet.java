package planewar;

public class Laserbullet extends Bullet{
    public Laserbullet() {
        life=30;
    }

    public Laserbullet(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public boolean outOfBounds() {
        if (life<0) return false;
        return true;
    }
    public void step(int x) {
        this.life--;
        this.x = x;
    }
}
