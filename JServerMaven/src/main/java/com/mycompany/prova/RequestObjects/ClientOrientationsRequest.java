/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prova.RequestObjects;

/**
 *
 * @author peduzzi_samuele
 */
public class ClientOrientationsRequest {
    
    private String requestType;
    private String originLocation;
    private String destinationLocation;

    public ClientOrientationsRequest(String requestType, String originLocation, String destinationLocation) {
        this.requestType = requestType;
        this.originLocation = originLocation;
        this.destinationLocation = destinationLocation;
    }

    public String getRequestType() {
        return requestType;
    }

    public String getOriginLocation() {
        return originLocation;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }
    
    
    
    
    
    
}
