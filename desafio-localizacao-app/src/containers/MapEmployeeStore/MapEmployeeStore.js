import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Map, GoogleApiWrapper, InfoWindow, Marker } from 'google-maps-react';
import { withStyles } from '@material-ui/core/styles';

import { GOOGLE_API } from '../../constants';
import { map, mapData } from '../../actions/employee';
import Loading from '../../components/Loading';
import Error from '../../components/Error';

class MapEmployeeStore extends Component {

    state = {
        showingInfoWindow: false,  //Hides or the shows the infoWindow
        activeMarker: {},          //Shows the active marker upon click
        selectedPlace: {}          //Shows the infoWindow to the selected place upon a marker
    };

    componentDidMount() {
        this.props.dispatch(mapData(this.props.match.params));
    }

    componentWillUnmount() {
        this.props.dispatch(map());
    }

    onMarkerClick = (props, marker) => {
        this.setState({
            selectedPlace: props,
            activeMarker: marker,
            showingInfoWindow: true
        });
    };
    
    onClose = () => {
        if (this.state.showingInfoWindow) {
            this.setState({
               showingInfoWindow: false,
                activeMarker: null
            });
        }
    };

    render() {

        const { loading, erro, data, classes } = this.props;

        if (loading) { return <Loading /> }

        if (erro) { return <Error /> }

        return (
          <div className={classes.root}>
            <Map
              google={this.props.google}
              zoom={13}
              initialCenter={{ lat: data.employee.latitude, lng: data.employee.longitude }}>
              <Marker onClick={this.onMarkerClick}
                title={data.employee.name}
                name={data.employee.name}
                />
                {data.stores.map(item => (
                    <Marker onClick={this.onMarkerClick}
                        key={item.id}
                        title={item.name}
                        name={item.name}
                        position={{lat: item.latitude, lng: item.longitude }}
                        icon={'http://maps.google.com/mapfiles/ms/icons/blue-dot.png'} />
                ))}
              <InfoWindow
                marker={this.state.activeMarker}
                visible={this.state.showingInfoWindow}
                onClose={this.onClose} >
                <div>
                  <h4>{this.state.selectedPlace.name}</h4>
                </div>
              </InfoWindow>
            </Map>
          </div>
        );
    }
}

const styles = () => ({
    root: {
        position: 'absolute',
        width: '100%',
        height: '90%',
    },
});

const mapStateToProps = state => {
    return {
        data: state.employee.dataMap,
        loading: state.employee.loading,
        erro: state.employee.erro,
    }
}

MapEmployeeStore = withStyles(styles)(MapEmployeeStore)
MapEmployeeStore = GoogleApiWrapper({ apiKey: GOOGLE_API })(MapEmployeeStore)
MapEmployeeStore = connect(mapStateToProps)(MapEmployeeStore)
export default MapEmployeeStore;