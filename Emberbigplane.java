package planewar;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Emberbigplane extends Planefather implements Ember{
    private static BufferedImage[] images = new BufferedImage[4];
    private int index = 0;

    static {
        try {
            images = new BufferedImage[]{
                    null,
                    ImageIO.read(Game.class.getResourceAsStream("image/bigplane_ember3.png")),
                    ImageIO.read(Game.class.getResourceAsStream("image/bigplane_ember2.png")),
                    ImageIO.read(Game.class.getResourceAsStream("image/bigplane_ember1.png")),
                    ImageIO.read(Game.class.getResourceAsStream("image/bigplane_ember0.png")),
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Emberbigplane(int x, int y) {
        life = 4;
        this.x = x;
        this.y = y;
        this.image = images[4];
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
