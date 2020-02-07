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
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    private DirectionsResult dirResult;

    public JMapsManager(String apiKey) {

        try {
            this.apiKey = apiKey;
            position = new GeoPosition(0, 0);
            context = new GeoApiContext.Builder()
                    .apiKey(apiKey)
                    .build();

            dirResult = null;

        } catch (Exception ex) {
            Logger.getLogger(JMapsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean makeGeocodingRequest() {
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

    public String eseguiRichiestaIstruzioniSordocieco(String origin, String destination) {

        String istructions = ""; //<-- Successivamente da sostituire con un  oggetto apposito contenente le istruzioni
        makeDirectionsRequestByFoot(origin, destination);

        return istructions;
    }

    private void makeDirectionsRequestByFoot(String origin, String destination) { //Richiesta e salvataggio dei WayPoints
        try {
            DirectionsResult result;
            result = DirectionsApi.newRequest(context).origin(origin)
                    .destination(destination)
                    .mode(TravelMode.WALKING)
                    .await();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            dirResult = result;
            getDirectionsAngles(result);
            //System.out.println(gson.toJson(result.routes)); //Stampa le coordinate dei wayPointa
        } catch (ApiException ex) {
            Logger.getLogger(JMapsManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(JMapsManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JMapsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List getDirectionsAngles(DirectionsResult result) { //Iterazioni per gli steps
        List angles = new ArrayList();
        for (int i = 0; i < result.routes.length; i++) { //Iterazione tra le routes
            DirectionsRoute route = result.routes[i];
            for (int j = 0; j < route.legs.length; j++) { //Iterazione tra le legs
                DirectionsLeg leg = route.legs[j];
                for (int k = 0; k < leg.steps.length; k++) { //Iterazione tra gli steps
                    DirectionsStep step = leg.steps[k];
                    //System.out.println(step.toString());
                    //System.out.println("startLocation -->" + step.startLocation);
                    //System.out.println("endLocation -->" + step.endLocation);
                    LatLng startLocation = step.startLocation; //Posizione di partenza dello step
                    LatLng endLocation = step.endLocation; //Posizione di arrivo dello step
                    Double angle = getDirectionAngle(startLocation, endLocation);
                }
            }
        }
        return angles;
    }

    private double getDirectionAngle(LatLng startLocation, LatLng endLocation) { //https://www.sunearthtools.com/it/tools/distance.php#top <-- Formula calcolo angolo
//      Δφ = ln( tan( latB / 2 + π / 4 ) / tan( latA / 2 + π / 4) )
//      Δlon = abs( lonA - lonB )
//      direzione :  θ = atan2( Δlon ,  Δφ )

        double startLat = startLocation.lat;
        double startLng = startLocation.lng;

        double endLat = endLocation.lat;
        double endLng = endLocation.lng;

        double angle = 0.0; //0 --> Avanti dritto 90 --> Destra 270 --> Sinistra 180 --> Indietro
        //double Δφ = Math.l
        
        
        return angle;        
    }

}
