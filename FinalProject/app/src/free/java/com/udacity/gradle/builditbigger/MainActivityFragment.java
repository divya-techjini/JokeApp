package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.databinding.FragmentMainBinding;

import udacity.com.libdisplay.DisplayJokeActivity;


public class MainActivityFragment extends Fragment implements View.OnClickListener {

    private InterstitialAd mInterstitialAd;
    private String mjoke;
    private FragmentMainBinding mBinding;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        mBinding.button.setOnClickListener(this);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mBinding.adView.loadAd(adRequest);
        mBinding.setShowloading(false);


        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                showAds();

                startJokeActivity(mjoke);
            }
        });

        showAds();


        return mBinding.getRoot();
    }

    private void showAds() {
        final AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

    public void tellJoke() {
        if (!mInterstitialAd.isLoaded() && mInterstitialAd.isLoading())
            showAds();
        mBinding.setShowloading(true);

        JokeAsyncTask endpointsAsyncTask = new JokeAsyncTask();
        endpointsAsyncTask.setListner(new JokeAsyncTask.TaskListner() {
            @Override
            public void getTaskResult(String result) {
                mBinding.setShowloading(false);
                mjoke = result;
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    if (result == null) {
                        Toast.makeText(getActivity(), getString(R.string.error), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    startJokeActivity(result);
                }


            }
        }).execute(getActivity());

    }

    private void startJokeActivity(String result) {
        Intent intent = new Intent(getActivity(), DisplayJokeActivity.class);
        intent.putExtra(DisplayJokeActivity.EXTRA_INTENT_JOKE, result);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        tellJoke();
    }
}
