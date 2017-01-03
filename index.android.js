import React, { cloneElement } from 'react';
import { WebView, NativeModules } from 'react-native';
import requireNativeComponent from 'requireNativeComponent';

class X5WebView extends WebView {
    static getX5CoreVersion = function (cb: Function) {
        if (cb) {
            NativeModules.X5WebView.getX5CoreVersion(cb);
        }
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

const RNX5WebView = requireNativeComponent('RNX5WebView', X5WebView);

export default X5WebView;
