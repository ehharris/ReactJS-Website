import React, {Component} from 'react';
import { Container, Row, Col, Card, CardBody, Nav, NavItem, NavLink, TabPane, TabContent} from 'reactstrap';
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
            activeTab: 'Map',
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
        this.toggle = this.toggle.bind(this);
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
  
    toggle(tab) {
        if (this.state.activeTab !== tab) {
            this.setState({
                activeTab: tab
            });
        }
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

    updateServer(newHost, newPort) {
        this.setState({server: newHost});
        this.setState({port: newPort});

        get_config(newPort, newHost).then((newConfig) => (
            this.setState({config: newConfig})
        ));

    }

    //Render
    renderNav(){
        if(this.state.trip.places.length >= 2){
          let arr0 = ['Map','Itinerary']
            return(
              <div>
                <Nav tabs className="cooltabs">
                  {arr0.map((value) =>
                      <NavItem key={value+1}>
                        <NavLink key={value+2} active={this.state.activeTab === value}
                          className="tabs"
                          onClick={() => { this.toggle(value); }}>
                          {value}
                        </NavLink>
                      </NavItem>
                    )
                  }
                </Nav>
              </div>
            );
        }
    }

    renderTabs(){
      if(this.state.trip.places.length >= 2) {
        return (
          <div>
            <TabContent activeTab={this.state.activeTab}>
              <TabPane tabId="Map">
                <Card>
                  <CardBody>
                    <Map trip={this.state.trip}/>
                  </CardBody>
                </Card>
              </TabPane>
              <TabPane tabId="Itinerary">
                <Card>
                  <CardBody>
                    <ItineraryTable trip={this.state.trip} updateBasedOnResponse={this.updateBasedOnResponse}/>
                  </CardBody>
                </Card>
              </TabPane>
            </TabContent>
          </div>
        )
      }
    }

    renderOptions(){
      return(
        <div>
          <Options options={this.state.trip.options} config={this.state.config}
                   updateOptions={this.updateOptions}/>
          <Optimization updateOptions={this.updateOptions} config={this.state.config}
                        options={this.state.trip.options}/>
        </div>
      )
    }

    render() {
        if(!this.state.config) { return <div/> }
        return(
            <Container id="Application">
                {this.renderNav()}
                {this.renderTabs()}
                <File updateBasedOnResponse={this.updateBasedOnResponse} trip={this.state.trip}/>
                <Row noGutters={true}>
                    <Col>
                        <Add updatePlaces={this.updatePlaces} places={this.state.trip.places}/>
                        <Port updateServer={this.updateServer}/>
                        <Search server={this.state.server} port={this.state.port} trip={this.state.trip} updateBasedOnResponse={this.updateBasedOnResponse}/>
                    </Col>
                    <Col>
                        <Card>
                            <CardBody>
                              {this.renderOptions()}
                            </CardBody>
                        </Card>
                        <Calculator/>
                    </Col>
                </Row>
            </Container>
        )
    }
}

export default Application;
