package paijojr.learnadsadmob;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import paijojr.learnadsadmob.function.BannerFunction;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameBanner;
    private BannerFunction bannerFunction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameBanner = findViewById(R.id.frame_banner_ads);
        bannerFunction = new BannerFunction(this, frameBanner);

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