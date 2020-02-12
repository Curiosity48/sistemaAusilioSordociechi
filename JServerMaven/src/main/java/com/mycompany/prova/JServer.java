/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prova;

import com.google.maps.model.LatLng;

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
        String origin = "Via Santa Caterina da Siena, Mariano Comense, CO, Italia";
        String destination = "Mariano Comense - Stazione Trenord, Mariano Comense, CO";
        System.out.println(mapsMng.eseguiRichiestaIstruzioniSordocieco(origin, destination));
        
//        LatLng startLocation = new LatLng(45.687420, 9.179614);            TEST  
//        LatLng endLocation = new LatLng(45.68757, 9.18889);
//
//        
//        System.out.println(mapsMng.getDirectionAngle(startLocation, endLocation));
    }
    
    
    
    
}
