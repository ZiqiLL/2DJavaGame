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
import static main.Game.GAME_WIDTH;
import static main.Game.GAME_HEIGHT;

public class GamePanel extends JPanel {
    
    private MouseInputs mouseInputs;
    private Game game;

    
    public GamePanel(Game game) {
        this.game = game;
        
        mouseInputs = new MouseInputs(this);

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
        
    }
    public void updateGame() {
    
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        game.render(g);
     
    }
    
    public Game getGame() {
        return game;
    }
    

}
