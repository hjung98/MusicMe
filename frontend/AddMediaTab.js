import React, { Component } from 'react';
import { StyleSheet, Text, View } from 'react-native';
import {Icon} from 'native-base'


class AddMediaTab extends Component {

    static navigationOptions = {

        tabBarIcon: ({ tintColor})=>{
            return (
                <Icon name = 'microphone' style = {{color: tintColor}} />
            )
        }
    }
    render() {
        return (
        <View style = {styles.container}>
            <Text>AddMediaTab</Text>
        </View>
        );
    }
}

export default AddMediaTab;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});