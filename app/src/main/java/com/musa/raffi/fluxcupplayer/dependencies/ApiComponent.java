package com.musa.raffi.fluxcupplayer.dependencies;

import android.support.v7.app.AppCompatActivity;

import com.musa.raffi.fluxcupplayer.MainActivity;
import com.musa.raffi.fluxcupplayer.detail.DetailActivity;

import dagger.Component;

/**
 * Created by Asus on 9/4/2016.
 */
@CustomScope
@Component(modules = ApiModule.class, dependencies = NetworkComponent.class)
public interface ApiComponent {
    void inject(MainActivity activity);
    void bind(DetailActivity activity);
}
