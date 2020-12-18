package paijojr.learnadsadmob.function;

import android.app.Activity;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.NativeAdOptions;

import paijojr.learnadsadmob.R;
import paijojr.learnadsadmob.template.NativeTemplate;
import paijojr.learnadsadmob.template.TemplateView;

/*
 * This repo is learn how to implement ads from Admob in Android Studio with java languages
 * https://github.com/paijojr/Learn-Ads-Admob
 * Native Ads Function
 */

public class NativeFunction {
    private final Activity activity;
    private final TemplateView templateView;

    public NativeFunction (Activity activity, TemplateView templateView) {
        this.activity = activity;
        this.templateView = templateView;
        showNative();
    }

    private void showNative () {
        AdLoader adLoader = new AdLoader.Builder(activity, activity.getString(R.string.native_id_test))
                .forUnifiedNativeAd(unifiedNativeAd -> {
                    NativeTemplate styles = new
                            NativeTemplate.Builder().build();
                    templateView.setVisibility(View.VISIBLE);
                    templateView.setStyles(styles);
                    templateView.setNativeAd(unifiedNativeAd);
                })
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        templateView.setVisibility(View.GONE);
                        // Handle the failure by logging, altering the UI, and so on.
                    }
                })
                .withNativeAdOptions(new NativeAdOptions.Builder()
                        // Methods in the NativeAdOptions.Builder class can be
                        // used here to specify individual options settings.
                        .build())
                .build();
        adLoader.loadAd(new AdRequest.Builder().build());
    }

}
