package planewar;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Boomboom extends Bullet{
    private static BufferedImage[] images = new BufferedImage[4];
    private int index = 0;
    static {
        try {
            images = new BufferedImage[]{
                    null,
                    ImageIO.read(Game.class.getResourceAsStream("image/boomboom 3.png")),
                    ImageIO.read(Game.class.getResourceAsStream("image/boomboom 2.png")),
                    ImageIO.read(Game.class.getResourceAsStream("image/boomboom 1.png")),
                    ImageIO.read(Game.class.getResourceAsStream("image/boomboom 0.png")),
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boomboom(int x, int y) {
        life = 4;
        this.image = images[4];
        height=image.getHeight();
        width=image.getWidth();
        this.x = x-width/2;
        this.y = y-height/2;

    }

    @Override
    public void step() {
        index++;
        if (index%4==0&&isalife()){
            height=image.getHeight();
            width=image.getWidth();
            this.x = x-width/2;
            this.y = y-height/2;
            this.image = this.images[life-1];
            life--;
        }
    }

    @Override
    public boolean outOfBounds() {
        return life>1;
    }

    @Override
    public boolean shootBy(Bullet bullet) {
        return false;
    }
}
