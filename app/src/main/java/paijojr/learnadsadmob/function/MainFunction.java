package paijojr.learnadsadmob.function;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/*
 * This repo is learn how to implement ads from Admob in Android Studio with java languages
 * https://github.com/paijojr/Learn-Ads-Admob
 * Main Function is here
 */

public class MainFunction {

    public static boolean checkNetwork (Activity activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if(netInfos != null)
                if(netInfos.isConnected())
                    return true;
        }
        return false;
    }

}
