package com.musa.raffi.fluxcupplayer.dependencies;

import com.musa.raffi.fluxcupplayer.MainActivity;

import dagger.Component;

/**
 * Created by Asus on 9/4/2016.
 */
@CustomScope
@Component(modules = ApiModule.class, dependencies = NetworkComponent.class)
public interface ApiComponent {
    void inject(MainActivity activity);
}
