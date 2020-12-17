package paijojr.learnadsadmob;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;

import java.util.ArrayList;
import java.util.List;

import paijojr.learnadsadmob.function.BannerFunction;
import paijojr.learnadsadmob.function.InterstitialFunction;
import paijojr.learnadsadmob.function.NativeFunction;
import paijojr.learnadsadmob.function.RewardFunction;
import paijojr.learnadsadmob.template.NativeTemplate;
import paijojr.learnadsadmob.template.TemplateView;

public class MainActivity extends AppCompatActivity {

    private BannerFunction bannerFunction;
    private InterstitialFunction interstitialFunction;
    private RewardFunction rewardFunction;
    private NativeFunction nativeFunction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAdConfiguration();
        Button btnCallInterst = findViewById(R.id.button_interstitial);
        Button btnCallReward = findViewById(R.id.button_reward);
        FrameLayout frameBanner = findViewById(R.id.frame_banner_ads);
        TemplateView templateView = findViewById(R.id.native_ads_container);

        bannerFunction = new BannerFunction(this, frameBanner);
        interstitialFunction = new InterstitialFunction(this);
        rewardFunction = new RewardFunction( this);
        nativeFunction = new NativeFunction(this, templateView);

        btnCallInterst.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
               interstitialFunction.callInterstitial();
            }
        });

        btnCallReward.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                rewardFunction.showReward();
            }
        });


    }

    private void initAdConfiguration() {
        List<String> testDevices = new ArrayList<>();
        testDevices.add(AdRequest.DEVICE_ID_EMULATOR);

        RequestConfiguration requestConfiguration
                = new RequestConfiguration.Builder()
                .setTestDeviceIds(testDevices)
                .build();
        MobileAds.setRequestConfiguration(requestConfiguration);
    }

    @Override
    public void onPause() {
        if (bannerFunction.adView != null) {
            bannerFunction.adView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (bannerFunction.adView != null) {
            bannerFunction.adView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (bannerFunction.adView != null) {
            bannerFunction.adView.destroy();
        }
        super.onDestroy();
    }
}