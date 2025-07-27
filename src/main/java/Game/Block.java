package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Block {
    int x,y;
    int height;
    int width;
    int points;
    boolean destroyed = false;
    private final BufferedImage block;
    public Block(int x, int y, int width, int height, int points) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.points = points;
        try {
            block = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/block.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(block, x, y, width, height, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
