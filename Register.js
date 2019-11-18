  
import React, {Component} from 'react';
import {
  View,
  Text,
  TextInput,
  Button,
  StyleSheet,
  ImageBackground,
  Dimensions,
} from 'react-native';
class Register extends Component {
  constructor() {
    super();
    this.state = {
      credentials: {
        name: '',
        email: '',
        password: '',
      },
      error: '',
    };
  }
  async register() {
    const singUpRequest = Object.assign({}, this.state.credentials);

    fetch('http://localhost:8080/auth/signup', {
      method: 'POST',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(singUpRequest),
    });
    let res = await response.text();
    console.log(res);
    if (response.status >= 200 && response.status < 300) {
      this.props.navigation.navigate('login');
    } else {
      //Handle error
      let error = res;
      throw error;
    }
    this.setState({
      error: 'Invalid Information',
    });
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
            <Text style={{marginBottom: 10, color: '#FFFFFF'}}>
              Register Page
            </Text>
            <TextInput
              onChangeText={text => this.updateText(text, 'name')}
              placeholder="Name"
              style={styles.input}
              autoCorrect={false}
            />
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
              secureTextEntry={true}
              placeholder="Password"
              style={styles.input}
              autoCorrect={false}
            />
            <Button
              style={{color: 'white'}}
              title="Sign Up"
              onPress={this.register.bind(this)}
            />

            <Text style={styles.error}>{this.state.error}</Text>
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
});

export default Register;