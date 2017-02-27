/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AIproblemSolver;

/**
 * State used in a AI solving problem
 *      .isFinal() will be used to determine if the state fits the goal 
 *      conditions of the algorythm
 * @author darven
 */
public abstract class AbstractState {
    
    /**
     * returns true if the state is af goal state of the problem
     * @return 
     *      true if state is a final state
     */
    abstract public boolean isFinal();
    
    @Override
    abstract public String toString();
}
