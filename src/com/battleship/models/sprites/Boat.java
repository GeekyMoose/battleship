/* 
 * Creation : Feb 11, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.models.sprites;

import com.battleship.behaviors.Sprite;
import com.battleship.constants.GameConstants;
import com.battleship.models.game.BoxMap;
import com.battleship.models.game.FleetGridModel;



/**
 * <h1>Boat</h1>
 * <p>
 * public abstract class Boat<br/>
 * implements Sprite
 * </p>
 *
 * <p>
 * Define a boat item. Every one in the fleet must extends boat.</p>
 * <ul>
 *  <li>AircraftCarrier</li>
 *  <li>Battleship</li>
 *  <li>Submarine</li>
 *  <li>Cruiser</li>
 *  <li>Destroyer</li>
 * </ul>
 *
 *
 * @date    Feb 11. 2015
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * @author  Contsantin MASSON
 */
public abstract class Boat implements GameConstants{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    protected String            kindOfBoat;
    protected int               nbLives;
    protected int               orientation;
    protected Compartment[]     tabCompartments;
    protected FleetGridModel    grid;
    
    
    
    
    
    //**************************************************************************
    // Constructors - Initialization
    //**************************************************************************
    /**
     * Create a new Boat. Boat has a default orientation, even if it is not
     * already placed on the grid. The FleetGridModel is the boat location (In
     * other words, it is the grid where he is).
     * Default orientation is horizontal.
     *
     * @param pName        Boat name (kind of boat, as Cruiser / Submarine ...)
     * @param pNbLives     Total number lives
     * @param pSize        Number of compartments occupy by the boat
     */
    protected Boat(String pName, int pNbLives, int pSize){
        this.kindOfBoat         = pName;
        this.nbLives            = pNbLives;
        this.orientation        = GameConstants.HORIZONTAL;
        this.grid               = null; //Bot is not placed at the moment
        this.tabCompartments    = new Compartment[pSize];
        for(int i = 0; i < pSize; i++) {
            tabCompartments[i] = new Compartment(null);
        }
    }
    
    
    
    
    
    //**************************************************************************
    // Check Functions - Usefull functions
    //**************************************************************************
    /**
     * Check if boat is dead
     * @return true if the boat has sinked (is dead), otherwise, false
     */
    public boolean isDead(){
        return (nbLives <= 0);
    }
    
    /**
     * Check if boat is placed on a grid
     * @return 
     */
    public boolean isPlaced(){
        return this.tabCompartments[0] != null;
    }

    /**
     * Takes one life from the boat, then, check if is dead
     *
     * @return true if the boat is dead, false otherwise
     */
    public boolean lostOneLife(){
        this.nbLives--;
        return this.isDead();
    }
    
    
    
    
    
    //**************************************************************************
    // Functions for placement
    //**************************************************************************
    /**
     * Check if position started at pBox map position is a valid position. 
     * (Using current orientation value)
     * @param pBox First box position
     * @return true if valid, otherwise, return false
     */
    private boolean isValidPosition(BoxMap pBox){
        return this.isValidPosition(pBox, this.orientation);
    }
    
    /**
     * Check if position started at pBox map position is a valid position. 
     * @param pBox          First box position
     * @param pOrientation  Boat orientation
     * @return true if valid, otherwise, return false
     */
    private boolean isValidPosition(BoxMap pBox, int pOrientation){
        BoxMap  next    = pBox;
        for(int k=0; k<this.tabCompartments.length;k++){
            if(next==null || !next.isEmpty()){
                return false;
            }
            next = pBox.getNextBoxMap(orientation);
        }
        return true;
    }
    
    /**
     * Place boat in a grid. At a BoxMap position. If position is not valid, nothing 
     * is done and false is returned. Orientation used is the current one. 
     * If boat was already placed on the grid, water replace old position
     * @param pBox first box position for this boat (Front box position)
     * @return true if placed successfully, otherwise, return false
     */
    public boolean placeAt(BoxMap pBox){
        if(!isValidPosition(pBox)){
            return false;
        }
        this.tabCompartments[0].setBoxPosition(pBox); //Place first position
        for(int k=1; k<this.tabCompartments.length;k++){
            this.tabCompartments[k].setBoxPosition(pBox.getNextBoxMap(orientation));
        }
        return true;
    }
    
    
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return BoxMap position of the front compartment
     * @return coordinates of the upper left boat compartment
     */
    public BoxMap getFrontPosition(){
        return tabCompartments[0].getBoxPosition();
    }

