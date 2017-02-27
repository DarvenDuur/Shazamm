/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestJug;

import AIproblemSolver.AbstractState;

/**
 *
 * @author darven
 */
public class JugState extends AbstractState {
    private final int BUCKET_A_MAX;
    private final int BUCKET_B_MAX;
    private final int TARGET;
    private int bucketACurrent;
    private int bucketBCurrent;
    
    /**
     * Sets the buckets initial content to 0.
     * @param bucketAMax
     *      maximum volume bucket A can contain
     * @param bucketBMax 
     *      maximum volume bucket B can contain
     * @param target
     */
    public JugState(int bucketAMax, int bucketBMax, int target){
        this.BUCKET_A_MAX = bucketAMax;
        this.BUCKET_B_MAX = bucketBMax;
        this.TARGET = target;
        this.bucketACurrent = 0;
        this.bucketBCurrent = 0;
    }
    
    /**
     * Sets all the values attatched to the state
     * @param bucketAMax
     *      maximum volume bucket A can contain
     * @param bucketBMax 
     *      maximum volume bucket B can contain
     * @param bucketACurrent
     *      volume of bucket A
     * @param bucketBCurrent
     *      volume of bucket B
     */
    public JugState(int bucketAMax, int bucketBMax, int target, int bucketACurrent, int bucketBCurrent){
        this.BUCKET_A_MAX = bucketAMax;
        this.BUCKET_B_MAX = bucketBMax;
        this.TARGET = target;
        this.setBucketACurrent(bucketACurrent);
        this.setBucketBCurrent(bucketBCurrent);
    }

    /**
     * Copy state
     * @param state
     *      JugState to copy
     */
    public JugState(JugState state){
        this.BUCKET_A_MAX = state.getBucketAMax();
        this.BUCKET_B_MAX = state.getBucketBMax();
        this.TARGET = state.TARGET;
        this.setBucketACurrent(state.getBucketACurrent());
        this.setBucketBCurrent(state.getBucketBCurrent());
    }
    
    /**
     * Copy state and set the new values for the content of thr buckets
     * @param state
     *      JugState to copy
     * @param bucketACurrent
     *      volume of bucket A
     * @param bucketBCurrent
     *      volume of bucket B
     */
    public JugState(JugState state, int bucketACurrent, int bucketBCurrent){
        this.BUCKET_A_MAX = state.getBucketAMax();
        this.BUCKET_B_MAX = state.getBucketBMax();
        this.TARGET = state.TARGET;
        this.setBucketACurrent(bucketACurrent);
        this.setBucketBCurrent(bucketBCurrent);
    }
    
    @Override
    public boolean isFinal() {
        return (this.getBucketACurrent() == this.TARGET) || (this.getBucketBCurrent() == this.TARGET);
    }

    @Override
    public String toString() {
        return String.format("Bucket A: %s/%s; ", ""+this.getBucketACurrent(), ""+this.getBucketAMax()) +
                String.format("Bucket B: %s/%s", ""+this.getBucketBCurrent(), ""+this.getBucketBMax());
    }

    /**
     * @return 
     *      maximum volume bucket A can contain
     */
    public int getBucketAMax() {
        return BUCKET_A_MAX;
    }
    /**
     * @return 
     *      maximum volume bucket B can contain
     */
    public int getBucketBMax() {
        return BUCKET_B_MAX;
    }

    /**
     * @return 
     *      current volume contained in bucket A
     */
    public int getBucketACurrent() {
        return bucketACurrent;
    }
    /**
     * @param bucketACurrent
     *      new volume of bucket A
     */
    public void setBucketACurrent(int bucketACurrent) {
        this.bucketACurrent = bucketACurrent;
        if (this.bucketACurrent<0){
            this.bucketACurrent = 0;
        }else if (this.bucketACurrent>this.BUCKET_A_MAX){
            this.bucketACurrent = this.BUCKET_A_MAX;
        }
    }

    /**
     * @return 
     *      current volume contained in bucket B
     */
    public int getBucketBCurrent() {
        return bucketBCurrent;
    }
    /**
     * @param bucketBCurrent
     *      new volume of bucket B
     */
    public void setBucketBCurrent(int bucketBCurrent) {
        this.bucketBCurrent = bucketBCurrent;
        if (this.bucketBCurrent<0){
            this.bucketBCurrent = 0;
        }else if (this.bucketBCurrent>this.BUCKET_B_MAX){
            this.bucketBCurrent = this.BUCKET_B_MAX;
        }
    }
    
}
