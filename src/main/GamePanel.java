package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel {
    
    private MouseInputs mouseInputs;
    private float xDelta = 100;
    private float yDelta = 100;
    private float xDir = 1f;
    private float yDir = 1f;
    private Color color = new Color(100,100,100);
    private Random random;
    
    public GamePanel() {
        random = new Random();
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        updateRectangle();
        
        g.setColor(color);
        g.fillRect((int)xDelta, (int)yDelta, 200, 50); 
    }
    
    public void changeXDelta(int value) {
        this.xDelta += value;
        
    }
    
    public void changeYDelta(int value) {
        this.yDelta += value;
        
    }
    
    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
        
    }
    
    private void updateRectangle() {
        xDelta += xDir;
        if(xDelta > 400 || xDelta < 0) {
            xDir *= -1;
            color = getRndColor();    
        }
        yDelta += yDir;
        if(yDelta > 400 || yDelta < 0) {
            yDir *= -1;
            color = getRndColor();
        }
        
    }

    private Color getRndColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        // TODO Auto-generated method stub
        return new Color(r, g, b);
    }

}
