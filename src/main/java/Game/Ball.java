package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Ball {
    int diameter = 30;
    int x,y;
    int velocityX = 4;
    int velocityY = 5;
    GamePanel gamePanel;
    private final BufferedImage ball;
    Paddle paddle;
    State state;

    public Ball(GamePanel gamePanel, Paddle paddle, State state) {
        this.x = gamePanel.screenWidth / 2;
        this.y = gamePanel.screenHeight / 2 ;
        this.gamePanel = gamePanel;
        this.paddle = paddle;
        this.state = state;

        try {
            ball = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/ball.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {
        x += velocityX;
        y += velocityY;

        if (x <= 0 || x >= gamePanel.screenWidth - diameter) {
            velocityX *= -1;
        }

        if (y <= 0) {
            velocityY *= -1;
        }

        if (y >= gamePanel.screenHeight) {
            x = gamePanel.screenWidth / 2 - diameter / 2;
            y = gamePanel.screenHeight / 2;
            velocityY = Math.abs(velocityY);
            state.takeLife();
        }



        Rectangle ballRect = new Rectangle(x, y, diameter, diameter);
        Rectangle paddleRect = new Rectangle(paddle.x, paddle.y, paddle.width, paddle.height);
        if (ballRect.intersects(paddleRect)) {
            int paddleCenter = paddle.x + paddle.width / 2;
            int ballCenter = x + diameter / 2;
            int offset = ballCenter - paddleCenter;

            velocityX = offset / 10;

            if (velocityX == 0) {
                velocityX = (Math.random() < 0.5) ? -2 : 2;
            } else if (Math.abs(velocityX) < 2) {
                velocityX = 2 * Integer.signum(velocityX);
            }

            velocityY = -Math.abs(velocityY);
        }

    }
    public void draw(Graphics2D g2d) {
        g2d.drawImage(ball, x, y, diameter, diameter, null);
    }

    public void reset() {
        x = gamePanel.screenWidth / 2 - diameter / 2;
        y = gamePanel.screenHeight / 2;
        velocityX = 3;
        velocityY = 2;
    }
}
