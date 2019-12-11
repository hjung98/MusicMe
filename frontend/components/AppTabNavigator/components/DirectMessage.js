import React, { Component } from "react";
import { StyleSheet, Text, View } from "react-native";
import { GiftedChat } from 'react-native-gifted-chat';

class DirectMessage extends React.Component {

    static navigationOptions = ({navigation}) => ({
        title: (navigation.state.params || {}).name || 'Chat!',
    });

    state = {
        messages: [],
    };

    render() {
        return (
            <GiftedChat
            messages = {this.state.messages}/>
        );
    }
}

export default DirectMessage;

const styles = StyleSheet.create({});

