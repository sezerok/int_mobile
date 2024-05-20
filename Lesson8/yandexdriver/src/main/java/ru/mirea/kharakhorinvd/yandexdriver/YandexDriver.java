package ru.mirea.kharakhorinvd.yandexdriver;


import android.app.Application;

import com.yandex.mapkit.MapKitFactory;

public class YandexDriver extends Application {
    private final String MAPKIT_API_KEY = "8e9a24e3-3594-4754-a45c-da10177f2a31";
    @Override
    public void onCreate() {
        super.onCreate();
// Set the api key before calling initialize on MapKitFactory.
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
    }
}
