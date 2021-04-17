package behring.android.yaochibao.domain;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import behring.android.yaochibao.common.Network;
import behring.android.yaochibao.common.Notification;
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
        if (Network.getInstance().isNetworkAvailable(context)) {
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
        Notification.getInstance().showCommendFoodNotification(context, commendFood);
    }
}
