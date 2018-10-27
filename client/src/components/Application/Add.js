import React, {Component} from 'react'
import {Input, Button, CardBody, Fade, Card, Form, FormGroup, ButtonGroup } from 'reactstrap';


class Add extends Component{
  constructor(props) {
    super(props);
    this.state = {
      place: {
        id: '',
        name: '',
        latitude: '',
        longitude: '',
      },
      message: false,
      input: ['id','name','latitude','longitude'],
    };

    this.createPlace = this.createPlace.bind(this);
    this.update = this.update.bind(this);
    this.message = this.message.bind(this);
  }

  createPlace(){
    if((this.state.place.id !== '') && (this.state.place.name !== '') &&
      (this.state.place.latitude !== '') && (this.state.place.longitude !== '')){

      if(!isNaN(this.state.place.longitude) && !isNaN(this.state.place.longitude)){

        let places = this.props.places;
        places.push(this.state.place);
        this.props.updatePlaces(places);
        this.message();
      }
    }
  }

  update(option, value) {
    let start = this.state.place;
    start[option] = value;
    this.setState({place: start});
  }

  message(){
    this.setState({message: true})
    setTimeout(() => {
      this.setState({
      place: {
        id: '',
        name: '',
        latitude: '',
        longitude: '',
      },
      message: false,
    })
  }, 2000);
  }


  render() {
    const forms = this.state.input.map((element) =>
      <Form>
        <Input
          value={this.state.place[element]} placeholder={element}
          onChange={(event) => this.update(element, event.target.value)}
        />
      </Form>
  );
    return(
      <Card>
        <CardBody>
        <p>Add a new place to your trip!</p>
          {forms}
          <Button onClick={this.createPlace} type="button"
            className='btn-outline-dark unit-button'>
              Add
          </Button>
          <Fade in={this.state.message}>
            {this.state.place.name} was successfully added to your trip!
          </Fade>
      </CardBody>
    </Card>
  )
  }

}

export default Add;