/* 
 * Creation:    Apr 1, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.asset.GridCalculator;
import com.battleship.asset.ThemeManager;
import com.battleship.behaviors.Sprite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;



/**
 * <h1>BoxMapViewSquare</h1>
 * <p>
 * public class BoxMapViewSquare<br/>
 * extends BoxMapView
 * </p>
 * <p>Display a BoxMap for Square grid</p>
 *
 * @since   Apr 1, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * 
 * @see BoxMapView
 */
public class BoxMapViewSquare extends BoxMapView{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private Rectangle rect;
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new BoxMap view for square mode with default border color
     * @param posX      coordinate in the grid
     * @param posY      coordinate in the grid
     * @param pDim      boxDefault dimension
     * @param pSprite   default sprite
     */
    public BoxMapViewSquare(int posX, int posY, Dimension pDim, Sprite pSprite){
        super(posX, posY, pDim, pSprite);
        this.rect = this.createRectangle();
    }
    
    /**
     * Create a new BoxMap view for square mode with specific border color
     * @param posX      coordinate in the grid
     * @param posY      coordinate in the grid
     * @param pDim      boxDefault dimension
     * @param pSprite   default sprite
     * @param c         color of the borders
     */
    public BoxMapViewSquare(int posX, int posY, Dimension pDim, Sprite pSprite, Color c){
        super(posX, posY, pDim, pSprite, c);
        this.rect = this.createRectangle();
    }
    
    private Rectangle createRectangle(){
        Point p     = new Point();
        p.x         = this.dimension.width * this.coordinate.x;
        p.y         = this.dimension.height * this.coordinate.y;
        return new Rectangle(p, this.dimension);
    }
    
    
    //**************************************************************************
    // Draw fucntions
    //**************************************************************************
    @Override
    protected void drawDefault(Graphics2D g2){
        g2.setStroke(new BasicStroke(this.borderSize));
        Image   i   = null;
        Point   p   = GridCalculator.placeImgSquare(this.coordinate, dimension, dimension);
        switch(this.sprite.getState()){
            case Sprite.ALIVE_BOAT:
                i = this.imgBoatAlive;
                break;
            case Sprite.ALIVE_WATER:
                i = this.imgWaterAlive;
                break;
            case Sprite.DEAD_BOAT:
                i = this.imgBoatDead;
                break;
            case Sprite.DEAD_WATER:
                i = this.imgWaterDead;
                break;
        }
        g2.drawImage(i, p.x, p.y, i.getWidth(null), i.getHeight(null), null);
        g2.setColor(this.borderColor);
        g2.drawRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
    }
    
    
    @Override
    protected void drawHidden(Graphics2D g2){
        Image   i   = null;
        Point   p   = GridCalculator.placeImgSquare(this.coordinate, dimension, dimension);
        switch(this.sprite.getState()){
            case Sprite.ALIVE_BOAT:
                i = this.imgHiddenWaterAlive;
                break;
            case Sprite.ALIVE_WATER:
                i = this.imgHiddenWaterAlive;
                break;
            case Sprite.DEAD_BOAT:
                i = this.imgHiddenBoatDead;
                break;
            case Sprite.DEAD_WATER:
                i = this.imgHiddenWaterDead;
                break;
        }
        g2.drawImage(i, p.x, p.y, i.getWidth(null), i.getHeight(null), null);
        g2.setColor(this.borderColor);
        g2.drawRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
    }

    
    @Override
    protected void drawTargeted(Graphics2D g2){
        Image   i   = this.imgTargeted;
        Point   p   = GridCalculator.placeImgSquare(this.coordinate, dimension, dimension);
        g2.drawImage(i, p.x, p.y, i.getWidth(null), i.getHeight(null), null);
        g2.setColor(this.borderColor);
        g2.drawRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
    }

    
    @Override
    protected void drawHover(Graphics2D g2){
        Image   i   = this.imgHoverBoatAlive;
        Point   p   = GridCalculator.placeImgSquare(this.coordinate, dimension, dimension);
        g2.drawImage(i, p.x, p.y, i.getWidth(null), i.getHeight(null), null);
        g2.setColor(this.borderColor);
        g2.drawRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
    }
    
    
    //**************************************************************************
    // UI Functions
    //**************************************************************************
    @Override
    public void reloadUI(){
        //Visible images
        this.imgBoatAlive           = ThemeManager.getTheme().getImg(301700);
        this.imgBoatDead            = ThemeManager.getTheme().getImg(301800);
        this.imgWaterAlive          = ThemeManager.getTheme().getImg(301600);
        this.imgWaterDead           = ThemeManager.getTheme().getImg(301900);

        //Hidden image
        this.imgHiddenBoatDead      = ThemeManager.getTheme().getImg(301800);
        this.imgHiddenWaterAlive    = ThemeManager.getTheme().getImg(301400);
        this.imgHiddenWaterDead     = ThemeManager.getTheme().getImg(301500);

        //Targeted image
        this.imgTargeted            = ThemeManager.getTheme().getImg(301300);
        
        //Hover image
        this.imgHoverBoatAlive      = ThemeManager.getTheme().getImg(301200);
        this.imgHoverBoatDead       = ThemeManager.getTheme().getImg(301200);
        this.imgHoverWaterAlive     = ThemeManager.getTheme().getImg(301200);
        this.imgHoverWaterDead      = ThemeManager.getTheme().getImg(301200);
        
        this.imgHoverHiddenValid    = ThemeManager.getTheme().getImg(301200);
        this.imgHoverHiddenNotValid = ThemeManager.getTheme().getImg(301200);
    }
}
