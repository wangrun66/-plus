package planewar;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Emberextraplane extends Planefather implements Ember{
    private static BufferedImage[] images = new BufferedImage[4];
    private int index = 0;

    static {
        try {
            images = new BufferedImage[]{
                    null,
                    ImageIO.read(Game.class.getResourceAsStream("image/extraplane_ember5.png")),
                    ImageIO.read(Game.class.getResourceAsStream("image/extraplane_ember4.png")),
                    ImageIO.read(Game.class.getResourceAsStream("image/extraplane_ember3.png")),
                    ImageIO.read(Game.class.getResourceAsStream("image/extraplane_ember2.png")),
                    ImageIO.read(Game.class.getResourceAsStream("image/extraplane_ember1.png")),
                    ImageIO.read(Game.class.getResourceAsStream("image/extraplane_ember0.png")),
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Emberextraplane(int x, int y) {
        life =5;
        this.x = x;
        this.y = y;
        this.image = images[5];
    }

    @Override
    public void step() {
        index++;
        if (index%4==0&&isalife()){
            this.image = this.images[life-1];
            life--;
        }
    }
    @Override
    public boolean outOfBounds() {
        return false;
    }

    @Override
    public boolean shootBy(Bullet bullet) {
        return false;
    }
}
