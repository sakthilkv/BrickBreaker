package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class State {
    int maxLife;
    int score;
    int life;

    GamePanel gamePanel;
    BufferedImage heartFull;
    BufferedImage heartEmpty;
    Font scoreFont = new Font("Arial", Font.BOLD, 20);

    public State(GamePanel gamePanel, int life, int score) {
        this.maxLife = life;
        this.score = score;
        this.life = maxLife;

        this.gamePanel = gamePanel;
        try {
            heartFull = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/heart_full.png")));
            heartEmpty = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/heart_empty.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void takeLife() {
        life--;
        if(life < 1) {
            gameRestart();
        }
    }

    public void gameRestart() {
        score = 0;
        life = maxLife;
        gamePanel.blockManager.resetBlocks();
        gamePanel.paddle.reset();
    }

    public void addScore(int points) {
        score += points;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(scoreFont);
        g2d.drawString("Score: " + score, 20, 30);

        for (int i = life; i < maxLife; i++) {
            g2d.drawImage(heartEmpty, gamePanel.screenWidth - (i + 1) * 40, 10, 30, 30, null);
        }

        for (int i = 0; i < life; i++) {
            g2d.drawImage(heartFull, gamePanel.screenWidth - (i + 1) * 40, 10, 30, 30, null);
        }
    }
}
