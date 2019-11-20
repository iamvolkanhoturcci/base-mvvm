package com.volkanhotur.basemvvm.android.base.navigator;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public interface NavigatorView {

    void pushFragment(@NonNull Fragment fragment);

    void popFragment();

    void popFragment(int dept);

    void switchTab(int tab);

    void returnRoot();

    void openActivity(@NonNull Intent intent);

}
