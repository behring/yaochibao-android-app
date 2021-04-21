package behring.android.yaochibao.android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import behring.android.yaochibao.BuildConfig;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

public class LogReportingTree extends Timber.Tree {
    private final Context context;

    public LogReportingTree(Context context) {
        this.context = context;
    }

    @Override
    protected boolean isLoggable(@Nullable String tag, int priority) {
        int currentLevel;
        switch (BuildConfig.ENVIROMENT) {
            case "prod":
                currentLevel = Log.WARN;
                break;
            case "dev":
                currentLevel = Log.DEBUG;
                break;
            case "test":
                currentLevel = Log.INFO;
                break;
            default:
                currentLevel = Log.VERBOSE;
        }
        return priority >= currentLevel;
    }

    @Override
    protected void log(int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable t) {
        if (BuildConfig.DEBUG) {
            logcat(tag, message, t);
        }
        Completable.fromRunnable(() -> saveLogcat(tag, message, t)).observeOn(Schedulers.io()).subscribe();
    }

    @SuppressLint("LogNotTimber")
    private void logcat(@Nullable String tag, @NotNull String message, @Nullable Throwable t) {
        if (tag == null) {
            if (t == null) {
                Log.i("TAG", message);
                return;
            }
            Log.i("TAG", message, t);
            return;
        }
        if (t == null) {
            Log.i(tag, message);
            return;
        }
        Log.i(tag, message, t);
    }

    @SuppressLint("LogNotTimber")
    private void saveLogcat(@Nullable String tag, @NotNull String message, @Nullable Throwable t) {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        sb.append(sdf.format(new Date())).append("  Log：");
        sb.append("[").append(tag).append("] {").append(message).append("}\n");
        if (t != null) {
            Writer writer = new StringWriter();
            PrintWriter pw = new PrintWriter(writer);
            t.printStackTrace(pw);

            Throwable cause = t.getCause();
            while (cause != null) {
                cause.printStackTrace(pw);
                cause = cause.getCause();
            }
            pw.close();
            String result = writer.toString();
            sb.append("Error Info：").append("\n").append(result);
        }

        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + context.getPackageName() + File.separator + "log");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        SimpleDateFormat fileFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        try {
            FileOutputStream fos = new FileOutputStream(new File(dir, fileFormat.format(new Date())), true);
            fos.write(sb.toString().getBytes());
            fos.flush();
            fos.close();
        } catch (IOException e) {
            Log.e(tag, message, e);
        }
    }
}
