package com.core.glide;

import android.content.Context;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.module.LibraryGlideModule;

/**
 * @author volkanhotur
 */

@com.bumptech.glide.annotation.GlideModule
public class GlideModule extends LibraryGlideModule {

    public GlideModule() {
        super();
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
    }
}
