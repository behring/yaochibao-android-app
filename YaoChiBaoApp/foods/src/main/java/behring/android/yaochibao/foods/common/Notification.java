package behring.android.yaochibao.foods.common;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import behring.android.yaochibao.foods.R;
import behring.android.yaochibao.foods.data.model.Food;

public class Notification {

    private static final Notification INSTANCE = new Notification();

    private Notification() {
    }

    public static Notification getInstance() {
        return INSTANCE;
    }

    public void showCommendFoodNotification(Context context, Food food) {
        String channelId = "channel_commend_food";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId);
        builder.setContentTitle(food.getName())
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentText(String.valueOf(food.getPriceCent() / 1000));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 第三个参数表示通知的重要程度，默认则只在通知栏闪烁一下
            NotificationChannel notificationChannel = new NotificationChannel(channelId, "餐品推荐", NotificationManager.IMPORTANCE_DEFAULT);
            // 注册通道，注册后除非卸载再安装否则不改变
            NotificationManagerCompat.from(context).createNotificationChannel(notificationChannel);
            builder.setChannelId(channelId);
        }
        NotificationManagerCompat.from(context).notify(1, builder.build());
    }
}
