package Game;

import java.awt.*;
import java.util.ArrayList;

public class BlockManager {
    ArrayList<Block> blocks = new ArrayList<>();
    State state;
    public BlockManager(int rows, int cols, int blockWidth, int blockHeight, int spacing, State state) {
        this.state = state;
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                int x = col * (blockWidth + spacing) + spacing;
                int y = row * (blockHeight + spacing) + spacing + 50;
                blocks.add(new Block(x, y, blockWidth, blockHeight, 100));
            }
        }
    }

    public void draw(Graphics2D g2d) {
        for(Block block: blocks){
            if(!block.destroyed) block.draw(g2d);
        }
    }

    public void checkCollision(Ball ball) {
        Rectangle ballRect = new Rectangle(ball.x, ball.y, ball.diameter, ball.diameter);
        for (Block block : blocks) {
            if (!block.destroyed && ballRect.intersects(block.getBounds())) {
                block.destroyed = true;
                ball.velocityY = -ball.velocityY;
                state.addScore(block.points);
                break;
            }
        }
        checkAllDestroyed();
    }

    public void checkAllDestroyed() {
        boolean allDestroyed = true;
        for (Block block : blocks) {
            if (!block.destroyed) {
                allDestroyed = false;
                break;
            }
        }
        if (allDestroyed) {
            state.gamePanel.ball.reset();
            resetBlocks();
        }
    }

    public void resetBlocks() {
        for (Block block : blocks) {
            block.destroyed = false;
        }
    }
}
