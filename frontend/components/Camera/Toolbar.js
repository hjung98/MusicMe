import React from "react";
import { Camera } from "expo-camera";
import { Ionicons, MaterialIcons, FontAwesome, MaterialCommunityIcons } from "@expo/vector-icons";
import { Col, Row, Grid } from "react-native-easy-grid";
import { TouchableOpacity, Text } from "react-native";
import EntypoIcon from "react-native-vector-icons/Entypo";


import styles from "./styles";

const { Type: CameraTypes } = Camera.Constants;
export default ({
  capturing,
  cameraType = CameraTypes.back,
  setCameraType,
  toggleRecord,
  pickVideo,
  goBack,
  video,
  _saveVideo
}) => (


    <Grid style={styles.bottomToolbar}>
      <Row>
        <Col style={styles.topToolbar}>
          <Ionicons
            name="ios-arrow-back"
            style={{
              color: "white",
              flexDirection: "row",
              flex: 4,
              marginLeft: 10,
              fontSize: 40,
              padding: 10
            }}
            onPress={goBack}
          />
        </Col>
        <Col size={2} style={styles.alignCenter}>
          <EntypoIcon
            name="upload"
            style={{ color: "white", fontSize: 30, paddingBottom: 90 }}
            onPress={pickVideo}
          />
        </Col>

        <Col size={2} style={styles.alignCenter}>
          {video && (
            <TouchableOpacity
              onPress={_saveVideo}
              style={{
                paddingBottom: 20,
                width: "100%",
                backgroundColor: "#fff"
              }}
            >
              <Text style={{ textAlign: "center" }}>save</Text>
            </TouchableOpacity>
          )}
          <TouchableOpacity
            onPress={toggleRecord}>
            <FontAwesome 
            name= {capturing ? "stop-circle" : "circle-thin"}
            style={{
              paddingBottom: 160,
              color: capturing ? "#ef4f84" : "white",
              fontSize: 90,
              
            }}
            /> 
        </TouchableOpacity>
      </Col>
          <Col style={styles.alignCenter}>
            <Ionicons
              name="ios-reverse-camera"
              color="white"
              size={45}
              style = {{
                paddingBottom: 115,
              }}
              onPress={() =>
                setCameraType(
                  cameraType === CameraTypes.back
                    ? CameraTypes.front
                    : CameraTypes.back
                )
              }
            />
          </Col>
    </Row>
  </Grid>
      );
