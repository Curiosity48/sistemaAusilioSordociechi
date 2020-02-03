import React from 'react';
//import TcpSocket from 'react-native-tcp-socket';
import {
  StyleSheet,
  Text,
  View,
  TouchableOpacity,
  TextInput,
} from 'react-native';

export default class geoLocation extends React.Component {
  constructor() {
    super();
    this.state = {
      ready: false,
      where: {
        lat: null,
        lon: null,
      },
      error: null,
    };
  }

  componentDidMount() {
    let geoOptions1 = {
      enableHighAccuracy: true,
      timeOut: 1000,
      maximumAge: 0,
    };

    let geoOptions2 = {
      enableHighAccuracy: true,
      timeOut: 1000,
      maximumAge: 0,
      useSignificantChanges: false,
      distanceFilter: 1,
    };

    this.setState({ ready: false, error: null });
    navigator.geolocation.getCurrentPosition(
      this.geoSuccess,
      this.geoFailure,
      geoOptions1
    );
    navigator.geolocation.watchPosition(
      this.geoSuccess,
      this.geoFailure,
      geoOptions2
    );
  }

  geoSuccess = position => {
    this.setState({
      ready: true,
      where: { lat: position.coords.latitude, lon: position.coords.longitude },
    });
  };

  geoFailure = err => {
    this.setState({ error: err.message });
  };

  componentWillUnmount() {
    navigator.geolocation.stopObserving();
  }

  render() {
    return (
      <View style={styles.container}>
        {!this.state.ready && alert('GEOLOCALIZZAZIONE FALLITA')}

        <Text>SeeSharp</Text>

        <Text style={{ marginTop: 10 }}>LATITUDINE:</Text>
        {this.state.ready && (
          <TextInput
            editable={false}
            maxLength={40}
            style={{
              height: 30,
              width: 100,
              borderColor: 'black',
              borderWidth: 1,
              backgroundColor: 'white',
              marginTop: 5,
            }}>
            {this.state.where.lat}
          </TextInput>
        )}

        <Text style={{ marginTop: 10 }}>LONGITUDINE:</Text>
        {this.state.ready && (
          <TextInput
            editable={false}
            maxLength={40}
            style={{
              height: 30,
              width: 100,
              borderColor: 'black',
              borderWidth: 1,
              backgroundColor: 'white',
              marginTop: 5,
            }}>
            {this.state.where.lon}
          </TextInput>
        )}

        <TouchableOpacity
          onPress={() => alert('SAS')}
          style={{ backgroundColor: 'blue', padding: 10, marginTop: 10 }}>
          <Text style={{ fontSize: 20, color: '#fff' }}>CLICCA</Text>
        </TouchableOpacity>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: 'gray',
  },
});
