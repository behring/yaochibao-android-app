package behring.android.yaochibao.domain;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import behring.android.yaochibao.R;
import behring.android.yaochibao.data.FoodsRepository;
import behring.android.yaochibao.data.model.Food;
import dagger.hilt.android.qualifiers.ApplicationContext;
import io.reactivex.rxjava3.core.Single;
import timber.log.Timber;

public class FoodsPresenter {
    private final FoodsRepository foodsRepository;
    private final Context context;

    @Inject
    public FoodsPresenter(@ApplicationContext Context context, FoodsRepository foodsRepository) {
        this.context = context;
        this.foodsRepository = foodsRepository;
    }

    public Single<List<Food>> getFoods(String searchString, int skipCount, int count) {
        if (isNetworkAvailable()) {
            return foodsRepository.getFoods(searchString, skipCount, count);
        } else {
            return foodsRepository.getFoodsFromDB();
        }
    }

    public void handleFoodCommendPushMessage(String data) {
        Timber.d("push message data: %s", data);
        Food commendFood = new Gson().fromJson(data, Food.class);
        if (commendFood == null) {
            Timber.w("parse commend food data error, commend food is null.");
            return;
        }
        showCommendFoodNotification(commendFood);
    }


    private void showCommendFoodNotification(Food food) {
        String channelId = "channel_commend_food";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId);
        builder.setContentTitle(food.getName())
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentText(String.valueOf(food.getPriceCent()/1000));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // 第三个参数表示通知的重要程度，默认则只在通知栏闪烁一下
            NotificationChannel notificationChannel = new NotificationChannel(channelId, "餐品推荐", NotificationManager.IMPORTANCE_DEFAULT);
            // 注册通道，注册后除非卸载再安装否则不改变
            NotificationManagerCompat.from(context).createNotificationChannel(notificationChannel);
            builder.setChannelId(channelId);
        }
        NotificationManagerCompat.from(context).notify(1, builder.build());
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) context.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return false;
        }
        NetworkInfo networkinfo = manager.getActiveNetworkInfo();
        return networkinfo != null && networkinfo.isAvailable();
    }
}
