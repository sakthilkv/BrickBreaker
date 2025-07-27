package Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class GamePanel extends JPanel implements Runnable  {
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale; // 48
    final int screenWidth = 720;
    final int screenHeight = 720;
    final int gridCols = screenWidth/tileSize; // 48
    final int gridRows = screenHeight/tileSize; // 48

    Thread gameThread;
    final int FPS = 60;
    int actualFPS = 0;
    boolean running = true;

    private final BufferedImage background;
    KeyHandler keyHandler = new KeyHandler();
    State state = new State(this,3,0);

    public Paddle paddle = new Paddle(this,keyHandler);
    Ball ball = new Ball(this, paddle, state);
    BlockManager blockManager = new BlockManager(5, 14, 60, 20, 5,state);

    public GamePanel() {

        try {
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/background.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyHandler);

    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 /FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currTime;
        long timer = 0;
        long drawCount = 0;

        while(running){
            currTime = System.nanoTime();

            delta += (currTime - lastTime) / drawInterval;
            timer += (currTime - lastTime);
            lastTime = currTime;

            if(delta >= 1){
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                actualFPS = (int) drawCount;
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        Graphics2D g2d = (Graphics2D) g;

        paddle.draw(g2d);
        ball.draw(g2d);
        blockManager.draw(g2d);
        state.draw(g2d);
        paddle.update();
        ball.update();
        blockManager.checkCollision(ball);
    }
}
