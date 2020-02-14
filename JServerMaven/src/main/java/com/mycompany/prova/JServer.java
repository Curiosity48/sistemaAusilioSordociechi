/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prova;

import com.mycompany.prova.ResultObjects.JMapResult;
import com.google.maps.model.LatLng;

import com.mycompany.prova.JMapsManager;
import com.mycompany.prova.RequestObjects.ClientOrientationsRequest;

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
        ClientOrientationsRequest request = getClientRequest();
        String origin = request.getOriginLocation();
        String destination = request.getDestinationLocation();
        
        getOrientationsFromApiAndSendToTheClient(origin, destination);
//        LatLng startLocation = new LatLng(45.687420, 9.179614);            TEST  
//        LatLng endLocation = new LatLng(45.68757, 9.18889);
//        System.out.println(mapsMng.getDirectionAngle(startLocation, endLocation));
    }
    
    private ClientOrientationsRequest getClientRequest() {
        
        ClientOrientationsRequest request = new ClientOrientationsRequest("orientationType","Via Santa Caterina da Siena, Mariano Comense, CO, Italia","Mariano Comense - Stazione Trenord, Mariano Comense, CO");
        //TCP gets
        return request;
    }
    
    private void getOrientationsFromApiAndSendToTheClient(String origin, String destination) {
        JMapResult result = eseguiRichiestaIstruzioniOrientamento(origin, destination);
        System.out.println(result.toString());
        sendOrientationsToTheClient(result);
    }
    
    private JMapResult eseguiRichiestaIstruzioniOrientamento(String origin, String destination) {
        

        JMapResult result = mapsMng.eseguiRichiestaIstruzioniSordocieco(origin, destination);
        return result;
    }
    
    private void sendOrientationsToTheClient(JMapResult result) {
        
        
    }
    
    

}
