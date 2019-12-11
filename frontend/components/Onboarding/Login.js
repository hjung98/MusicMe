import React, { Component } from "react";
import { View, AsyncStorage } from "react-native";

import LoginGesture from "./LoginGesture.js";

class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      error: "",
      showProgress: false,
      isReady: false
    };
    this.login = this.login.bind(this);
    this.register = this.register.bind(this);
    // this._saveUser = this._saveUser.bind(this);
    this._loadData = this._loadData.bind(this);
  }

  async register(signUpRequest) {
    this.setState({ showProgress: true });
    try {
      let response = await fetch(
        "http://cosc-257-grp3.cs.amherst.edu:8080/auth/signup",
        {
          method: "POST",
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json"
          },
          body: JSON.stringify(signUpRequest)
        }
      );
      let res = await JSON.parse(response);

      if (response.status >= 200 && response.status < 300) {
        alert("You have succesfully signed up!");
      } else {
        //Handle error
        let error = res;
        throw error;
      }
    } catch (error) {
      alert("Invalid Information. Try Again!");
      this.setState({ error: "Invalid Credentials" });
      this.setState({ showProgress: false });
    }
  }

  async login(loginRequest) {
    this.setState({ showProgress: true });
    try {
      let response = await fetch(
        "http://cosc-257-grp3.cs.amherst.edu:8080/auth/login",
        {
          method: "POST",
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json"
          },
          body: JSON.stringify(loginRequest)
        }
      );
      let res = JSON.parse(await response.text());

      if (res.id) {
        // //store user info locally

        await AsyncStorage.setItem("userId", `${res.id}`);
        await AsyncStorage.setItem("userEmail", `${res.email}`);
        await AsyncStorage.setItem("userName", `${res.name}`);
        await AsyncStorage.setItem("isLoggedIn", "true");

        this.props.navigation.navigate("HomeTab");
      } else {
        //Handle error
        let error = res;
        throw error;
      }
    } catch (error) {
      this.setState({ error: "Invalid Credentials", showProgress: false });
    }
  }
  componentDidMount() {
    this._loadData();
  }
  // _saveUser = async (id, email, name) => {
  //   console.log("-----");

  //   await AsyncStorage.setItem("userId", `${id}`);
  //   await AsyncStorage.setItem("userEmail", `${email}`);
  //   await AsyncStorage.setItem("userName", `${name}`);
  // };
  _loadData = async () => {
    const isLoggedIn = await AsyncStorage.getItem("isLoggedIn");
    this.props.navigation.navigate(isLoggedIn == "true" ? "HomeTab" : "Login");
  };
  render() {
    return (
      <View
        style={{
          flex: 1,
          backgroundColor: "white",
          justifyContent: "flex-end"
        }}
      >
        <LoginGesture login={this.login} register={this.register} />
      </View>
    );
  }
}

export default Login;
