package com.volkanhotur.basemvvm.android.ui.main;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;

import com.volkanhotur.basemvvm.R;
import com.volkanhotur.basemvvm.android.base.activity.BaseActivity;
import com.volkanhotur.basemvvm.android.utils.DialogUtils;
import com.volkanhotur.basemvvm.android.utils.impl.DialogChoiceListener;
import com.volkanhotur.basemvvm.databinding.ActivityMainBinding;

/**
 * @author volkanhotur
 */

public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding> {

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

        viewModel.getInformation().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String message) {
                binding.data.setText(message);


                //Alerty Dialog Sample
                DialogUtils.showChoiceDialog(context(), "Success",
                        "Data fetched from Github successfully", "DONE", "CLOSE", new DialogChoiceListener() {
                            @Override
                            public void positiveClickListener(Dialog dialog) {
                                DialogUtils.showInfoDialog(context(), "Success",
                                        "Data fetched from Github successfully", "DONE");
                            }

                            @Override
                            public void negativeClickListener(Dialog dialog) {
                                dialog.dismiss();
                            }
                        });
            }
        });

    }

    @Override
    public void onClick(View view) { }
}
