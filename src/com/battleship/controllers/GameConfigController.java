/* 
 * Creation:    Feb 25, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.controllers;

import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import com.battleship.models.game.GameConfigModel;





/**
 * <h1>GameConfigController</h1>
 * <p>
 * public class GameConfigController<br/>
 * extends Controller
 * </p>
 * 
 * <p>Controller for ConfigGame model</p>
 *
 * @date    Feb 25, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 * @see     GameConfigModel
 * @see     GameConfigView
 */
public class GameConfigController extends Controller{
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new controller for GameConfig
     * @param pModel Model managed by this controller
     * @throws ExecError throws if pView or pMode is null
     */
    public GameConfigController(GameConfigModel pModel) throws ExecError{
        super(pModel);
        DebugTrack.showInitMsg("Create GameConfigController controller");
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Reset configuration to default state
     */
    public void resetDefaultConfig(){
        ((GameConfigModel)this.model).resetConfig();
    }
    
    /**
     * Check if config is valid
     * @return true if valid, otherwise, return false
     */
    public boolean isValidConfig(){
        return ((GameConfigModel)this.model).isValid();
    }
    
    /**
     * Change current grid width by new value.
     * See model function for further information
     * @param pValue new value
     */
    public void changeGridWidth(int pValue){
        ((GameConfigModel)this.model).setGridWidth(pValue);
    }
    
    /**
     * Change current grid height by new value.
     * See model function for further information
     * @param pValue new value
     */
    public void changeGridHeight(int pValue){
        ((GameConfigModel)this.model).setGridHeight(pValue);
    }
    
    /**
     * Change current grid type by new one.
     * See model function for further information
     * @param pValue new value
     */
    public void changeGridType(int pValue){
        ((GameConfigModel)this.model).setGridType(pValue);
    }
}
