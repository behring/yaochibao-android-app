package behring.android.yaochibao.android.service;

import android.content.Intent;
import android.os.Build;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.gson.Gson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import behring.android.yaochibao.domain.FoodsPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@Config(sdk = Build.VERSION_CODES.P)
public class PushReceivingServiceTest {
    @Test
    public void should_call_FoodsPresenter_handleFoodCommendPushMessage_when_receive_push_FOOD_COMMEND() {
        //given
        Intent serviceIntent = new Intent(ApplicationProvider.getApplicationContext(), PushReceivingService.class);
        String pushMessageStr = "{type:FOOD_COMMEND,data:\"{restaurantId:12313,foodId:10003,description:特价鸡排饭}\"}";
        serviceIntent.putExtra(PushReceivingService.PUSH_MESSAGE_KEY, pushMessageStr);
        PushReceivingService service = new PushReceivingService();
        FoodsPresenter foodsPresenter = mock(FoodsPresenter.class);
        service.foodsPresenter = foodsPresenter;
        //when
        service.onStartCommand(serviceIntent, 0 ,0);
        //then
        verify(foodsPresenter).handleFoodCommendPushMessage(new Gson().fromJson(pushMessageStr, PushReceivingService.PushMessage.class).data);
    }
}