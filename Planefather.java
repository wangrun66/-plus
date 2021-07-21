package planewar;

import java.awt.image.BufferedImage;

public abstract class Planefather {
    protected int x;
    protected  int y;
    protected  int height;
    protected  int width;
    protected static int lv;
    protected BufferedImage image;

    public int getLv() { return lv; }
    public void setLv(int lv) { this.lv = lv; }
    public int getLife() { return life; }
    public void setLife(int life) { this.life = life; }
    protected int life;
    public Planefather(){}
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    public BufferedImage getImage() { return image; }
    public void setImage(BufferedImage image) { this.image = image; }
    public abstract void step();
    public abstract boolean outOfBounds();
    //是否被击中
    public boolean shootBy(Bullet bullet) {
        int x = bullet.x;  //
        int y = bullet.y;  //
        return this.x < x && x < this.x + width && this.y < y && y < this.y + height;
    }
    public void subtractLife() {
        --this.life;
    }
    public boolean isalife(){return life>0;}

}
