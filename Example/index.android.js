/**
 * Example App for X5 WebView
 * @flow
 */

import React, { Component } from 'react';
import {
    AppRegistry,
    StyleSheet
} from 'react-native';

import X5WebView from 'react-native-x5';

class X5 extends Component {
    componentDidMount() {
        X5WebView.getX5CoreVersion(version => {
            console.log(version);
        });
    }

    render() {
        return (
            <X5WebView
                source={{uri: 'https://shimo.im/'}}
                style={styles.webView}
            />
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center'
    },
    webView: {
        flex: 1
    }
});

AppRegistry.registerComponent('X5', () => X5);
