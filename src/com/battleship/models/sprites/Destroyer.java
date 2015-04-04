/* 
 * Creation : Feb 11, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.models.sprites;





/**
 * <h1>Destroyer</h1>
 * <p>
 * public class Destroyer<br/>
 * extends Boat
 * </p>
 * 
 * <h2>Destroyer features</h2>
 *  <ul>
 *      <li>Size : 2</li>
 *      <li>Number Lives : 2</li>
 *  </ul>
 * 
 * @date    Feb 11. 2015
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * @author  Contsantin MASSON
 */
public class Destroyer extends Boat {
    
    /**
     * Create a new Destroyer
     */
    public Destroyer(){
        super("Destroyer", 2, 2);
    }
}
