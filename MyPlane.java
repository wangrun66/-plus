package planewar;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MyPlane extends Planefather {
    private int boom = 3;
    private int index = 0;
    private int fire = 1;
    private int isProtect = 0;
    private BufferedImage[] images = new BufferedImage[0];
    private BufferedImage[] emberImages = new BufferedImage[0];

    public int getIsProtect() {
        return isProtect;
    }

    public void setIsProtect(int isProtect) {
        this.isProtect = isProtect;
    }

    {
        this.life = 3;
    }

    public MyPlane() {
        this.images = new BufferedImage[]{Game.myPlane0, Game.myPlane1};
        this.emberImages = new BufferedImage[]{Game.emberMyPlane0, Game.emberMyPlane1, Game.emberMyPlane2, Game.emberMyPlane3};
        this.image = Game.myPlane0;
        this.width = this.image.getWidth();
        this.height = this.image.getHeight();
        this.x = Game.width / 2 - width / 2;
        this.y = Game.height / 2 - height / 2 + 100;
    }

    public int getFire() {
        return fire;
    }

    public void setFire(int fire) {
        this.fire = fire;
    }
    public void subFire(){if (fire>1){this.fire--;}}
    public int getBoom() {
        return boom;
    }

    public void setBoom(int boom) {
        this.boom = boom;
    }

    public void move(int x, int y) {
        width=image.getWidth();
        height=image.getHeight();
        this.x = x - this.width / 2;
        this.y = y - this.height / 2;
    }

    public boolean outOfBounds() {
        return false;
    }

    public Bullet boom() {
        subtractboom();
        return new Boom(this.x + this.width / 2, this.y + this.height / 2);
    }

    public ArrayList<Bullet> shoot() {
        int xStep = (97) / (fire + 1);
        int yStep = 124/2/fire;
        ArrayList<Bullet> bullets = new ArrayList<Bullet>();
        for (int i = 1; i <= fire; i++) {
            bullets.add(new Bullet(this.x+47 + i * xStep, this.y+33 +height/2- yStep*(fire/2>=i?i:fire+1-i)));
        }
        return bullets;
    }

    public void addLife() {
        if (life<5)
        ++this.life;
        else addFire();
    }

    public void addFire() {
        if (fire <=10) ++this.fire;
    }

    public void subtractboom() {
        --this.boom;
    }

    public void addboom() {
        if (boom<5)
        ++this.boom;
        else addFire();
    }

    public void step() {
        if (isProtect > 0) {
            this.image = this.emberImages[this.index / 10 % this.images.length];
            if (index%10==0){
                isProtect--;
            }
        } else {
                this.image = this.images[this.index / 10 % this.images.length];
        }
        index++;
        width=image.getWidth();
        height=image.getHeight();

    }

    public boolean hit(Planefather other) {
        if (isProtect > 0) return false;
        if (!(other instanceof Ember)) {
            int x1 = other.x - 97 / 2;
            int x2 = other.x + 97 / 2 + other.width;
            int y1 = other.y - 124 / 2;
            int y2 = other.y + 124 / 2 + other.height;
            int myx = this.x+47 + 97 / 2;
            int myy = this.y+33+ 124 / 2;
            return myx > x1 && myx < x2 && myy > y1 && myy < y2;
        }
        return false;
    }
}
