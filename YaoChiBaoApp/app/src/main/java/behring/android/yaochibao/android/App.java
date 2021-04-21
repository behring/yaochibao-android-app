package behring.android.yaochibao.android;

import android.app.Application;
import android.content.Intent;

import behring.android.yaochibao.android.service.PushReceivingService;
import dagger.hilt.android.HiltAndroidApp;
import timber.log.Timber;

@HiltAndroidApp
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new LogReportingTree(this));
        startService(new Intent(this, PushReceivingService.class));
        Timber.d("App [onCreate]");
    }
}
