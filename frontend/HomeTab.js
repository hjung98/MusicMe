import React, { Component } from 'react';
import { StyleSheet, Text, View } from 'react-native';

import {Icon, Container, Content, Thumbnail, Header, Left, Right, Body, Button } from 'native-base'
import CardComponent from '../CardComponent'

class HomeTab extends Component {

    static navigationOptions = {

        tabBarIcon: ({ tintColor})=>{
            return (
                <Icon name = 'home' style = {{color: tintColor}} />
            )
        }
    }
    render() {
        return (
            <Container style = {styles.container}>
                <Header>
                        <Left><Icon name = "camera" style = {{paddingLeft: 10}}></Icon></Left>
                        <Body><Text>MusicMe</Text></Body>
                        <Right><Icon name = "paper-plane" style = {{paddingRight: 10}}></Icon></Right>
                </Header>
                <Content>
                    <CardComponent/>
                </Content>

            </Container>
        );
    }
}

export default HomeTab;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'white'
  },
});