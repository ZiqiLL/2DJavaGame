package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

public class GamePanel extends JPanel {
    
    private MouseInputs mouseInputs;
    private float xDelta = 100;
    private float yDelta = 100;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 20;
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean moving = false;
    
    public GamePanel() {
        
        mouseInputs = new MouseInputs(this);
        importImg();
        loadAnimations();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        
    }
    
    public void setDirection(int direction) {
        this.playerDir = direction;
        moving = true;
    }
    
    public void setMoving(boolean moving) {
        this.moving = moving;
        
    }
    
    private void loadAnimations() {
        animations = new BufferedImage[9][6];
        for(int j = 0; j < animations.length; j++) {
            for(int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i*64, j * 40, 64, 40);
            }
            
        }
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setPreferredSize(size);
        
    }
    public void updateGame() {
        setAnimation();
        updateAnimationTick();
        updatePosition();
        
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(animations[playerAction][aniIndex], (int)xDelta, (int)yDelta, 128, 80, null);
     
    }
    
    private void updatePosition() {
        if(moving) {
            switch(playerDir) {
            case LEFT:
                xDelta -= 6;
                break;
            case UP:
                yDelta -= 6;
                break;
            case RIGHT:
                xDelta += 6;
                break;
            case DOWN:
                yDelta += 6;
                break;
            }
        }
        
    }

    private void setAnimation() {
       if(moving) {
           playerAction = RUNNING;
       } else {
           playerAction = IDLE;
       }
        
    }

    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
            }
        }
   }
}
