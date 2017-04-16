package vince.majorprashant.Database;

import com.orm.SugarContext;
import com.orm.SugarRecord;

/**
 * Created by vince on 4/16/17.
 */
public class Location extends SugarRecord{

    private String name;
    private String phone;
    private double lat;
    private double lng;
    public Location() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
