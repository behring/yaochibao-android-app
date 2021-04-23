package behring.android.yaochibao.foods.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.text.TextUtils;

import androidx.annotation.StringDef;

import com.google.gson.Gson;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Inject;

import behring.android.yaochibao.foods.domain.FoodsPresenter;
import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

import static behring.android.yaochibao.foods.android.service.PushReceivingService.PushType.FOOD_COMMEND;


/**
 * push接收服务，当服务端发送push消息时，onStartCommand将受到push消息。
 *
 * @since 2021-04-15
 */
@AndroidEntryPoint
public class PushReceivingService extends Service {
    public static String PUSH_MESSAGE_KEY = "message";
    @Inject
    FoodsPresenter foodsPresenter;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Timber.d("call [onStartCommand]");
        String message = intent.getStringExtra(PUSH_MESSAGE_KEY);
        if (!TextUtils.isEmpty(message)) {
            Timber.d("message content: %s", message);
            PushMessage pushMessage = parsePushMessage(message);
            callSomeonePresenter(pushMessage);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    private PushMessage parsePushMessage(String pushMessageStr) {
        return new Gson().fromJson(pushMessageStr, PushMessage.class);
    }

    private void callSomeonePresenter(PushMessage pushMessage) {
        if (FOOD_COMMEND.equals(pushMessage.type)) {
            foodsPresenter.handleFoodCommendPushMessage(pushMessage.data);
        }
    }

    public static class PushMessage {
        @PushType
        String type;
        String data;
    }

    @StringDef(value = {FOOD_COMMEND})
    @Retention(RetentionPolicy.SOURCE)
    @interface PushType {
        String FOOD_COMMEND = "FOOD_COMMEND";
    }

    public class LocalBinder extends Binder {
        PushReceivingService getService() {
            return PushReceivingService.this;
        }
    }
}