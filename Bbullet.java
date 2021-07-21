package planewar;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bbullet extends Bullet{
    public static BufferedImage images;
    static {
        try {
            images = ImageIO.read(Game.class.getResourceAsStream("image/bbullet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Bbullet(){}
    public Bbullet(int x, int y) {
        life = 3;
        this.image = images;
        height=image.getHeight();
        width=image.getWidth();
        this.x = x-width/2;
        this.y = y-height/2;
    }

    @Override
    public void step() {
        this.life--;
    }
    public boolean outOfBounds() {
        return life>1;
    }
}
