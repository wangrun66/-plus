package planewar;

import java.util.Random;

public class Bee extends Planefather implements Award{
    private int xSpeed = 4;
    private int ySpeed = 2;
    private int awardType;

    public Bee() {
        life=1;
        this.image = Game.bee;
        this.width = this.image.getWidth();
        this.height = this.image.getHeight();
        this.y = -this.height;
        Random rand = new Random();
        this.x = rand.nextInt(Game.width - this.width);
        this.awardType = rand.nextInt(3);
    }

    public int getType() {
        return this.awardType;
    }

    public boolean outOfBounds() {
        return this.y > this.height+Game.height;
    }

    @Override
    public Planefather death() {
        return  new Emberbee(x,y);
    }

    public void step() {
        this.x += this.xSpeed;
        this.y += this.ySpeed;
        if(this.x > Game.width - this.width) {
            this.xSpeed = -4;
        }

        if(this.x < 0) {
            this.xSpeed = 4;
        }

    }
}
