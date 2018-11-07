import React, {Component} from 'react';
import { Container, Row, Col, Card } from 'reactstrap';
import Info from './Info'
import Options from './Options';
import Map from './Map.jsx';
import Port from './Port';
import File from './File';
import ItineraryTable from './Itinerary.jsx';
import Search from './Search';
import Calculator from './Calculator';
import Optimization from './Optimization';
import Add from './Add';

import { request } from '../../api/api';
import { get_config } from '../../api/api';

/* Renders the application.
 * Holds the destinations and options state shared with the trip.
 */
class Application extends Component {
    constructor(props){
        super(props);
        this.state = {
            config: null,
            server: location.hostname,
            port: '31410',
            trip: {
                type: "trip",
                title: "",
                options : {
                    units: "miles",
                    optimization: "none"
                },
                places: [],
                distances: [],
                map: '<svg width="1920" height="20" xmlns="http://www.w3.org/2000/svg" xmlns:svg="http://www.w3.org/2000/svg"><g></g></svg>'
            }
        };
        this.updateTrip = this.updateTrip.bind(this);
        this.updateBasedOnResponse = this.updateBasedOnResponse.bind(this);
        this.updateOptions = this.updateOptions.bind(this);
        this.updateServer = this.updateServer.bind(this);
        this.updatePlaces = this.updatePlaces.bind(this);
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
        request(value, "plan", this.state.port, this.state.server)
            .then((resData) => this.setState({trip: resData}));
    }


    updateOptions(option, value){
        let trip = this.state.trip;
        trip.options[option] = value;
        this.setState(trip);
    }

    updatePlaces(places){
        this.setState({'places': places});
        if (this.state.trip.places.length >= 2) {
            this.updateBasedOnResponse(this.state.trip);
        }
    }

    updateServer(value1, value2) {
        this.setState({server: value1});
        this.setState({port: value2});
    }

    render() {
        if(!this.state.config) { return <div/> }
        return(
            <Container id="Application">
                <Info/>
                <Row>
                    <Col>
                        <File updateBasedOnResponse={this.updateBasedOnResponse} trip={this.state.trip}/>
                        <Port updateServer={this.updateServer}/>
                        <Add updatePlaces={this.updatePlaces} places={this.state.trip.places}/>
                    </Col>
                    <Col>
                        <Card>
                            <Options options={this.state.trip.options} config={this.state.config}
                                     updateOptions={this.updateOptions}/>
                            <Optimization updateOptions={this.updateOptions} config={this.state.config}
                                          options={this.state.trip.options}/>
                        </Card>
                        <Calculator/>
                    </Col>
                </Row>
                <Map trip={this.state.trip}/>
                <ItineraryTable trip={this.state.trip} updateBasedOnResponse={this.updateBasedOnResponse}/>
                <Search server={this.state.server} port={this.state.port} trip={this.state.trip} updateBasedOnResponse={this.updateBasedOnResponse}/>
            </Container>
        )
    }
}

export default Application;
