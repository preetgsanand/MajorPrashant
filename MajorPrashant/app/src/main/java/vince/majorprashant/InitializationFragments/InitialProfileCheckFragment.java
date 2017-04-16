package vince.majorprashant.InitializationFragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import vince.majorprashant.InitializationActivity;
import vince.majorprashant.R;


public class InitialProfileCheckFragment extends Fragment implements View.OnClickListener {
    private static String phoneNumber;
    static Handler handler;
    private ProgressDialog progress;
    private int fragmentCode;
    private View view;
    private Button retry,cont;
    private TextView title,subtitle;
    private ViewGroup.LayoutParams buttonCont,buttonRetry;

    public static InitialProfileCheckFragment newInstance(String phone) {
        InitialProfileCheckFragment fragment = new InitialProfileCheckFragment();
        phoneNumber = phone;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_initial_profile_check, container, false);
        initializeViews();
        return view;
    }


    private void initializeViews() {
        retry = (Button) view.findViewById(R.id.retry);
        cont = (Button) view.findViewById(R.id.continueOtp);
        title = (TextView) view.findViewById(R.id.checkTitle);
        subtitle = (TextView) view.findViewById(R.id.checkSubtitle);

        retry.setOnClickListener(this);
        cont.setOnClickListener(this);

        buttonCont= (ViewGroup.LayoutParams)cont.getLayoutParams();
        buttonRetry= (ViewGroup.LayoutParams)retry.getLayoutParams();

        progress = new ProgressDialog(getContext());
        progress.setMessage("Checking Server");
        progress.show();

        handler = new Handler();
        callCheckUserAPI(phoneNumber);
    }

    private void setRetry() {
        buttonRetry.height = 100;
        retry.setLayoutParams(buttonRetry);
        subtitle.setText("Cannot connect to server properly");
        title.setText("Check Failed");
        progress.dismiss();
    }

    private void setContinue(int code) {
        buttonCont.height = 100;
        cont.setLayoutParams(buttonCont);
        if (code == 0) {
            subtitle.setText("You are not registered with us");
        }
        progress.dismiss();
    }
    private void callCheckUserAPI(final String phoneNumber) {
        progress.show();
        ((InitializationActivity)getActivity()).callAPI(12,phoneNumber,null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.continueOtp:
                ((InitializationActivity) getActivity()).setFragment(fragmentCode);
            break;
            case R.id.retry:
                callCheckUserAPI(phoneNumber);
                break;
        }
    }

    public void setApiResult(int result) {
        progress.dismiss();
        switch (result) {
            case -1:setRetry();
                break;
            case 0:
                setContinue(0);
                break;
            case 1:fragmentCode = 4;
                setContinue(1);
                break;
        }
    }

}
