package planewar;

import java.util.Random;

public class Bigplane extends Planefather implements Enemy{
    private int lv;
    private int speed;
    @Override
    public int getScore() {
        return 5+lv/4;
    }
    public Bigplane() {
        this.lv=Planefather.lv;
        this.life=10+lv/20;
        speed=2+lv/50>7?7:2+lv/50;
        this.image = Game.bigplane;
        this.width = this.image.getWidth();
        this.height = this.image.getHeight();
        Random rand = new Random();
        this.x = rand.nextInt(Game.width - this.width);
        this.y=-this.height;
    }
    @Override
    public boolean outOfBounds() {
        return this.y > this.height+Game.height;
    }

    @Override
    public void step() {
        this.y += this.speed;
    }
    public boolean isalife(){
        return this.life>0;
    }

    @Override
    public Planefather death() {
        return new Emberbigplane(x,y);
    }
}
