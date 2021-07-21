package planewar;

public class Abullet extends Bullet{
    protected int speed=6;

    public Abullet() {
        this.life=1;
    }
    public Abullet(int x, int y) {
        this.life=1;
        this.image = Game.abullet;
        width=image.getWidth();
        height=image.getHeight();
        this.x = x;
        this.y = y;
    }

    public void step() {

        this.y += this.speed;
    }

    public boolean outOfBounds() {
        return this.y > this.height+Game.height;
    }
}
