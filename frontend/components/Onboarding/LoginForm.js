import React, { Component } from "react";
import {
  View,
  Image,
  Text,
  Button,
  StyleSheet,
  TextInput,
  TouchableOpacity,
  StatusBar
} from "react-native";
import config from "../config";

class LoginForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      signUpCredentials: {
        name: "",
        email: "",
        password: ""
      },
      loginCredentials: {
        email: "",
        password: ""
      },
      buttonOption: 0
    };
    this.login = this.login.bind(this);
    this.register = this.register.bind(this);
    this.segmentClicked = this.segmentClicked.bind(this);
  }

  async login() {
    const loginRequest = Object.assign({}, this.state.loginCredentials);
    this.props.login(loginRequest);
  }
  async register() {
    const signUpRequest = Object.assign({}, this.state.signUpCredentials);
    this.props.register(signUpRequest);
  }

  segmentClicked(index) {
    this.setState({
      buttonOption: index
    });
  }
  updateLoginText(text, field) {
    //Because it is an object, we have to do Objectassign so that we assign a new pointer in memory
    let newCredentials = Object.assign({}, this.state.loginCredentials);

    newCredentials[field] = text;
    this.setState({
      loginCredentials: newCredentials
    });
  }
  updateSignUpText(text, field) {
    //Because it is an object, we have to do Objectassign so that we assign a new pointer in memory
    let newCredentials = Object.assign({}, this.state.signUpCredentials);

    newCredentials[field] = text;
    this.setState({
      signUpCredentials: newCredentials
    });
  }

  render() {
    if (this.state.buttonOption == 0) {
      return (
        <View style={styles.container}>
          <StatusBar barStyle="light-content" />
          <TextInput
            placeholder="email"
            placeholderTextColor="white"
            returnKeyType="next"
            keyboardType="email-address"
            autoCapitalize="none"
            autoCorrect={false}
            style={styles.input}
            onChangeText={text => this.updateLoginText(text, "email")}
            clearButtonMode="always"
          />
          <TextInput
            placeholder="password"
            placeholderTextColor="grey"
            returnKeyType="go"
            secureTextEntry
            style={styles.input}
            ref={input => (this.passwordInput = input)}
            onChangeText={text => this.updateLoginText(text, "password")}
            clearButtonMode="always"
          />
          <TouchableOpacity onPress={this.login} style={styles.buttonIn}>
            <Text style={styles.buttonText}>LOGIN</Text>
          </TouchableOpacity>
          <TouchableOpacity
            style={styles.buttonUp}
            onPress={() => this.segmentClicked(1)}
            active={this.state.buttonOption == 1}
          >
            <Text style={styles.buttonText}>SIGN UP</Text>
          </TouchableOpacity>
        </View>
      );
    } else if (this.state.buttonOption == 1) {
      return (
        <View style={styles.container}>
          <StatusBar barStyle="light-content" />
          <TextInput
            placeholder="name"
            placeholderTextColor="white"
            returnKeyType="next"
            autoCapitalize="none"
            autoCorrect={false}
            style={styles.input}
            onChangeText={text => this.updateSignUpText(text, "name")}
          />
          <TextInput
            placeholder="email"
            placeholderTextColor="white"
            returnKeyType="next"
            keyboardType="email-address"
            autoCapitalize="none"
            autoCorrect={false}
            style={styles.input}
            onChangeText={text => this.updateSignUpText(text, "email")}
          />
          <TextInput
            placeholder="password"
            placeholderTextColor="grey"
            returnKeyType="go"
            secureTextEntry
            style={styles.input}
            onChangeText={text => this.updateSignUpText(text, "password")}
          />
          <TouchableOpacity onPress={this.register} style={styles.buttonUp}>
            <Text style={styles.buttonText}>SIGN UP</Text>
          </TouchableOpacity>
          <TouchableOpacity
            style={{
              backgroundColor: "#9980FA",
              paddingVertical: 15,
              marginBottom: 10
            }}
            onPress={() => this.segmentClicked(0)}
            active={this.state.buttonOption == 0}
          >
            <Text style={styles.buttonText}>Back to Login</Text>
          </TouchableOpacity>
        </View>
      );
    }
  }
}

const styles = StyleSheet.create({
  container: {
    padding: 15,
    paddingBottom: 60
  },
  input: {
    height: 40,
    backgroundColor: "pink",
    marginBottom: 10,
    color: "black",
    paddingHorizontal: 10
  },
  buttonIn: {
    backgroundColor: "#ED4C67",
    paddingVertical: 15,
    marginBottom: 10
  },
  buttonUp: {
    backgroundColor: "#B53471",
    paddingVertical: 15,
    marginBottom: 10
  },
  buttonText: {
    textAlign: "center",
    color: "white",
    fontWeight: "700"
  }
});

export default LoginForm;