    /**
     * Return current number lives
     * @return current number lives
     */
    public int getNbLives(){
        return nbLives;
    }

    /**
     * Return current orientation
     * @return current orientation
     */
    public int getOrientation(){
        return orientation;
    }

    /**
     * Change current orientation. Boat position will be recalculated
     * @param pValue new orientation
     * @return 
     */
    public boolean setOrientation(int pValue){
        //If boat is not already on a grid, we can directly change orientation
        if(this.getFrontPosition() == null){
            this.orientation = pValue;
            return true;
        }
        else{
            if(this.isValidPosition(this.getFrontPosition(), pValue)){
                this.orientation = pValue;
                this.placeAt(this.getFrontPosition()); //replace with new orientation
                return true;
            }
        }
        return false;
    }
    
    
    
    
    
    //**************************************************************************
    // Inner class : Compartment
    //**************************************************************************
    /**
     * <h1>Boat.Compartment</h1>
     * <p>
     * protected class Compartment<br/>
     * implements Sprite
     * </p>
     * <p>
     * Boat is compound of compartment. Each compartment must be placed on the
     * map and could be hit, destroyed etc
     * </p>
     * 
     * @date    Feb 11. 2015
     * @author  Anthony CHAFFOT
     * @author  Jessica FAVIN
     * @author  Contsantin MASSON
     * 
     * @see Boat
     * @see Sprite
     */
    private class Compartment implements Sprite {
        private     boolean     isDestroyed;
        private     BoxMap      boxPosition;
        private     int[]       id_img;  //Image data (Identification)
        
        /**
         * Create a new compartment on BoxMap pPosition. Box map could be null, 
         * it means the boat is not placed at the moment
         *
         * @param pPosition BoxMap where to place this compartment
         */
        protected Compartment(BoxMap pPosition){
            this.isDestroyed    = false;
            this.boxPosition    = pPosition;
            this.id_img         = new int[NB_IMG];
        }


        /**
         * Check if compartment is destroyed
         *
         * @return true if destroyed, otherwise, return false
         */
        protected boolean isDestroyed(){
            return isDestroyed;
        }
        

        //**********************************************************************
        // Getters - Setters
        //**********************************************************************
        /**
         * Return the current position. Position is a BoxMap
         * @return BoxMap which is the position
         */
        protected BoxMap getBoxPosition(){
            return this.boxPosition;
        }
        
        /**
         * Set compartment position. If boat was already located on a grid, 
         * old position will be lost (And water replace this old position).
         * New position should be valid (Exists etc). 
         * If null given, this compartment is no longer in a grid
         * @param pPosition boxMap where compartment has to be placed
         */
        protected void setBoxPosition(BoxMap pPosition){
            if(this.boxPosition!=null){
                this.boxPosition.setContent(new Water());
            }
            this.boxPosition = pPosition;
            pPosition.setContent(this);
        }
        

        //**********************************************************************
        // Sprite implements 
        //**********************************************************************
        @Override
        public boolean hit(){
            if(this.isDestroyed){
                return false; //Already hit dude
            }
            Boat.this.lostOneLife();
            return true; //Boat hit
        }

        @Override
        public boolean canBeHit(){
            return isDestroyed;
        }

        @Override
        public int getImgId(int idImg){
            return (idImg<0 || idImg>NB_IMG)? id_img[0] : id_img[idImg];
        }

        @Override
        public void setImg(int pDef, int pDestroyed, int pHover, int pNovalid, int pValid){
            this.id_img[0] = pDef;
            this.id_img[1] = pDestroyed;
            this.id_img[2] = pHover;
            this.id_img[3] = pNovalid;
            this.id_img[4] = pValid;
        }


        @Override
        public void setHiddenImg(int pDef, int pDestroyed, int pHover, int pNovalid, int pValid){
            this.id_img[5] = pDef;
            this.id_img[6] = pDestroyed;
            this.id_img[7] = pHover;
            this.id_img[8] = pNovalid;
            this.id_img[9] = pValid;
        }
    } //----------------------------------END INNER CLASS-----------------------
}
