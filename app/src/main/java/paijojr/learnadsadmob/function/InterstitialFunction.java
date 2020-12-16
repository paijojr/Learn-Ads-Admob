package paijojr.learnadsadmob.function;

import android.app.Activity;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import paijojr.learnadsadmob.R;

import static paijojr.learnadsadmob.function.MainFunction.checkNetwork;

public class InterstitialFunction {

    private final Activity activity;
    private final InterstitialAd mInterstitialAd;

    public InterstitialFunction (Activity activity) {
        this.activity = activity;

        mInterstitialAd = new InterstitialAd(activity);
        mInterstitialAd.setAdUnitId(activity.getString(R.string.interstitial_id_test));
        requestInterstitial();
    }

    private void requestInterstitial() {
        if(checkNetwork(activity)) {
            AdRequest adRequest = new AdRequest.Builder()
                    .build();
            mInterstitialAd.loadAd(adRequest);
            Log.d ("InterstitialFunction", "Network Available and Try Request Ads");
        } else {
            Log.d ("InterstitialFunction", "No Network Available");
        }
    }

    public void callInterstitial () {
        if (mInterstitialAd.isLoaded()) {
            Log.d ("InterstitialFunction", "Interstitial Available");
            mInterstitialAd.show();
        } else {
            Log.d ("InterstitialFunction", "Interstitial Not Available, Request Again");
            requestInterstitial();
        }
    }
}
