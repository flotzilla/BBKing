package com.flyonthewall.entity;

/**
 * Created with IntelliJ IDEA.
 * User: obyte
 * Date: 31.05.13
 * Time: 23:48
 * To change this template use File | Settings | File Templates.
 */
public class Approach {
    private float mass;
    private int minRepetsCount;
    private int maxepetsCount;

    public Approach(float mass, int minRepetsCount, int maxepetsCount) {
        this.mass = mass;
        this.minRepetsCount = minRepetsCount;
        this.maxepetsCount = maxepetsCount;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public int getMinRepetsCount() {
        return minRepetsCount;
    }

    public void setMinRepetsCount(int minRepetsCount) {
        this.minRepetsCount = minRepetsCount;
    }

    public int getMaxRepetsCount() {
        return maxepetsCount;
    }

    public void setMaxRepetsCount(int maxepetsCount) {
        this.maxepetsCount = maxepetsCount;
    }

    public String toString(){
        if (maxepetsCount == 0){
            return mass + " x " + minRepetsCount;
        }else{
            return mass + " x " + minRepetsCount + " - " + maxepetsCount;
        }

    }
}
