package behring.android.yaochibao.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;

import timber.log.Timber;

/**
 * push接收服务，当服务端发送push消息时，onStartCommand将受到push消息。
 *
 * @since 2021-04-15
 */
public class PushReceivingService extends Service {
    public PushReceivingService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Timber.d("call [onStartCommand]");
        String message = intent.getStringExtra("message");
        if (!TextUtils.isEmpty(message)) {
            Timber.d("message content: %s", message);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}