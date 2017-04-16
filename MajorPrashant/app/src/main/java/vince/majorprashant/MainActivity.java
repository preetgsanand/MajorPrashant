package vince.majorprashant;

import android.content.Intent;
import android.location.Location;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;
import com.orm.SugarContext;

import vince.majorprashant.Database.User;

public class MainActivity extends AppCompatActivity implements LocationProvider.Provider{

    public static String firebase_url = "https://major-cf652.firebaseio.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SugarContext.init(this);
        MultiDex.install(this);
        Firebase.setAndroidContext(this);
        checkIntitalSetup();
    }

    private void checkIntitalSetup() {
        User user = User.findById(User.class,1);
        if(user == null) {
            callInitializationActivity();
        }
    }

    private void callInitializationActivity() {
        Intent intent = new Intent(getApplicationContext(),InitializationActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void setNewLocation(Location location) {
        Firebase ref = new Firebase(firebase_url);

        vince.majorprashant.Database.Location locationDB = new vince.majorprashant.Database.Location();
        User user = User.findById(User.class,1);
        locationDB.setName(user.getName());
        locationDB.setPhone(user.getPhone());

        //Getting values to store
        double lat = location.getLatitude();
        double lng = location.getLongitude();

        locationDB.setLat(lat);
        locationDB.setLng(lng);
        locationDB.save();

        //Storing values to firebase
        ref.child("users").child(user.getPhone()).setValue(locationDB);
    }
}
