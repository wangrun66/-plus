package planewar;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Smallplane extends Planefather implements Enemy{
    private int lv;
    private int speed;
    @Override
    public int getScore() {
        return 1+lv/10;
    }
    public Smallplane() {
        this.lv=Planefather.lv;
        this.life=1+lv/60;
        speed=3+lv/30>10?10:3+lv/30;
        this.image = Game.smallplane;
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
    public Planefather death(){
        return new Embersmallplane(x,y);
    }
}
