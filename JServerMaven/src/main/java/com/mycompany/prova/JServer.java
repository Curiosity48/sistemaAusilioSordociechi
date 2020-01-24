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
public class JServer {
    
    private String apiKey;
    private JMapsManager mapsMng; 

    public JServer() {
        apiKey = "AIzaSyBnqC6wcZ2_HAWp4XgllXXPC3IEJ9xouAs";
        mapsMng = new JMapsManager(apiKey);
    }
    
    public void execServerRoutine() {
        String origin = "Asso";
        String destination = "Mariano";
        mapsMng.makeDirectionsRequest(origin, destination);
    }
    
    
    
    
}
