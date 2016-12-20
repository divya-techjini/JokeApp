package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.udacity.gradle.builditbigger.databinding.FragmentMainBinding;

import udacity.com.libdisplay.DisplayJokeActivity;


public class MainActivityFragment extends Fragment implements View.OnClickListener {

    private FragmentMainBinding mBinding;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        mBinding.button.setOnClickListener(this);
        mBinding.setShowloading(false);

        return mBinding.getRoot();
    }

    @Override
    public void onClick(View v) {
        mBinding.setShowloading(true);
        JokeAsyncTask endpointsAsyncTask = new JokeAsyncTask();
        endpointsAsyncTask.setListner(new JokeAsyncTask.TaskListner() {
            @Override
            public void getTaskResult(String result) {
                mBinding.setShowloading(false);
                if (result == null) {
                    Toast.makeText(getActivity(), getString(R.string.error), Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getActivity(), DisplayJokeActivity.class);
                intent.putExtra(DisplayJokeActivity.EXTRA_INTENT_JOKE, result);
                startActivity(intent);

            }
        }).execute(getActivity());
    }
}
