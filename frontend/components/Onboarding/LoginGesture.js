import React, { Component } from "react";
import {
  View,
  Image,
  Text,
  KeyboardAvoidingView,
  StyleSheet
} from "react-native";
import config from "../config";
import LoginForm from "./LoginForm";

class LoginGesture extends Component {
  render() {
    return (
      <KeyboardAvoidingView behavior="padding" style={styles.container}>
        <View style={styles.container}>
          <View style={styles.logoContainer}>
            <Image
              style={styles.logo}
              source={require("../../assets/templogo.jpg")}
            />
            <Text style={styles.title}>MusicMe</Text>
            <Text style={{ fontSize: 15, color: "grey" }}>
              Discover Good Vibrations
            </Text>
          </View>
          <View style={styles.formContainer}>
            <LoginForm
              login={this.props.login}
              register={this.props.register}
            />
          </View>
        </View>
      </KeyboardAvoidingView>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "white"
  },
  logoContainer: {
    alignItems: "center",
    flexGrow: 1,
    justifyContent: "center"
  },
  logo: {
    width: 180,
    height: 180
  },
  title: {
    color: "black",
    marginTop: 10,
    padding: 10,
    fontSize: 25,
    fontWeight: "bold",
    textAlign: "center",
    opacity: 0.9
  }
});

export default LoginGesture;
