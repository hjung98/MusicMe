import React, { Component } from "react";
import {
  StyleSheet,
  Text,
  View,
  Image,
  Dimensions,
  ActivityIndicator,
  TouchableOpacity,
  AsyncStorage,
  TouchableWithoutFeedback,
  TouchableHighlight,
  FlatList
} from "react-native";
import {
  Icon,
  Container,
  Content,
  Header,
  Left,
  Body,
  Right,
  Button
} from "native-base";
import {
  MaterialCommunityIcons,
  Ionicons,
  Entypo,
  Feather,
  FontAwesome
} from "@expo/vector-icons";
import { DOWNLOAD } from "../Config";
import { createAppContainer } from "react-navigation";
import { createStackNavigator } from "react-navigation-stack";

import * as VideoThumbnails from "expo-video-thumbnails";

import ProfileVideo from "./components/ProfileVideo";
import * as ImagePicker from "expo-image-picker";

var { height, width } = Dimensions.get("window");
const cellHeight = height / 6;
const cellWidth = width / 3;

const viewabilityConfig = {
  itemVisiblePercentThreshold: 80
};

export class Item extends React.PureComponent {
  state = {
    mute: true,
    shouldPlay: true,
    fullscreen: false,
    image: null
  };
  componentWillUnmount() {
    if (this.video) {
    }
  }
  async play() {
    try {
    } catch (e) {
      console.warn(e);
    }
  }
  generateThumbnail = async () => {
    try {
      const { uri } = await VideoThumbnails.getThumbnailAsync(
        `${this.props.url}`,
        {
          time: 15000
        }
      );
      this.setState({ image: uri });
    } catch (e) {
      console.warn(e);
    }
  };
  pause() {
    if (this.video) {
      this.video.pauseAsync();
    }
  }
  handleMute = () => {
    this.setState({
      mute: !this.state.mute
    });
  };
  componentDidMount() {
    this.generateThumbnail();
  }
  render() {
    const { image } = this.state;
    return (
      <View style={styles.cell}>
        {!this.props.loading ? (
          <View style={styles.container}>
            {image && (
              <Image
                source={{ uri: image }}
                style={{ width: cellWidth, height: cellHeight }}
              />
            )}
          </View>
        ) : (
          <ActivityIndicator style={{ top: 50 }} size="small" color="#00ff00" />
        )}
      </View>
    );
  }
}

class ProfileTab extends Component {
  constructor(props) {
    super(props);

    this.cellRefs = {};
    this.handleRefresh = this.handleRefresh.bind(this);
    this._loadUserInfo = this._loadUserInfo.bind(this);
    this._unloadUser = this._unloadUser.bind(this);
    this.handleChooseImage = this.handleChooseImage.bind(this);
  }

  state = {
    items: [],
    thumbnails: [],
    loading: true,
    hasCameraPermission: null,
    image: null,
    videosDisplayed: [],
    refreshing: false,
    activeIndex: 0,
    dataSource: {},
    userId: "",
    name: "",
    email: ""
  };

