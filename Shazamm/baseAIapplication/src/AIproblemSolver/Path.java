/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AIproblemSolver;

import java.util.LinkedList;

/**
 * Path listing all states and actions used to reach the current state
 * @author darven
 */
public class Path {
    private final LinkedList<Node> PATH;

    /**
     * CONSTRUCTOR
     */
    public Path() {
        this.PATH = new LinkedList<>();
    }
    /**
     * @see Path()
     * @param path
     *      path to copy
     */
    public Path(Path path) {
        this.PATH = (LinkedList<Node>) path.PATH.clone();
    }
    /**
     * @see Path()
     * @param path
     *      path to copy
     * @param node
     *      node to add to the inputed path
     */
    public Path(Path path, Node node) {
        this.PATH = (LinkedList<Node>) path.PATH.clone();
        this.add(node);
    }
    /**
     * @see Path()
     * @param node
     *      node to add to the new path
     */
    public Path(Node node) {
        this();
        this.add(node);
    }
    
    /**
     * add inputed node to the path, as the new current state
     * @param node 
     *      node to add to the path
     */
    public void add(Node node){
        this.PATH.addFirst(node);
    }
    
    /**
     * 
     * @return 
     *      current state (first state of the path)
     */
    public Node getCurrent(){
        return this.PATH.peekFirst();
    }
    
    @Override
    public String toString(){
        return "Path length (action number): "+(this.PATH.size()-1)+"\nHead: "+this.PATH.getFirst().toString();
    }
    
    public String toString(boolean extension){
        if (!extension){
            return this.toString();
        }else{
            String text = "";
            
            for (int i = this.PATH.size() - 1; i >= 0 ; i--){
                text += String.format("\nNode %s: %s", this.PATH.size()-i-1,this.PATH.get(i).toString());
            }
            return "Path length (action number): "+(this.PATH.size()-1)+text;
        }
        
    }
}
