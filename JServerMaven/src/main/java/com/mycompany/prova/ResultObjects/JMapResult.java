/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prova.ResultObjects;

import java.util.List;

/**
 *
 * @author peduzzi_samuele
 */
public class JMapResult {
    
    private List orientationAngles;
    private List steps;

    public JMapResult() {
    }

    public JMapResult(List orientationAngles, List steps) {
        this.orientationAngles = orientationAngles;
        this.steps = steps;
    }

    public void setOrientationAngles(List orientationAngles) {
        this.orientationAngles = orientationAngles;
    }

    public void setSteps(List steps) {
        this.steps = steps;
    }
    
    

    public List getOrientationAngles() {
        return orientationAngles;
    }

    public List getSteps() {
        return steps;
    }

    @Override
    public String toString() {
        return "JMapResult{" + "orientationAngles=" + orientationAngles + ", steps=" + steps + '}';
    }
    
    
    
    
    
    
    
}
