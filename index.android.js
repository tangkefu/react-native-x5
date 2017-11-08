import React, { cloneElement, PropTypes } from 'react';
import { WebView, NativeModules, requireNativeComponent } from 'react-native';

class X5WebView extends WebView {
    static getX5CoreVersion = function (cb: Function): Promise {
        if (cb) {
            NativeModules.X5WebView.getX5CoreVersion(cb);
        }

        return new Promise(resolve => {
            NativeModules.X5WebView.getX5CoreVersion(resolve);
        });
    };

    render() {
        const wrapper = super.render();
        const [webview,...children] = wrapper.props.children;
        const X5webview = (
            <RNX5WebView
                {...webview.props}
            />
        );

        return cloneElement(wrapper, wrapper.props, X5webview, ...children);
    }
}

const RNX5WebView = requireNativeComponent('RNX5WebView', X5WebView, {
    nativeOnly: {
        messagingEnabled: PropTypes.bool,
    },
});

export default X5WebView;
