import React, {Component} from 'react';
import { Container } from 'reactstrap';
import Info from './Info'
import Options from './Options';
import Map from './Map';
import Port from './Port';
import File from './File';
import ItineraryTable from './Itinerary.jsx';

import { get_config } from '../../api/api';


/* Renders the application.
 * Holds the destinations and options state shared with the trip.
 */
class Application extends Component {
  constructor(props){
    super(props);
    this.state = {
      config: null,
      trip: {
        type: "trip",
        title: "",
        options : {
          unit: "",
          optimization: ""
        },
        places: [{}],
        distances: [],
        map: '<svg width="1920" height="20" xmlns="http://www.w3.org/2000/svg" xmlns:svg="http://www.w3.org/2000/svg"><g></g></svg>'
      }
    };
    this.updateTrip = this.updateTrip.bind(this);
    this.updateBasedOnResponse = this.updateBasedOnResponse.bind(this);
    this.updateOptions = this.updateOptions.bind(this);
  }

  componentWillMount() {
    get_config().then(
      config => {
        this.setState({
          config:config
        })
      }
    );
  }

  updateTrip(field, value){
    let trip = this.state.trip;
    trip[field] = value;
    this.setState(trip);
  }

  updateBasedOnResponse(value) {
      fetch('http://' + location.hostname + ":31410/plan", {
          method: 'POST',
          body: JSON.stringify(value),
          headers: {
              'Content-Type': 'applications/json',
              'Accept': 'applications/json'
          }
      })
          .then(response => response.json())
          .then(resData => this.setState({trip: resData}));
  }


  updateOptions(option, value){
    let trip = this.state.trip;
    trip.options[option] = value;
    this.setState(trip);
  }

  render() {
    if(!this.state.config) { return <div/> }
    return(
      <Container id="Application">
        <Info/>
        <Map trip={this.state.trip}/>
        <ItineraryTable data={this.state.trip}/>
        <Options options={this.state.trip.options} config={this.state.config} updateOptions={this.updateOptions}/>
        <Port/>
        <File updateBasedOnResponse={this.updateBasedOnResponse} trip={this.state.trip}/>
      </Container>
    )
  }
}

export default Application;
