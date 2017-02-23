/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AIproblemSolver;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * AI problem solving class, use .solve to get the solution of the problem
 * @author darven
 * @param <TState>
 *      State defined for the problem, restrict actions and state inputs
 */
public class AIProblem <TState extends AbstractState> {
    
    /*
    ACTIONS: list of action
    INIT_STATE: initial state
    ADD_MODE: mode used to add to the tree
    */
    private final LinkedList<AbstractAction<TState>> actions = new LinkedList<>();
    private final TState INIT_STATE;
    private final String ADD_MODE;
    /* SEARCH MODES
    COST_SEARCH: search by cost ?
    DEPTH_SEARCH
    WIDTH_SEARCH
    A_STAR_SEARCH: A* algorithm (https://en.wikipedia.org/wiki/A*_search_algorithm)
    */
    public static final String COST_SEARCH = "costSearch";
    public static final String DEPTH_SEARCH = "depth";
    public static final String WIDTH_SEARCH = "width";
    public static final String A_STAR_SEARCH = "aStarSearch";

    /**
     * CONSTRUCTOR
     * @param initState
     *      initial state
     */
    public AIProblem(TState initState) {
        this.ADD_MODE = "";
        this.INIT_STATE = initState;
    }
    /**
     * CONSTRUCTOR
     * @param initState
     *      initial state
     * @param addMode 
     *      how to add the new states to the search tree
     */
    public AIProblem(TState initState, String addMode) {
        this.ADD_MODE = addMode;
        this.INIT_STATE = initState;
    }
    /**
     * CONSTRUCTOR
     * @param actions
     *      actions available to solve the problem
     * @param initState
     *      initial state
     */
    public AIProblem(LinkedList<AbstractAction<TState>> actions, TState initState) {
        this.ADD_MODE = "default";
        this.actions.addAll(actions);
        this.INIT_STATE = initState;
    }
    /**
     * CONSTRUCTOR
     * @param actions
     *      actions available to solve the problem
     * @param initState
     *      initial state
     * @param addMode 
     *      how to add the new states to the search tree
     */
    public AIProblem(LinkedList<AbstractAction<TState>> actions, TState initState, String addMode) {
        this.ADD_MODE = addMode;
        this.actions.addAll(actions);
        this.INIT_STATE = initState;
    }
    
    /**
     * apply inputed action to the inputed state
     * @param state
     *      state to apply the action to
     * @param action
     *      action to apply to the state
     * @return 
     *      node with the new state and the action used to reach it (inputed 
     *      action), null if action not in the actions available in the problem
     */
    public Node applyAction(TState state, AbstractAction<TState> action){
        boolean isInActions = false;
        for (AbstractAction tempAction : actions){
            isInActions = isInActions || tempAction.equals(action);
        }
        
        if (isInActions){
            return action.apply(state);
        }else{
            return null;
        }
        
    }
    
    /**
     * try to solve the problem with available actions
     * @return 
     *      path used to reach the gaol state, null if no path existing to the 
     *      goal
     */
    public Path solve(){
        //
        Path path = new Path(new Node(null, INIT_STATE));
        //
        LinkedList<Path> tree=new LinkedList<>();
        tree.add(path);
        
        //
        boolean found=false;
        while(!found && !tree.isEmpty()){
            path = tree.pop();
            //System.out.println(path);
            
            //
            if(path.getCurrent().getState().isFinal()){
                found=true;
            }else{
                for(AbstractAction action : actions){
                    Node node =  applyAction((TState) path.getCurrent().getState(), action);
                    
                    this.addToTree(tree, new Path(path, node));
                }
            }
        }
        if (tree.isEmpty()){
            return null;
        }else{
            return path;
        }
    }
    
    /**
     * modifie l'arbre en live
     * @param tree
     *      linearised tree to which to add the new path
     * @param path 
     *      path to add to the tree
     */
    private void addToTree(LinkedList<Path> tree, Path path) {
        switch (ADD_MODE){
            
            default:
            case DEPTH_SEARCH:
                tree.addFirst(path);
                break;
            
            case WIDTH_SEARCH:
                tree.addLast(path);
                break;
            
            case COST_SEARCH:
                throw new UnsupportedOperationException("Cost search not supported yet!");
                //break;
            
            case A_STAR_SEARCH:
                throw new UnsupportedOperationException("A* search not supported yet!");
                //break;
        }
    }
    
    protected void addAction(AbstractAction<TState> action){
        this.actions.add(action);
    }
    protected void addAllAction(LinkedList<AbstractAction<TState>> actions){
        this.actions.addAll(actions);
    }
    
    @Override
    public String toString(){
        return ("AI problem:\n"
                + String.format(" * Actions: %s\n", this.actions.toString())
                + String.format(" * Initial state: %s\n", this.INIT_STATE.toString())
                + String.format(" * Search addition mode: %s", this.ADD_MODE));
    }
    
}
