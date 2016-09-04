package com.musa.raffi.fluxcupplayer.dependencies;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Asus on 9/4/2016.
 */
@Singleton
@Component(modules = NetworkModule.class)
public interface NetworkComponent {
    Retrofit retrofit();
}
