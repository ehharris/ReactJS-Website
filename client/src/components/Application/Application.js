import React, {Component} from 'react';
import { Container } from 'reactstrap';
import Info from './Info'
import Options from './Options';
import Map from './Map';
import Port from './Port';
import File from './File';

import { get_config } from '../../api/api';
import Itinerary from "./Itinerary";

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
          units: "miles"
          optimization: "none"
        },
        places: [{
            "id": "dnvr",
            "name": "Denver",
            "latitude": 39.7392,
            "longitude": -104.9903
        }, {
            "id": "bldr",
            "name": "Boulder",
            "latitude": 40.015,
            "longitude": -105.2706
        }],
        distances: [24,25],
        map: '<svg width="1920" height="20" xmlns="http://www.w3.org/2000/svg" xmlns:svg="http://www.w3.org/2000/svg"><g></g></svg>'
      }
    };
    this.updateTrip = this.updateTrip.bind(this);
    this.updateBasedOnResponse = this.updateBasedOnResponse.bind(this);
    this.updateOptions = this.updateOptions.bind(this);
    this.uploadFile = this.uploadFile.bind(this);
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
    this.setState({'trip': value});
  }

  updateOptions(option, value){
    let trip = this.state.trip;
    trip.options[option] = value;
    this.setState(trip);
  }
  
  //TODO File Upload unfinished
  //Taken from Piazza - Caleb Carlson - https://piazza.com/class/jkocdn0g2lm2f7?cid=180
  uploadFile(event){
      let file = event.target.files[0]; // first file in the FileList
      if (file) {
          var reader = new FileReader();
          reader.onload = function(event) {
                // The file's text will be printed here
                // event.target.result is the file in string representation,
                // it is up to you to handle the rest
              console.log(event.target.result);
          };
          reader.readAsText(file);
      }
  }

  render() {
    if(!this.state.config) { return <div/> }

    return(
      <Container id="Application">
        <Info/>
        <File updateBasedOnResponse={this.updateBasedOnResponse} trip={this.state.trip}/>
        <Options options={this.state.trip.options} config={this.state.config} updateOptions={this.updateOptions}/>
        <Port/>
        <p className="Table-header">Itinerary</p>
        <Itinerary data={this.state.trip}/>
        <Map/>
      </Container>
    )
  }
}

export default Application;
