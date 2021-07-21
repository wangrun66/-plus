package planewar;

public class Boom extends Bullet{
    protected int speed=-12;

    public Boom() {
        this.life=1;
    }
    public Boom(int x, int y) {
        this.life=1;
        this.x = x;
        this.y = y;
        this.image = Game.boom0;
        width=image.getWidth();
        height=image.getHeight();
    }

    public void step() {
        if (speed>0){
            image=Game.boom;
        }
        speed+=1;
        this.y -= this.speed;
    }

    public boolean outOfBounds() {
        return this.y > -this.height+100;
    }
    public Bullet boom() {
        return new Boomboom(x+width*3/2,y+height*3/2);
    }
}
