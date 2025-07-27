package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Paddle {
    int height = 20;
    int width = 100;
    int x,y;
    int velocityX = 5;

    private final BufferedImage paddle;
    private final KeyHandler keyHandler;
    GamePanel gamePanel;

    public Paddle(GamePanel gamePanel, KeyHandler keyHandler) {
        this.x = gamePanel.screenWidth / 2 - this.width / 2;
        this.y = gamePanel.screenHeight - this.height - 10;
        this.keyHandler = keyHandler;
        this.gamePanel = gamePanel;
        try {
            paddle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/paddle.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void reset() {
        x = gamePanel.screenWidth / 2 - width / 2;
    }

    public void update() {
        if (keyHandler.leftPressed) {
            x -= velocityX;
        }
        if (keyHandler.rightPressed) {
            x += velocityX;
        }

        if (x < 0) {
            x = 0;
        }
        if (x > gamePanel.screenWidth - width) {
            x =  gamePanel.screenWidth - width;
        }
    }
    public void draw(Graphics2D g2d) {
        g2d.drawImage(paddle, x, y, width, height, null);
    }
}
