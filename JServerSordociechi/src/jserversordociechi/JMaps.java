/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jserversordociechi;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author peduzzi_samuele
 */
public class JMaps {

    private GeoPosition position;

    public JMaps() {

        position = new GeoPosition(0, 0);
    }

    public void sendGoogleMapsHttpRequest(String requestString) {

        try {
            URL url;
            url = new URL(requestString);
            HttpsConnection conn = (HttpsConnection) url.openConnection();
            InputStream stream = conn.getInputStream();
        } 
        catch (Exception ex) {
            Logger.getLogger(JMaps.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
