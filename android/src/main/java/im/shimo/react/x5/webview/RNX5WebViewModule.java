package im.shimo.react.x5.webview;

import android.util.Log;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.WebView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ReactModule(name = RNX5WebViewModule.REACT_CLASS)
public class RNX5WebViewModule extends ReactContextBaseJavaModule {

    protected static final String REACT_CLASS = "RNX5WebViewModule";


    @Override
    public String getName() {
        return REACT_CLASS;
    }

    public RNX5WebViewModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @ReactMethod
    public void getX5State(final Promise promise) {
        if (RNX5Environment.getState()) {
            promise.resolve(null);
        } else {
            RNX5Environment.addInitCallback(new Callback() {
                @Override
                public void invoke(Object... args) {
                    boolean result = (boolean)args[0];

                    if (result) {
                        promise.resolve(null);
                    } else {
                        promise.reject(new Throwable("X5 init failed."));
                    }
                }
            });
        }

    }


    @ReactMethod
    public void getVersion(final Promise promise) {
        promise.resolve(RNX5Environment.getVerision());
    }
}
