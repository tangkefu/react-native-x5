package im.shimo.react.x5.webview;

import android.content.Context;
import android.util.Log;

import com.facebook.react.bridge.Callback;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.WebView;

import java.util.ArrayList;

public class RNX5Environment {
    static private int mVersion;
    static private boolean mInitFinished;
    static private boolean mInited;
    static private ArrayList<Callback> mCallbacks = new ArrayList<>();

    static public void setup(Context context) {
        Log.e("X5-init", "122222");
        QbSdk.PreInitCallback callback = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean result) {
                mInitFinished = true;
                mInited = result;

                Log.d("X5-init", "result: " + result);
                for (int i = 0; i < mCallbacks.size(); i++) {
                    Callback callback = mCallbacks.get(i);
                    callback.invoke(result);
                }
            }

            @Override
            public void onCoreInitFinished() {

            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(context, callback);

        Log.e("X5-init", "start");
        mVersion = WebView.getTbsCoreVersion(context);
    }

    static int getVerision() {
        return mVersion;
    }

    static boolean getState() {
        return mInited;
    }

    static void addInitCallback(Callback callback) {
        if (mInitFinished) {
            callback.invoke(mInited);
        } else {
            mCallbacks.add(callback);
        }
    }
}
