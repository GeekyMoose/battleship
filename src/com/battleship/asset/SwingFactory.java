/* 
 * Creation:    Feb 25, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.asset;

import com.battleship.constants.GameConstants;
import com.battleship.controllers.GameConfigController;
import com.battleship.controllers.GameController;
import com.battleship.controllers.GridController;
import com.battleship.controllers.PlaceBoatsController;
import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import com.battleship.models.game.FleetGridModel;

import com.battleship.models.game.GameConfigModel;
import com.battleship.models.game.GameModel;
import com.battleship.models.game.PlaceBoatsModel;

import com.battleship.views.app.GameConfigPanel;
import com.battleship.views.app.GamePanel;
import com.battleship.views.app.GridHexaView;
import com.battleship.views.app.GridPanel;
import com.battleship.views.app.GridSquareView;
import com.battleship.views.app.PlaceBoatsPanel;
import com.battleship.views.tools.PagePanel;
import com.battleship.views.tools.WindowFrame;
import java.awt.Dimension;
import javax.swing.JPanel;





/**
 * <h1>SwingFactory</h1>
 * <p>
 * public class SwingFactory<br/>
 * implements GameConstants
 * </p>
 * 
 * <p>
 * This class enable us to create view class with the specific controller and 
 * its model. Each time a View page is created, it need its controller and model. 
 * This class enable us to automatically create this items for the view.
 * </p>
 *
 * @date    Feb 25, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public abstract class SwingFactory implements GameConstants{
    //**************************************************************************
    // Data
    //**************************************************************************
    //Models 
    private static GameConfigModel  model_gameConfig            = null;
    private static PlaceBoatsModel  model_placeBoatsModel       = null;
    private static GameModel        model_gameModel             = null;
    
    //Views
    private static GameConfigPanel  view_gameConfigPanel        = null;
    private static PlaceBoatsPanel  view_placeBoatsPanel        = null;
    private static GamePanel        view_game                   = null;
    
    
    
    
    
    //**************************************************************************
    // Loading Functions for program used Models / Views / Controllers
    //**************************************************************************
    /**
     * Create Model / View / Controller for ConfigGamePanel
     * @param pFrame frame containing ConfigGamePanel
     * @return PagePanel ConfigGamePanel created
     * @throws ExecError if unable to create the panel
     */
    public static PagePanel loadConfigGame(WindowFrame pFrame) throws ExecError{
        if(SwingFactory.view_gameConfigPanel != null){
            DebugTrack.showExecMsg("ConfigGamePanel already loaded");
            return SwingFactory.view_gameConfigPanel;
        }
        GameConfigModel         m = new GameConfigModel();
        GameConfigController    c = new GameConfigController(m);
        GameConfigPanel         v = new GameConfigPanel(pFrame, c);
        m.addObserver(v);
        c.setView(v);
        v.initPage();
        SwingFactory.model_gameConfig     = m;
        SwingFactory.view_gameConfigPanel = v;
        return v;
    }
    
    
    /**
     * Create Model / View / Controller for PlaceBoatsPanel
     * @param pFrame    frame containing ConfigGamePanel
     * @return PagePanel PlaceBoatsPanel created
     * @throws ExecError if unable to create the panel
     */
    public static PagePanel loadPlaceBoats(WindowFrame pFrame) throws ExecError{
        if(SwingFactory.view_placeBoatsPanel != null){
            DebugTrack.showExecMsg("PlaceBoatsPanel already loaded");
            return SwingFactory.view_placeBoatsPanel;
        }
        if(SwingFactory.model_gameConfig == null){
            throw new ExecError(700, "gameConfig");
        }
        PlaceBoatsModel         m = new PlaceBoatsModel(SwingFactory.model_gameConfig);
        PlaceBoatsController    c = new PlaceBoatsController(m);
        PlaceBoatsPanel         v = new PlaceBoatsPanel(pFrame, c);
        m.addObserver(v);
        c.setView(v);
        v.initPage();
        SwingFactory.model_placeBoatsModel     = m;
        SwingFactory.view_placeBoatsPanel      = v;
        return v;
    }
    
    
    /**
     * Create Model / View / Controller for Game 
     * @param pFrame frame containing ConfigGamePanel
     * @return PagePanel PlaceBoatsPanel created
     * @throws ExecError if unable to create the panel
     */
    public static PagePanel loadGame(WindowFrame pFrame) throws ExecError{
        if(SwingFactory.view_game != null){
            DebugTrack.showExecMsg("Game already loaded");
            return SwingFactory.view_game;
        }
        if(SwingFactory.model_gameConfig == null){
            throw new ExecError(700, "gameConfig");
        }
        
        GameConfigModel     config      = SwingFactory.model_gameConfig;
        GameModel           m           = new GameModel(config);
        GameController      c           = new GameController(m);
        GamePanel           v           = new GamePanel(pFrame, c);
        m.addObserver(v);
        c.setView(v);
        v.initPage();
        SwingFactory.model_gameModel    = m;
        SwingFactory.view_game          = v;
        return v;
    }
    
    
    /**
     * Generate the view grid for game
     * @param parent
     * @param pModel
     * @param pDim
     * @return
     * @throws ExecError 
     */
    public static GridPanel loadGridPanel(JPanel parent, FleetGridModel pModel, Dimension pDim) 
    throws ExecError{
        if(SwingFactory.model_gameConfig == null){
            throw new ExecError(700, "gameConfig");
        }
        GameConfigModel config  = SwingFactory.model_gameConfig;
        int             width   = config.getGridWidth();
        int             height  = config.getGridHeight();
        int             type    = config.getGridType();
        
        GridController      c       = new GridController(pModel);
        GridPanel           v       = null;
        switch(type){
            case GameConstants.GRID_TYPE_SQUARE:
                v = new GridSquareView(parent, c, width, height, type, pDim);
                break;
            case GameConstants.GRID_TYPE_HEXAGON:
                v  = new GridHexaView(parent, c, width, height, type, pDim);
                break;
        }
        pModel.addObserver(v);
        c.setView(v);
        return v;
    }
}
