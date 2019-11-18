import React, { Component } from 'react';
import { StyleSheet, Text, View} from 'react-native';
import { Icon }  from 'native-base'

import { createAppContainer, createSwitchNavigator } from 'react-navigation';
import { createBottomTabNavigator } from 'react-navigation-tabs';
import { createStackNavigator } from 'react-navigation-stack';

import HomeTab from './AppTabNavigator/HomeTab'
//import SearchTab from './AppTabNavigator/SearchTab'
import AddMediaTab from './AppTabNavigator/AddMediaTab'
//import LikesTab from './AppTabNavigator/LikesTab'
import ProfileTab from './AppTabNavigator/ProfileTab'
import Register from './Onboarding/Register'
import Login from './Onboarding/Login'


const AppTabNavigator = createBottomTabNavigator (
  {
    HomeTab: {
      screen: HomeTab
    },
    // SearchTab: {
    //   screen: SearchTab
    // },
    AddMediaTab: {
      screen: AddMediaTab
    },
    // LikesTab: {
    //   screen: LikesTab
    // },
    ProfileTab: {
      screen: ProfileTab
    },
  },
  {
    animationEnabled: true,
    swipeEnabled: true,
    tabBarPosition: "bottom",
    tabBarOptions: {
      activeTintColor: '#000000',
      inactiveTintColor: '#d1cece',
      showLabel: false,
      showIcon: true
    },
  }
)

const IntroStack = createStackNavigator ({
  login: Login,
  register: Register,
});

const MainStack = createSwitchNavigator ({
 intro: IntroStack,
 main: AppTabNavigator,
});


const AppContainer = createAppContainer(MainStack);

class MainScreen extends Component {

  static navigationOptions = {
      
      /*headerLeft: <Icon name = "camera" style =
      {{ paddingLeft: 10 }} />,
      title: "MusicMe",
      headerRight: <Icon name = "paper-plane" style =
      {{ paddingRight: 10 }} />*/
      header: null
  }
  render() {
    return (
      <AppContainer/>
    );
  }
}


const styles = StyleSheet.create ({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center'
  }
});
export default MainScreen;

