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
import java.awt.Point;
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

    private LatLng position;
    private String apiKey;
    private GeoApiContext context;

    private DirectionsResult dirResult;

    public JMapsManager(String apiKey) {

        try {
            this.apiKey = apiKey;
            position = new LatLng(0, 0);
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

    public JMapResult eseguiRichiestaIstruzioniSordocieco(String origin, String destination) {
        
        JMapResult myResult  = new JMapResult(); //<-- Successivamente da sostituire con un  oggetto apposito contenente le istruzioni
        myResult = makeDirectionsRequestByFoot(origin, destination);
        
        return myResult;
    }

    private JMapResult makeDirectionsRequestByFoot(String origin, String destination) { //Richiesta e salvataggio dei WayPoints
        JMapResult mapResult = new JMapResult();
        try {
            DirectionsResult result;
            result = DirectionsApi.newRequest(context).origin(origin)
                    .destination(destination)
                    .mode(TravelMode.WALKING)
                    .await();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            dirResult = result;
            List angles = getDirectionsAngles(result);
            mapResult.setOrientationAngles(angles);
            //System.out.println(gson.toJson(result.routes)); //Stampa le coordinate dei wayPointa
        } catch (ApiException ex) {
            Logger.getLogger(JMapsManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(JMapsManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JMapsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapResult;
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
                    double angle = getDirectionAngle(startLocation, endLocation);
                    angles.add(angle);
                }
            }
        }
        return angles;
    }

    private double getDirectionAngle(LatLng startLocation, LatLng endLocation) { //https://www.sunearthtools.com/it/tools/distance.php#top <-- Formula calcolo angolo
//      Δφ = ln( tan( latB / 2 + π / 4 ) / tan( latA / 2 + π / 4) )
//      Δlon = abs( lonA - lonB )
//      direzione :  θ = atan2( Δlon ,  Δφ )

        double angle = 0.0f; //0 --> Avanti dritto 90 --> Destra 270 --> Sinistra 180 --> Indietro

        double startLat = startLocation.lat;
        double startLng = startLocation.lng;

        double endLat = endLocation.lat;
        double endLng = endLocation.lng;
        
        angle = calculateAngle(startLng, startLat, endLng, endLat);
//        
//        double Δφ = Math.log( Math.tan(endLat / 2 + Math.PI / 4) / Math.tan(startLat / 2 + Math.PI / 4) );
//        double Δlon = Math.abs(startLng - endLng);
//        double direzione = Math.atan2(Δlon,Δφ);
//        
//        //if(direzione > 0 && direzione < 360)
//        angle = direzione;
        return angle;
    }

    private static double calculateAngle(double x1, double y1, double x2, double y2) { //<-- Funzionante ma impreciso
        double angle = Math.toDegrees(Math.atan2(x2 - x1, y2 - y1));
        // Keep angle between 0 and 360
        angle = angle + Math.ceil(-angle / 360) * 360;

        return angle;
    }

}
