package com.core.viewmodel.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/**
 * @author volkanhotur
 */
@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;

    @Inject
    ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
        this.creators = creators;
    }

    @Override @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) throws RuntimeException {
        Provider<? extends ViewModel> creator = creators.get(modelClass);

        if (creator == null) {
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }

        if (creator == null) {
            throw new IllegalArgumentException("Unknown model class " + modelClass);
        }

        try {
            //noinspection unchecked
            return (T) creator.get();
        } catch (ClassCastException e) {
            throw new RuntimeException(e);
        }
    }
}