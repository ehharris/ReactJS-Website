import React, {Component} from 'react'
import {Input, Button, CardBody, Card, Form, Label, InputGroup, Fade} from 'reactstrap';
import {request} from "../../api/api";


class Calculator extends Component{
  constructor(props) {
    super(props);
    this.state = {
      show: false,
      distance: {
        type: "distance",
        version: "4",
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
    this.calculate = this.calculate.bind(this);
    this.updateLatLong = this.updateLatLong.bind(this);
  }

  updateLatLong(option1, option2, value) {
    let start = this.state.distance[option1];
    start[option2] = value;
    this.setState(start);
  }

  calculate(){
    let responsePlan = request(this.state.distance, 'distance')
      .then(res => {this.setState({'distance': res});}
    );
    this.setState({show: true});
  }

  render() {

    return(
      <Card>
        <CardBody>
          <p>Calculate the distance between two places!</p>
          <Form inline>
            <Label className="labelpre">Origin</Label>
            <InputGroup>
              <Input
                className="inputborder"
                placeholder="Latitude"
                onChange={(event) => this.updateLatLong('origin', 'latitude', event.target.value)}
              />
              <Input
                className="inputborder"
                placeholder="Longitude"
                onChange={(event) => this.updateLatLong('origin', 'longitude', event.target.value)}
              />
            </InputGroup>
          </Form>
          <Form inline>
            <Label className="labelpre">Destination</Label>
            <InputGroup>
              <Input
                className="inputborder"
                placeholder="Latitude"
                onChange={(event) => this.updateLatLong('destination', 'latitude', event.target.value)}
              />
              <Input
                className="inputborder"
                placeholder="Longitude"
                onChange={(event) => this.updateLatLong('destination', 'longitude', event.target.value)}
              />
            </InputGroup>
          </Form>
          <Form inline>
            <Button onClick={this.calculate} type="button" className='btn-outline-dark unit-button'>
              Calculate
            </Button>
            <Fade in={this.state.show}>
              <Label sm={{ size: 2, offset: 4 }} className="labelpop">{this.state.distance.distance} {this.state.distance.units}</Label>
            </Fade>
          </Form>
        </CardBody>
      </Card>
    )

  }
}

export default Calculator;
