/* 
 * Creation:    Apr 1, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.asset.GridCalculator;
import com.battleship.controllers.GridController;
import com.battleship.exceptions.ExecError;
import com.battleship.observers.ObservableModel;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;



/**
 * <h1>GridHexaView</h1>
 * <p>
 * public class GridHexaView<br/>
 * extends GridPanel
 * </p>
 * <p>Display an hexagon grid</p>
 *
 * @date    Apr 1, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class GridHexaView extends GridPanel{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new Hexagon grid in view
     * @param pParent       parent PagePanel where grid is placed
     * @param pController   grid controller
     * @param pW            grid width
     * @param pH            grid height
     * @param pDim          dimension of one BoxMap
     * @param pType         grid type
     * @throws ExecError thrown if error during creation
     */
    public GridHexaView(JPanel pParent, GridController pController, 
                        int pW, int pH, int pType, Dimension pDim) throws ExecError{
        super(pParent, pController,pW, pH, pType, pDim);
    }


    @Override
    public void update(ObservableModel o, Object arg){
     
    }
    
    
    
    //**************************************************************************
    // Mouse Listener Event
    //**************************************************************************
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        this.cursor.mouseCursorMoved(e, controller);
    }
}