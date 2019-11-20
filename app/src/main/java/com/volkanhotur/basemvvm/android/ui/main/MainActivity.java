package com.volkanhotur.basemvvm.android.ui.main;

import android.os.Bundle;
import android.view.View;

import com.volkanhotur.basemvvm.R;
import com.volkanhotur.basemvvm.android.base.activity.DefaultActivity;
import com.volkanhotur.basemvvm.databinding.ActivityMainBinding;

public class MainActivity extends DefaultActivity<MainViewModel, ActivityMainBinding> {

    @Override
    protected Class<MainViewModel> getViewModelClass() {
        return MainViewModel.class;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitialized(Bundle instance, MainViewModel viewModel, ActivityMainBinding binding) {
        binding.setViewModel(viewModel);

        viewModel.getInformationFromRemote(this);

        viewModel.getInformation().observe(this, binding.data::setText);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}
