package vince.majorprashant;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.*;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.digits.sdk.android.Digits;
import com.firebase.client.Firebase;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.plus.model.people.Person;
import com.orm.SugarContext;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.models.*;

import io.fabric.sdk.android.Fabric;
import vince.majorprashant.Database.*;
import vince.majorprashant.Database.User;
import vince.majorprashant.InitializationFragments.InitialProfileCheckFragment;
import vince.majorprashant.InitializationFragments.IntroFragment;
import vince.majorprashant.InitializationFragments.OTPFragment;
import vince.majorprashant.Utils.API;
import vince.majorprashant.Utils.Utils;

public class InitializationActivity extends AppCompatActivity{

    private static final String TWITTER_KEY = "nrdZchPnbE5YPZMK5HmWbk8vc";
    private static final String TWITTER_SECRET = "1PWRtLjlTVzCXGf1AEh0Oo3plJragD5bcSkDIRAbnghsvxNBGY";
    public static String phoneNumber;
    private InitialProfileCheckFragment initialProfileCheckFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.activity = 2;
        setContentView(R.layout.activity_initialization);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits.Builder().build());
        SugarContext.init(this);
        setFragment(1);
    }


    public void setFragment(int code) {
        switch (code) {
            case 1:getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new IntroFragment()).commit();
                break;
            case 2:getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new OTPFragment()).addToBackStack(null).commit();
                break;
            case 4:
                if (User.findById(User.class,1).getRole().contains("2")) {
                      setAdminActivity();
                }
                else {
                    setMainActivity();
                }
            case 3:
                initialProfileCheckFragment = InitialProfileCheckFragment.newInstance(phoneNumber);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    initialProfileCheckFragment).addToBackStack(null).commit();
                break;
        }
    }

    public void setMainActivity() {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void askPermissions() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);

        }
    }
    public void setAdminActivity() {
        Intent intent = new Intent(getApplicationContext(),AdminActivity.class);
        startActivity(intent);
        finish();
    }


    public void callAPI(int code,String phoneNumber,User user) {
        API api = new API(code,InitializationActivity.this);
        if(phoneNumber != null) {
            api.setPhoneNumber(phoneNumber);
        }
        if(user != null) {
            api.setUser(user);
        }
        api.callAPI();
    }


    public void apiResult(int code,int result) {
        switch (code) {
            case 12:initialProfileCheckFragment.setApiResult(result);
                break;
        }
    }


}
