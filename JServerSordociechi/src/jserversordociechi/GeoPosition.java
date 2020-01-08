/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jserversordociechi;

/**
 *
 * @author peduzzi_samuele
 */
public class GeoPosition {

    private  double longitude;
    private  double latitude;
    
    public GeoPosition(double longitude, double latitude) {
        super();
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }
    
    public double getLongitude() {
        return longitude;
    }
    
    public void setLatitude(double latitude ) {
        this.latitude = latitude;
    }
    
    public void setLongitude(double latitude ) {
        this.latitude = latitude;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GeoPosition other = (GeoPosition) obj;
        if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
            return false;
        if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "GeoPosition [longitude=" + longitude + ", latitude=" + latitude + "]";
    }

}
