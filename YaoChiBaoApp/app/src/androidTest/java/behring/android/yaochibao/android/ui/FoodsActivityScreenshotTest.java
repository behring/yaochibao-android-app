package behring.android.yaochibao.android.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

//import androidx.test.annotation.UiThreadTest;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.facebook.testing.screenshot.Screenshot;
import com.facebook.testing.screenshot.ViewHelpers;

import org.junit.Test;
import org.junit.runner.RunWith;

import behring.android.yaochibao.R;

public class FoodsActivityScreenshotTest {

    @Test
    public void screenshotFoodsActivity() {
        try (ActivityScenario<FoodsActivity> scenario = ActivityScenario.launch(FoodsActivity.class)) {
            scenario.onActivity(activity -> Screenshot.snapActivity(activity).record());
        }

//        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//        LayoutInflater inflater = LayoutInflater.from(targetContext);
//        View view = inflater.inflate(R.layout.activity_foods, null, false);
//        ViewHelpers.setupView(view).setExactWidthDp(300).layout();
//        Screenshot.snap(view).record();
    }
}