package com.hc.hcppjoke.ui.sofa;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.hc.hcppjoke.R;
import com.hc.hcppjoke.ui.home.HomeViewModel;
import com.hc.libnavannotation.FragmentDestination;

/**
 * Created by hcw  on 2020/6/1
 * 类描述：
 * all rights reserved
 */
@FragmentDestination(pageUrl = "main/tabs/sofa")
public class SofaFragment extends Fragment {

    private static final String TAG = "HCTAG";
    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "sofa onCreateView: ");
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}