/* 
 * Creation:    Apr 1, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.views.app;

import com.battleship.controllers.GridController;
import com.battleship.exceptions.ExecError;
import com.battleship.observers.ObservableModel;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;





/**
 * <h1>GridSquareView</h1>
 * <p>public class GridSquareView</p>
 *
 * @author Constantin MASSON
 * @date Apr 1, 2015
 */
public class GridSquareView extends GridPanel{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private Dimension dimBox;
    
    
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new square grid in view
     * @param pParent       parent PagePanel where grid is placed
     * @param pController   grid controller
     * @param pW            grid width
     * @param pH            grid height
     * @param pDim          dimension of one BoxMap
     * @param pType         grid type
     * @throws ExecError thrown if error during creation
     */
    public GridSquareView(JPanel pParent, GridController pController, 
                        int pW, int pH, int pType, Dimension pDim) throws ExecError{
        super(pParent, pController,pW, pH, pType, pDim);
        this.dimBox = pDim;
    }


    @Override
    public void update(ObservableModel o, Object arg){
     
    }

    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    
    
    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
    }
}
