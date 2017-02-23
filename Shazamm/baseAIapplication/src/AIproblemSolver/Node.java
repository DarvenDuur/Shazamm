/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AIproblemSolver;

/**
 *
 * @author darven
 */
public class Node {
    private final AbstractAction action;
    private final AbstractState state;

    public Node(AbstractAction action, AbstractState state) {
        this.action = action;
        this.state = state;
    }

    /**
     * @return the state
     */
    public AbstractState getState() {
        return state;
    }

    /**
     * @return the action
     */
    public AbstractAction getAction() {
        return action;
    }
    
    @Override
    public String toString(){
        String actionText = "null";
        if (this.action != null){
            actionText = this.action.toString();
        }
        return "\nNode:\n * action: " + actionText + "\n * state: " + this.state.toString();
    }
}
