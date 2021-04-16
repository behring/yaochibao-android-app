package behring.android.yaochibao;

import android.os.Bundle;

import androidx.test.runner.AndroidJUnitRunner;

import com.facebook.litho.config.ComponentsConfiguration;
import com.facebook.testing.screenshot.ScreenshotRunner;
import com.facebook.testing.screenshot.layouthierarchy.LayoutHierarchyDumper;
import com.facebook.testing.screenshot.layouthierarchy.litho.LithoAttributePlugin;
import com.facebook.testing.screenshot.layouthierarchy.litho.LithoHierarchyPlugin;

class ScreenshotTestRunner extends AndroidJUnitRunner {
    static {
        ComponentsConfiguration.isDebugModeEnabled = true;
        LayoutHierarchyDumper.addGlobalHierarchyPlugin(LithoHierarchyPlugin.getInstance());
        LayoutHierarchyDumper.addGlobalAttributePlugin(LithoAttributePlugin.getInstance());
    }

    @Override
    public void onCreate(Bundle arguments) {
        ScreenshotRunner.onCreate(this, arguments);
        super.onCreate(arguments);
    }

    @Override
    public void finish(int resultCode, Bundle results) {
        ScreenshotRunner.onDestroy();
        super.finish(resultCode, results);
    }
}