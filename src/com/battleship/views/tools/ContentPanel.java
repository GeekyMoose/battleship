/* 
 * Creation : Mar 11, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.views.tools;

import java.util.ArrayList;
import javax.swing.JPanel;





/**
 * <h1>ContentPanel</h1>
 * <p>
 * public abstract class ContentPanel<br/>
 * extends JPanel
 * </p>
 * <p>ContentPanel are placed into a PagePanel.</p>
 *
 * @since   Mar 13, 2015
 * @author  Constantin MASSON
 */
public abstract class ContentPanel extends JPanel implements UiElement{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     ArrayList<UiElement>    listUiElements;
    protected   JPanel                  parentPage;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new Content panel include in a PagePanel. (Or in nothing if 
     * null given)
     * @param pParentPage PagePanel which contains this ContentPanel
     */
    public ContentPanel(JPanel pParentPage){
        this.parentPage = pParentPage;
    }
    
    public ContentPanel(){
        this.parentPage = null;
    }
    
    
    
    
    
    //**************************************************************************
    //  Functions
    //**************************************************************************
    @Override
    public abstract void loadUI();

    @Override
    public abstract void reloadUI();
    
    
    
    
    
    //**************************************************************************
    // Geters - Setters
    //**************************************************************************
    /**
     * Get current parentPage
     * @return PagePanel parent
     */
    public JPanel getParentPage(){
        return this.parentPage;
    }
    
    /**
     * Set content parent
     * @param pParent  content parent
     */
    public void setParent(JPanel pParent){
        this.parentPage = pParent;
    }
}
