/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models.game;

import com.battleship.asset.CheatCode;
import com.battleship.asset.Session;
import com.battleship.constants.GameConstants;
import com.battleship.main.DebugTrack;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.Timer;





/**
 * <h1>GameModel</h1>
 * <p>
 * public class GameModel<br/>
 * extends Model<br/>
 * implements GameConstants
 * </p>
 * 
 * <p>
 * This class manage the game. Game is set by a configuration which is 
 * a GameConfigModel class.
 * </p>
 *
 * @date    Feb 9, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public class GameModel extends Model implements GameConstants{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    //Runtime constants
    public      static final int    SWITCH_UPDATE           = 0;
    public      static final int    SWITCH_TURN             = 1;
    public      static final int    SWITCH_PAGE             = 2;
    public      static final int    GAME_OVER               = 3;
    public      static final int    GAME_VICTORY            = 4;
    public      static final int    SWITCH_BEHAVIORS        = 5;
    
    //Variables
    private     GameConfigModel     config;
    private     final int           gridType;
    private     final int           gridWidth;
    private     final int           gridHeight;
    
    private     Player[]            listPlayers;
    private     int                 counterTurn;
    private     int                 currentPlayerTurn;
    
    
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Constructor: create a new GameModel from a GameConfigModel class<br/>
     * Set the configuration from GameConfigModel. After creation, basement config 
     * could not be changed anymore (As gridWidth, gridHeight and gridType)
     * @param pConfig configuration to set
     */
    public GameModel(GameConfigModel pConfig) {
        DebugTrack.showInitMsg("Create GameModel");
        CheatCode.setData(this);
        this.config             = pConfig;
        this.gridWidth          = pConfig.getGridWidth();
        this.gridHeight         = pConfig.getGridHeight();
        this.gridType           = pConfig.getGridType();
        this.listPlayers        = pConfig.getPlayers();
        
        this.counterTurn        = 1;
        this.currentPlayerTurn  = pConfig.getFirstPlayerTurn();
        
        //Add this GameModel in every player and Fleet of player
        for (Player p : this.listPlayers){
            p.setGameModel(this);
            p.getFleet().setGame(this);
        }
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Switch turn behavior. It depend of current game mode
     * <ul>
     *  <li>Mode AI : start AI shoot</li>
     *  <li>Mode V2 : display a switching panel</li>
     *  <li>Mode LAN : do nothing, juste wait for other player shot</li>
     * </ul>
     * 
     */
    public void switchTurnBehaviors(){
        int     foeIndex        = (this.currentPlayerTurn+1)%2;  //Works only for 2 players
        Player  foe             = this.listPlayers[foeIndex];
        int     mode            = Session.getGameMode();
        
        switch(mode){
            case GameConstants.MODE_AI:
                notifyObservers(GameModel.SWITCH_BEHAVIORS);
                DoBreak doBreak = new DoBreak(GameConstants.DELAY_SWITCH_BREAK);
                doBreak.start();
                break;
                
            case GameConstants.MODE_V2:
                notifyObservers(GameModel.SWITCH_BEHAVIORS);
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        try {
                            Thread.sleep(GameConstants.DELAY_SWITCH_BREAK);
                        } catch(InterruptedException ex) {
                        }
                        if(listPlayers[currentPlayerTurn].getFleet().isFleetDestroyed()){
                            Session.getSession().earMoneyFromScore(Session.getPlayer().getScore());
                            notifyObservers(GameModel.GAME_OVER);
                            return;
                        } else if(foe.getFleet().isFleetDestroyed()){
                            Session.getSession().earMoneyFromScore(Session.getPlayer().getScore());
                            notifyObservers(GameModel.GAME_VICTORY);
                            return;
                        }
                        counterTurn++;
                        currentPlayerTurn  = foeIndex;
                        notifyObservers(GameModel.SWITCH_PAGE);
                    }
                });
                break;
            case GameConstants.MODE_LAN:
                break;
        }
    }
    
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return game config used by this game
     * @return 
     */
    public GameConfigModel getConfig(){
        return this.config;
    }
    
    /**
     * Return nb turn player
     * @return 
     */
    public int getNbTurn(){
        return this.counterTurn;
    }
    
    /**
     * Return id of current player turn
     * @return int id current player turn
     */
    public int getIdPlayerTurn(){
        return this.currentPlayerTurn;
    }
    
    /**
     * Return the current player turn
     * @return Player current player
     */
    public Player getPlayerTurn(){
        return this.listPlayers[this.currentPlayerTurn];
    }
    
    
    //**************************************************************************
    // Inner Class : Temporary solution, it's a little bit ugly
    //**************************************************************************
    private class DoBreak implements ActionListener{
        private final Timer timer;
        
        public DoBreak(int pDelay){
            this.timer = new Timer(pDelay, this);
        }
        
        @Override
        public void actionPerformed(ActionEvent e){
            int     foeIndex        = (currentPlayerTurn+1)%2;  //Works only for 2 players
            Player  foe             = listPlayers[foeIndex];
            if(Session.getPlayer().getFleet().isFleetDestroyed()){
                Session.getSession().earMoneyFromScore(Session.getPlayer().getScore());
                notifyObservers(GameModel.GAME_OVER);
                return;
            } 
            else if(foe.getFleet().isFleetDestroyed()){
                Session.getSession().earMoneyFromScore(Session.getPlayer().getScore());
                notifyObservers(GameModel.GAME_VICTORY);
                return;
            }
            counterTurn++;
            currentPlayerTurn  = foeIndex;
            notifyObservers(GameModel.SWITCH_BEHAVIORS);
            //AI player shoot on session player
            if(foe instanceof PlayerAI){
                DebugTrack.showExecMsg("AI Turn");
                counterTurn++;
                currentPlayerTurn  = 0;
                ((PlayerAI)foe).processAiShoot(Session.getPlayer().getFleet().getTabBoxMap());
                //notifyObservers(GameModel.SWITCH_TURN);
            }
            this.timer.stop();
        }
        
        public void start(){
            this.timer.start();
        }
    }
}
