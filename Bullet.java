package planewar;

public class Bullet extends Planefather{
    protected int speed=0;

    public Bullet() {
        this.life=1;
    }
    public Bullet(int x, int y) {
        this.life=1;
        this.image = Game.bullet;
        width=image.getWidth();
        height=image.getHeight();
        this.x = x;
        this.y = y;
    }

    public void step() {
        this.speed++;
        this.y -= this.speed;
    }

    public boolean outOfBounds() {
        return this.y > -this.height;
    }
    public Bullet boom() {
        return new Bbullet(x+width*3/2,y+height*3/2);
    }
}
