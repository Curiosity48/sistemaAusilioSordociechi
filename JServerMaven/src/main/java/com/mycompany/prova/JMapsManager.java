/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prova;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.TravelMode;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author peduzzi_samuele
 */
public class JMapsManager {

    private GeoPosition position;
    private String apiKey;
    private GeoApiContext context;

    public JMapsManager(String apiKey) {

        try {
            this.apiKey = apiKey;
            position = new GeoPosition(0,0);
            context = new GeoApiContext.Builder()
                    .apiKey(apiKey)
                    .build();

        } catch (Exception ex) {
            Logger.getLogger(JMapsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean makeGeocodingRequest() {
        try {
            GeocodingResult[] results;
            results = GeocodingApi.geocode(context,
                    "Via Cadorna 45, IT 22060").await();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(results[0].addressComponents));
        } catch (Exception ex) {
            Logger.getLogger(JMapsManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    public String makeDirectionsRequest(String origin, String destination) {
        String strResult = "";
        try {
            DirectionsResult result;
            result = DirectionsApi.newRequest(context).origin(origin)
                    .destination(destination)
                    .mode(TravelMode.WALKING)
                    .await();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            strResult = gson.toJson(result);
        } catch (ApiException ex) {
            Logger.getLogger(JMapsManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(JMapsManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JMapsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return strResult;
    }

}
