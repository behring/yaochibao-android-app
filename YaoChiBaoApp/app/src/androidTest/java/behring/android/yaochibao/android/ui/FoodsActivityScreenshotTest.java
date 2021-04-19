package behring.android.yaochibao.android.ui;

import androidx.test.core.app.ActivityScenario;

import com.facebook.testing.screenshot.Screenshot;

import org.junit.Test;

public class FoodsActivityScreenshotTest {
    @Test
    public void screenshotFoodsActivity() {
        try (ActivityScenario<FoodsActivity> scenario = ActivityScenario.launch(FoodsActivity.class)) {
            scenario.onActivity(activity -> Screenshot.snapActivity(activity).setName("FoodsActivity").record());
        }
    }
}