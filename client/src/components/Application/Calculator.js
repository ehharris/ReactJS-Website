import React, {Component} from 'react'
import {Input, Button, CardBody, ButtonToolbar, Card, Form, FormGroup, Label,} from 'reactstrap';
import {request} from "../../api/api";


class Calculator extends Component{
  constructor(props) {
    super(props);
    this.state = {
      distance: {
        type: "distance",
        version: "3",
        origin : {
          latitude: 0,
          longitude: 0
        },
        destination: {
          latitude: 0,
          longitude: 0
        },
        units: "miles",
        distance: 0,
      }
    };

    this.plan = this.plan.bind(this);
    this.updateLatLong = this.updateLatLong.bind(this);
  }

  updateLatLong(option1, option2, value) {
    let start = this.state.distance[option1];
    start[option2] = value;
    this.setState(start);
  }

  // updateDestination(option, value){
  //   let distance = this.state.distance.destination;
  //   distance[option] = value;
  //   this.setState(distance);
  // }

  plan(){
    let responsePlan = request(this.state.distance, 'distance').then(
      res => {this.setState({'distance': res});}
    );

    console.log("TESTIES: " + this.state.distance.distance);
  }

  render() {

    return(
      <Card>
        <CardBody>
          <p>Calculate the distance between two places!</p>
          <p>{"\n"}</p>
            <Form inline>
              <FormGroup className="mb-2 mr-sm-2 mb-sm-0">
                <Label for="unitName" className="mr-sm-2">Latitude Origin</Label>
                <Input
                  type="unitName"
                  name="unit name"
                  id="unitName"
                  placeholder="0"
                  onChange={(event) => this.updateLatLong('origin', 'latitude', event.target.value)}
                />
              </FormGroup>
              <FormGroup className="mb-2 mr-sm-2 mb-sm-0">
                <Label for="unitRadius" className="mr-sm-2">Longitude Origin</Label>
                <Input
                  type="unitRadius"
                  name="unit radius"
                  id="exampleEmail"
                  placeholder="0"
                  onChange={(event) => this.updateLatLong('origin', 'longitude', event.target.value)}
                />
              </FormGroup>
            </Form>
          <p>{"\n"}</p>
          <Form inline>
            <FormGroup className="mb-2 mr-sm-2 mb-sm-0">
              <Label for="unitName" className="mr-sm-2">Latitude Destination</Label>
              <Input
                type="unitName"
                name="unit name"
                id="unitName"
                placeholder="0"
                onChange={(event) => this.updateLatLong('destination', 'latitude', event.target.value)}
              />
            </FormGroup>
            <FormGroup className="mb-2 mr-sm-2 mb-sm-0">
              <Label for="unitRadius" className="mr-sm-2">Longitude Destination</Label>
              <Input
                type="unitRadius"
                name="unit radius"
                id="exampleEmail"
                placeholder="0"
                onChange={(event) => this.updateLatLong('destination', 'longitude', event.target.value)}
              />
            </FormGroup>
          </Form>
          <p>{"\n"}</p>
            <Button onClick={this.plan} type="button" className='btn-outline-dark unit-button'>
              Calculate!
            </Button>
          <p>{"\n"}</p>
          <p>Result: {this.state.distance.distance}</p>
        </CardBody>
      </Card>
    )

  }
}

export default Calculator;
