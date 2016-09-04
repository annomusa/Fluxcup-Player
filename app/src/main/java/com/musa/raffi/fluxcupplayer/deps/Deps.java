package com.musa.raffi.fluxcupplayer.deps;

import android.app.Application;

import com.musa.raffi.fluxcupplayer.models.Resource;
import com.musa.raffi.fluxcupplayer.dependencies.ApiComponent;
import com.musa.raffi.fluxcupplayer.dependencies.DaggerApiComponent;
import com.musa.raffi.fluxcupplayer.dependencies.DaggerNetworkComponent;
import com.musa.raffi.fluxcupplayer.dependencies.NetworkComponent;
import com.musa.raffi.fluxcupplayer.dependencies.NetworkModule;

/**
 * Created by Asus on 9/4/2016.
 */

public class Deps extends Application{
    private ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        resolveDependency();
        super.onCreate();
    }

    private void resolveDependency() {
        mApiComponent = DaggerApiComponent.builder()
                .networkComponent(getNetworkComponent())
                .build();

    }

    public NetworkComponent getNetworkComponent() {
        return DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(Resource.ROOT_URL))
                .build();
    }

    public ApiComponent getApiComponent(){
        return mApiComponent;
    }
}
