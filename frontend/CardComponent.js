import React, { Component } from 'react';
import { StyleSheet, Text, View } from 'react-native';
import {Icon} from 'native-base'


class CardComponent extends Component {
    render() {
        return (
        <View style = {styles.container}>
            <Text>CardComponent</Text>
        </View>
        );
    }
}

export default CardComponent;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});