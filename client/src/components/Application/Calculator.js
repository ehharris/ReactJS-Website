import React, {Component} from 'react'
import {Input, Button, CardBody, Card, Form, Label, InputGroup, Fade} from 'reactstrap';
import {request} from "../../api/api";


class Calculator extends Component{
  constructor(props) {
    super(props);
    this.state = {
      show: false,
      gen1: ["origin", "destination"],
      gen2: ["latitude", "longitude"],
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
    const calculator = this.state.gen1.map((type) =>
      <Form inline key={type+1}>
        <Label className="labelpre" key={type+2}>{type.charAt(0).toUpperCase() + type.slice(1)}</Label>
        <InputGroup key={type+3}>
          {this.state.gen2.map((type2) =>
            <Input key={type+type2} className="inputborder"
              placeholder="Latitude" value={type}
              onChange={(event) => this.updateLatLong(type, type2, event.target.value)}/>)}
        </InputGroup>
      </Form>);
    return(
      <Card>
        <CardBody>
          {calculator}
          <Form inline>
            <Button onClick={this.calculate} type="button" className='btn-outline-dark unit-button'>
              Calculate</Button>
            <Fade in={this.state.show}>
              <Label sm={{ size: 2, offset: 4 }} className="labelpop">
                {this.state.distance.distance} {this.state.distance.units}</Label></Fade>
          </Form>
        </CardBody>
      </Card>
    )
  }


}

export default Calculator;
