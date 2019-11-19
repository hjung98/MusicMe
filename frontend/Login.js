import React, {Component} from 'react';
import {
  View,
  Text,
  TextInput,
  Button,
  StyleSheet,
  ImageBackground,
  Dimensions,
  ActivityIndicator,
} from 'react-native';
// import config from '../../config';

const ACCESS_TOKEN = 'access_token';

class Login extends Component {
  constructor() {
    super();
    this.state = {
      credentials: {
        email: '',
        password: '',
      },
      error: '',
      showProgress: false,
    };
  }


   async login() {
    const loginRequest = Object.assign({}, this.state.credentials);

    this.setState({showProgress: true});
    try {
      let response = await fetch('http://localhost:8080/auth/login', {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(loginRequest),
      });
      let res = await response.text();
      console.log(res);
      if (response.status >= 200 && response.status < 300) {
        //Handle success
        let accessToken = res;
        console.log(accessToken);

        this.props.navigation.navigate('camera');
      } else {
        //Handle error
        let error = res;
        throw error;
      }
    } catch (error) {
      this.setState({error: 'Invalid Credentials'});
      console.log('error ' + error);
      this.setState({showProgress: false});
    }
  }

  updateText(text, field) {
    //Because it is an object, we have to do Objectassign so that we assign a new pointer in memory
    let newCredentials = Object.assign(this.state.credentials);

    newCredentials[field] = text;
    this.setState({
      credentials: newCredentials,
    });
  }
  render() {
    return (
      <View style={styles.backgroundImage}>

          <View style={styles.container}>
            <Text style={{marginBottom: 10, color: '#FFFFFF'}}>Login</Text>

            <TextInput
              onChangeText={text => this.updateText(text, 'email')}
              placeholder="Email"
              style={styles.input}
              autoCorrect={false}
              autoCapitalize={false}
            />
            <TextInput
              autoCapitalize={false}
              onChangeText={text => this.updateText(text, 'password')}
              secureTextEntry
              placeholder="Password"
              style={styles.input}
              autoCorrect={false}
            />
            <View />
            <View style={styles.buttonContainer}>
              <Button
                style={{position: 'absolute', left: 0}}
                title="Login"
                // onPress={this.login.bind(this)}
                onPress={()=> {this.props.navigation.navigate('HomeTab')}}
              />
              <Button
                title="New User?"
                onPress={() => this.props.navigation.navigate('register')}
              />
            </View>
            <Text style={styles.error}>{this.state.error}</Text>

            <ActivityIndicator
              animating={this.state.showProgress}
              size="large"
              style={styles.loader}
            />
          </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  input: {
    height: 50,
    width: '100%',
    color: '#0000FF',
    paddingHorizontal: 20,
    marginBottom: 10,
    backgroundColor: '#FFFFFF',
  },
  buttonContainer: {
    flexDirection: 'row',
    justifyContent: 'flex-end',
  },
  container: {
    height: '100%',
    width: '100%',
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  backgroundImage: {
    width: Dimensions.get('window').width,
    height: Dimensions.get('window').height,
  },
  error: {
    color: 'red',
    paddingTop: 10,
  },
  success: {
    color: 'green',
    paddingTop: 10,
  },
  loader: {
    marginTop: 20,
  },
});

export default Login;