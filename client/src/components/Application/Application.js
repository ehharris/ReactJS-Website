import React, {Component} from 'react';
import {
  Label,
  CardGroup,
  Container,
  Alert,
  Card,
  CardBody,
  Nav,
  NavItem,
  NavLink,
  TabPane,
  TabContent,
  ModalHeader,
  ModalBody, Modal, Button
} from 'reactstrap';
import Options from './Options';
import Map from './Map.jsx';
import Port from './Port';
import File from './File';
import ItineraryTable from './Itinerary.jsx';
import Search from './Search';
import Calculator from './Calculator';
import Optimization from './Optimization';
import Add from './Add';
import Dev from './Dev';
import Info from './Info';
import Plan from './Plan';


import { request } from '../../api/api';
import { get_config } from '../../api/api';
import avatarDave from "./Resource/Dave-Matthews.jpg";

/* Renders the application.
 * Holds the destinations and options state shared with the trip.
 */
class Application extends Component {
    constructor(props){
        super(props);
        this.state = {
            config: null,
            activeTab1: 'Map',
            activeTab2: 'Planner',
            modal1: false,
            modal2: false,
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
        this.toggleTab = this.toggleTab.bind(this);
        this.toggleMod = this.toggleMod.bind(this);
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

    componentDidMount(){
      let units = this.getCookie("units");
      let optimization = this.getCookie("optimization");
      let unitName = this.getCookie("unitName");
      let unitRadius = this.getCookie("unitRadius");

      if (units !== "") {
        this.updateOptions('units', units);
      }
      if (optimization !== "") {
        this.updateOptions('optimization', optimization);
      }
      if (unitName !== "") {
        this.updateOptions('unitName', unitName);
      }
      if (unitRadius !== "") {
        this.updateOptions('unitRadius', unitRadius);
      }
    }

    toggleTab(value, tab) {
        if(value === '1'){
          this.setState({activeTab1: tab});
        }
        if(value === '2'){
          this.setState({activeTab2: tab});
        }
    }

    toggleMod(value) {
      if(value === '1'){
        this.setState({modal1: !this.state.modal1});
      }
      if(value === '2'){
        this.setState({modal2: !this.state.modal2});
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
        if (this.state.trip.places.length >= 1) {
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

    //Code for manipulating cookies taken from W3 Schools - https://www.w3schools.com/js/js_cookies.asp
    getCookie(cname) {
      let name = cname + "=";
      let ca = document.cookie.split(';');
      for(let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) === ' ') {
          c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
          return c.substring(name.length, c.length);
        }
      }
      return "";
    }

    setCookie(cname, cvalue, exdays) {
      let d = new Date();
      d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
      let expires = "expires="+d.toUTCString();
      document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }

    renderNav(){
          let arr0 = ['Map','Itinerary']
            return(
              <div>
                <Nav tabs className="cooltabs">
                  {arr0.map((value) =>
                      <NavItem key={value+1}>
                        <NavLink key={value+2} active={this.state.activeTab1 === value}
                          className="tabs"
                          onClick={() => { this.toggleTab('1', value); }}>
                          {value}
                        </NavLink>
                      </NavItem>
                    )
                  }
                </Nav>
              </div>
            );
    }

    renderMap(){
      if(this.state.trip.places.length >= 1){
        return(
          <Map trip={this.state.trip}/>
        )
      } else {
        return(
          <Alert color="info">Add at least one place to your trip to get a map.</Alert>
        )
      }
    }

    renderItinerary(){
      if(this.state.trip.places.length >= 1){
        return(
          <ItineraryTable trip={this.state.trip} updateBasedOnResponse={this.updateBasedOnResponse}/>
        )
      } else {
        return(
          <Alert color="info">Add at least one place to your trip to get an itinerary.</Alert>
        )
      }
    }

    renderTabs(){
        return (
          <div>
            <TabContent activeTab={this.state.activeTab1}>
              <TabPane tabId="Map">
                <hr/>
                <Card>
                  <CardBody>
                    {this.renderMap()}
                  </CardBody>
                </Card>
              </TabPane>
              <TabPane tabId="Itinerary">
                <hr/>
                <Card>
                  <CardBody>
                    {this.renderItinerary()}
                  </CardBody>
                </Card>
              </TabPane>
            </TabContent>
          </div>
        )
    }

    renderOptions(){
      return(
        <Card>
          <CardBody>
            <Options options={this.state.trip.options} config={this.state.config}
                     updateOptions={this.updateOptions} setCookie={this.setCookie}/>
            <Optimization updateOptions={this.updateOptions} config={this.state.config}
                          options={this.state.trip.options} setCookie={this.setCookie}/>
          </CardBody>
        </Card>
      )
    }

    renderAddAndSearch(){
      return(
        <Card>
          <CardBody>
            <p>Add additional places to your trip.</p>
            &nbsp;
            <Button className='btn-outline-dark unit-button' onClick={() => { this.toggleMod('1')}}>
              Add your own place
            </Button>
            {' '}<Label> or </Label>{' '}
            <Button className='btn-outline-dark unit-button' onClick={() => { this.toggleMod('2')}}>
              Search worldwide for a place
            </Button>
          </CardBody>
        </Card>
      )
    }

    render() {


        if(!this.state.config) { return <div/> }
        return(
            <Container id="Application">

              <Info/>

              <hr/>

              <Nav tabs className="cooltabs">
                <NavItem>
                  <NavLink active={this.state.activeTab2 === 'Planner'}
                           className="tabs"
                           onClick={() => { this.toggleTab('2','Planner'); }}>
                    Trip Planner
                  </NavLink>
                </NavItem>
                <NavItem>
                  <NavLink active={this.state.activeTab2 === 'About'}
                           className="tabs"
                           onClick={() => { this.toggleTab('2','About'); }}>
                    Meet the Developers
                  </NavLink>
                </NavItem>
              </Nav>

              <hr/>

              <TabContent activeTab={this.state.activeTab2}>

                <TabPane tabId="Planner">
                  <Card body outline color="secondary">

                    <CardGroup>
                        <File updateBasedOnResponse={this.updateBasedOnResponse} trip={this.state.trip}/>
                        {this.renderAddAndSearch()}
                    </CardGroup>

                    <hr/>

                    <Modal contentClassName={"modalT"} isOpen={this.state.modal1} toggle={() => {this.toggleMod('1')}}>
                      <ModalHeader toggle={() => {this.toggleMod('1')}}>Add a new place to your trip!</ModalHeader>
                      <ModalBody>
                        <Add updatePlaces={this.updatePlaces} places={this.state.trip.places}/>
                      </ModalBody>
                    </Modal>

                    <Modal contentClassName={"modalT"} isOpen={this.state.modal2} toggle={() => {this.toggleMod('2')}}>
                      <ModalHeader toggle={() => {this.toggleMod('2')}}>Search worldwide for a new place!</ModalHeader>
                      <ModalBody>
                        <Search server={this.state.server} port={this.state.port} places={this.state.trip.places} config={this.state.config} updatePlaces={this.updatePlaces}/>
                      </ModalBody>
                    </Modal>



                    <CardGroup>
                      {this.renderOptions()}
                      <Calculator/>
                    </CardGroup>

                    <hr/>

                    <Port updateServer={this.updateServer}/>

                    <hr/>

                    <Plan updateBasedOnResponse={this.updateBasedOnResponse} trip={this.state.trip}/>

                    <hr/>

                    {this.renderNav()}
                    {this.renderTabs()}

                  </Card>
                </TabPane>

                <TabPane tabId="About">
                  <Dev/>
                </TabPane>

              </TabContent>

            </Container>
        )
    }
}

export default Application;