  segmentClicked = index => {
    this.setState({
      activeIndex: index
    });
  };
  handleChooseImage = async () => {
    let result = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.Images,
      allowsEditing: true,
      aspect: [2, 1]
    });
    if (!result.cancelled) {
      this.setState({ image: result });
    }

    await AsyncStorage.setItem("userImage", JSON.stringify(result));
  };
  componentDidMount() {
    this.loadItems();

    setTimeout(this.loadItems, 1000);
    setTimeout(this.loadItems, 1100);
    setTimeout(this.loadItems, 1200);
    setTimeout(this.loadItems, 1300);
  }

  componentWillMount() {
    this._loadUserInfo();
    this.getVideos();
  }

  async getVideos() {
    const id = await AsyncStorage.getItem("userId");

    try {
      const videoEntities = await fetch(
        `http://cosc-257-grp3.cs.amherst.edu:8080/videos/${id}`
      ).then(res => res.json());

      var updatedVideos = [];
      var videoEntity = {
        url: "",
        caption: ""
      };
      videoEntities.forEach(video => {
        videoEntity = {
          url: `${DOWNLOAD}${video.pathToVideo}`,
          caption: video.caption,
          id: video.videoIdentity.user.id
        };
        updatedVideos.push(videoEntity);
      });

      this.setState(
        {
          videosDisplayed: updatedVideos,
          loading: false,
          refreshing: false
        },
        function() {
          this.loadItems();
        }
      );
    } catch (e) {
      console.error("error loading videos", e);
    }
  }

  _onViewableItemsChanged = props => {
    const changed = props.changed;
    changed.forEach(item => {
      const cell = this.cellRefs[item.key];
      if (cell) {
        if (item.isViewable) {
          cell.play();
        } else {
          cell.pause();
        }
      }
    });
  };

  handleRefresh() {
    this.setState(
      {
        items: [],
        refreshing: true
      },
      function() {
        this.getVideos();
      }
    );
  }

  loadItems = () => {
    const newItems = this.state.videosDisplayed.map((item, i) => ({
      ...item,
      id: i,
      userid: item.id,
      caption: item.caption,
      url: item.url
    }));
    const items = [...newItems];
    this.setState({ items, loading: false });
  };

  _renderItem = ({ item }) => {
    return (
      <View
        style={{
          margin: 1,
          width: cellWidth,
          height: cellHeight
        }}
      >
        <Item
          ref={ref => {
            this.cellRefs[item.id] = ref;
          }}
          {...item}
        />
      </View>
    );
  };

  _unloadUser = async () => {
    await AsyncStorage.removeItem("isLoggedIn");
    await AsyncStorage.removeItem("userId");

    await AsyncStorage.removeItem("userName");

    await AsyncStorage.removeItem("userEmail");
    this.props.navigation.navigate("Login");
  };
  _loadUserInfo = async () => {
    try {
      const id = await AsyncStorage.getItem("userId");
      const name = await AsyncStorage.getItem("userName");
      const email = await AsyncStorage.getItem("userEmail");
      const image = await AsyncStorage.getItem("userImage");

      this.setState({ userId: id, email: email, name: name, image: image });
    } catch (error) {
      Alert.alert("Error", "User info cannot be loaded");
    }
  };
  static navigationOptions = {
    tabBarIcon: ({ tintColor }) => {
      return <Icon name="person" style={{ color: tintColor }} />;
    }
  };
  render() {
    const { items, name, videosDisplayed, image } = this.state;

    return (
      <Container style={{ flex: 1, backgroundColor: "white" }}>
        <Header>
          <Left>
            <Icon name="person-add" style={{ paddingLeft: 10 }}></Icon>
          </Left>
          <Body>
            <Text style={{ fontSize: 18, fontWeight: "bold" }}>My Profile</Text>
          </Body>
          <Right>
            <Icon name="ios-menu" style={{ paddingRight: 10 }}></Icon>
          </Right>
        </Header>
        <Content>
          <View style={{ paddingTop: 10 }}>
            <View style={{ flexDirection: "row" }}>
              <TouchableOpacity
                style={{ flex: 1, alignItems: "center" }}
                onPress={this.handleChooseImage}
              >
                <Image
                  source={image}
                  style={{
                    width: 75,
                    height: 75,
                    borderRadius: 37.5,
                    borderWidth: 2 / 3,
                    borderColor: "grey"
                  }}
                />
              </TouchableOpacity>
              <View style={{ flex: 3 }}>
                <View
                  style={{
                    flexDirection: "row",
                    justifyContent: "space-around"
                  }}
                >
                  <View style={{ alignItems: "center" }}>
                    <Text style={{ fontSize: 18 }}>
                      {videosDisplayed.length}
                    </Text>
                    <Text style={{ fontSize: 13, color: "grey" }}>posts</Text>
                  </View>
                  <View style={{ alignItems: "center" }}>
                    <Text style={{ fontSize: 18 }}>0</Text>
                    <Text style={{ fontSize: 13, color: "grey" }}>
                      followers
                    </Text>
                  </View>
                  <View style={{ alignItems: "center" }}>
                    <Text style={{ fontSize: 18 }}>0</Text>
                    <Text style={{ fontSize: 13, color: "grey" }}>
                      following
                    </Text>
                  </View>
                </View>
                <View
                  style={{
                    flexDirection: "row",
                    paddingTop: 12,
                    alignItems: "center",
                    justifyContent: "center"
                  }}
                >
                  <Button
                    bordered
                    dark
                    style={{
                      flex: 3,
                      marginLeft: 10,
                      justifyContent: "center",
                      height: 40
                    }}
                    onPress={() => {
                      this._unloadUser();
                    }}
                  >
                    <Text>Log Out</Text>
                  </Button>
                  <Button
                    bordered
                    dark
                    style={{
                      flex: 1,
                      height: 40,
                      marginRight: 10,
                      marginLeft: 5,
                      justifyContent: "center",
                      alignItems: "center"
                    }}
                    onPress={() => this.handleRefresh}
                  >
                    <Feather name="refresh-cw" style={{ fontSize: 20 }} />
                  </Button>
                </View>
              </View>
            </View>
            <View style={{ paddingVertical: 10, paddingHorizontal: 10 }}>
              <Text
                style={{ fontWeight: "bold", paddingRight: 5, fontSize: 15 }}
              >
                {name}
              </Text>
              <Text style={{ fontSize: 15 }}>Frustrated Coder</Text>
            </View>
          </View>
          <View>
            <View
              style={{
                flexDirection: "row",
                justifyContent: "space-around",
                borderTopWidth: 1,
                borderTopColor: "#eae5e5"
              }}
            >
              <Button
                transparent
                onPress={() => this.segmentClicked(0)}
                active={this.state.activeIndex == 0}
              >
                <Icon
                  name="keypad"
                  style={[
                    this.state.activeIndex == 0
                      ? {}
                      : {
                          color: "grey"
                        }
                  ]}
                />
              </Button>
              {/* <Button
                transparent
                onPress={() => this.segmentClicked(1)}
                active={this.state.activeIndex == 1}
              >
                <Icon
                  name="ios-list"
                  style={[
                    this.state.activeIndex == 1
                      ? {}
                      : {
                          color: "grey"
                        }
                  ]}
                />
              </Button> */}
            </View>

            {this.state.activeIndex == 0 ? (
              <View style={styles.container}>
                <FlatList
                  extraData={this.state}
                  data={items}
                  renderItem={this._renderItem}
                  keyExtractor={item => `${item.id}`}
                  onViewableItemsChanged={this._onViewableItemsChanged}
                  numColumns={3}
                  viewabilityConfig={viewabilityConfig}
                  onRefresh={() => {
                    this.handleRefresh();
                  }}
                  refreshing={this.state.refreshing}
                  removeClippedSubviews={true}
                />
              </View>
            ) : (
              alert("d")
            )}
          </View>
        </Content>
      </Container>
    );
  }
}

const AppNavigator = createStackNavigator({
  ProfileTab: {
    screen: ProfileTab,
    navigationOptions: {
      header: null
    }
  },

  ProfileVideo: { screen: ProfileVideo }
});

const AppContainer = createAppContainer(AppNavigator);

export default AppContainer;

const styles = StyleSheet.create({
  container: {
    flexDirection: "row",
    alignItems: "flex-start",
    flexWrap: "wrap"
  },
  imageThumbnail: {
    height: 100
  },
  cell: {}
});
