package planewar;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Extraplane extends Planefather implements Enemy{
    private int lv;
    private int xSpeed = 2;
    private int ySpeed = 1;
    private int fireindex=0;
    private int maxlife;
    private BufferedImage[] images = new BufferedImage[0];
    private int index = 0;
    public Extraplane() {
        this.lv=Planefather.lv;
        this.images = new BufferedImage[]{Game.extraplane_n1, Game.extraplane_n2};
        this.maxlife=500+lv*10;
        this.life=maxlife;
        ySpeed=1+lv/50>5?5:1+lv/50;
        this.image = Game.extraplane_n1;
        this.width = this.image.getWidth();
        this.height = this.image.getHeight();
        Random rand = new Random();
        this.x = rand.nextInt(Game.width - this.width);
        this.y=-this.height;
    }

    public int getMaxlife() {
        return maxlife;
    }

    public void setMaxlife(int maxlife) {
        this.maxlife = maxlife;
    }

    @Override
    public int getScore() {
        return 50+lv/10;
    }

    @Override
    public boolean isalife() {
        return this.life>0;
    }

    @Override
    public Planefather death() {
        return new Emberextraplane(x,y);
    }

    @Override
    public void step(){}

    public boolean sstep() {
        if(this.images.length > 0) {
            this.image = this.images[this.index++ / 10 % this.images.length];
        }
        if (y<100){
            this.y += this.ySpeed;
        }else{
            fireindex++;
            this.x+=xSpeed;
            if(this.x > Game.width - this.width) {xSpeed=-2;}
            if (this.x < 0){xSpeed=2;}
            if (fireindex%(60-lv/10>15?60-lv/10:15)==0) return true;
        }

        return false;
    }

    @Override
    public boolean shootBy(Bullet bullet) {
        int x = bullet.x;  //
        int y = bullet.y;  //
        if (this.x < x && x < this.x + width && this.y < y && y < this.y + height) {
            this.image=Game.extraplane_hit;
            return true;
        }else return false;
    }

    @Override
    public boolean outOfBounds() {
        return this.y > this.height+Game.height;
    }

    public Abullet Eshoot() {
            return new Abullet(this.x + width/2, this.y +height);
    }
}
