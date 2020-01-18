/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prova;

/**
 *
 * @author peduzzi_samuele
 */
public class JServerSordociechi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JMapsManager mM = new JMapsManager("AIzaSyBnqC6wcZ2_HAWp4XgllXXPC3IEJ9xouAs");
        mM.makeDirectionsRequest("Milan", "London"); //Test Milano a londra
        
        
    }
    
}
