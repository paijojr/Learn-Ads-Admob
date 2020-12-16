package paijojr.learnadsadmob.function;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;

import java.util.ArrayList;
import java.util.List;

import paijojr.learnadsadmob.R;

public class BannerFunction {

    private final Activity activity;
    public final AdView adView;
    private FrameLayout adContainerView;

    public BannerFunction (Activity activity, FrameLayout adContainerView) {
        this.activity = activity;
        this.adContainerView = adContainerView;

        adView = new AdView(activity);
        adView.setAdUnitId(activity.getString(R.string.banner_id_test));
        adContainerView.addView(adView);
        loadBanner();
    }

    private void loadBanner() {
        List<String> testDevices = new ArrayList<>();
        testDevices.add(AdRequest.DEVICE_ID_EMULATOR);

        RequestConfiguration requestConfiguration
                = new RequestConfiguration.Builder()
                .setTestDeviceIds(testDevices)
                .build();
        MobileAds.setRequestConfiguration(requestConfiguration);

        AdSize adSize = getAdSize();
        adView.setAdSize(adSize);

        adView.loadAd(new AdRequest.Builder().build());
    }

    private AdSize getAdSize() {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth);
    }


}
