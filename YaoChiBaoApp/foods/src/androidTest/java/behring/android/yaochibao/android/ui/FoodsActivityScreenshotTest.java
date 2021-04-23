package behring.android.yaochibao.android.ui;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.test.annotation.UiThreadTest;
import androidx.test.core.app.ActivityScenario;
import androidx.test.platform.app.InstrumentationRegistry;

import com.facebook.testing.screenshot.Screenshot;
import com.facebook.testing.screenshot.ViewHelpers;

import org.junit.Before;
import org.junit.Test;

import behring.android.yaochibao.R;
import behring.android.yaochibao.TestHelper;
import behring.android.yaochibao.databinding.ItemFoodBinding;

public class FoodsActivityScreenshotTest {
    private Context context;

    @Before
    public void setup() {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @Test
    public void screenshotFoodsActivity() {
        try (ActivityScenario<FoodsActivity> scenario = ActivityScenario.launch(FoodsActivity.class)) {
            scenario.onActivity(activity -> Screenshot.snapActivity(activity).setName("FoodsActivity").record());
        }
    }

    @Test
    @UiThreadTest
    public void screenshotFoodItem() {
        ItemFoodBinding binding = ItemFoodBinding.bind(LayoutInflater.from(context)
                .inflate(R.layout.item_food, null, false));
        binding.setFood(TestHelper.mockFood(1));
        binding.executePendingBindings();
        ViewHelpers.setupView(binding.getRoot()).setExactWidthDp(300).layout();
        Screenshot.snap(binding.getRoot()).setName("FoodItem").record();
    }
}