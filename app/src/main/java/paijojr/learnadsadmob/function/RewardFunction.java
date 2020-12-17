package paijojr.learnadsadmob.function;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import paijojr.learnadsadmob.R;

import static paijojr.learnadsadmob.function.MainFunction.checkNetwork;

public class RewardFunction {
    private Activity activity;
    private RewardedAd rewardedAd;

    public RewardFunction (Activity activity) {
        this.activity = activity;

        createAndLoadRewardedAd();
    }

    public void showReward () {
        if(checkNetwork(activity)) {
            if (rewardedAd.isLoaded()) {
                Activity activityContext = activity;
                RewardedAdCallback adCallback = new RewardedAdCallback() {
                    @Override
                    public void onRewardedAdOpened() {
                        // Ad opened.
                        Log.d("RewardFunction", "Ad opened");
                    }

                    @Override
                    public void onRewardedAdClosed() {
                        // Ad closed.
                        Log.d("RewardFunction", "Ad closed");
                        createAndLoadRewardedAd();
                        //rewardedAd = createAndLoadRewardedAd();
                    }

                    @Override
                    public void onUserEarnedReward(@NonNull RewardItem reward) {
                        // User earned reward.
                        Log.d("RewardFunction", "User earned reward");
                    }

                    @Override
                    public void onRewardedAdFailedToShow(AdError adError) {
                        // Ad failed to display.
                        Log.d("RewardFunction", "Ad failed to display");
                    }
                };
                rewardedAd.show(activityContext, adCallback);
            } else {
                Log.d("RewardFunction", "The rewarded ad wasn't loaded yet.");
            }
        } else {
            Log.d("RewardFunction", "No Connection Internet");
        }
    }

    private RewardedAd createAndLoadRewardedAd() {
        rewardedAd = new RewardedAd(activity, activity.getString(R.string.reward_id_test));
        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad successfully loaded.
                Log.d("RewardFunction", "Ad successfully loaded");
            }

            @Override
            public void onRewardedAdFailedToLoad(LoadAdError adError) {
                // Ad failed to load.
                Log.d("RewardFunction", "Ad failed to load");
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
        return rewardedAd;
    }

}
