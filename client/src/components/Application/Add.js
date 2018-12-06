import React, {Component} from 'react'
import {Input, Button, CardBody, Fade, Card, Form, Label, ModalHeader, ModalBody, Modal} from 'reactstrap';


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
  }, 1500);
  }

  renderForm(){
    return(
      <Form inline>
        <Button onClick={this.createPlace} type="button"
                className='btn-outline-dark unit-button'> Add </Button>
        <Fade in={this.state.message}>
          <Label className="labelpop">{this.state.place.name} was successfully added to your trip!
          </Label>
        </Fade>
      </Form>
    )
  }

  render() {
    const forms = this.state.input.map((element) =>
      <Form inline key={element+1}>
        <Label key={element+2} className="labelpre">
          {element.charAt(0).toUpperCase() + element.slice(1)}</Label>
        <Input className="inputborderadd" key={element+3}
               value={this.state.place[element]}
               onChange={(event) => this.update(element, event.target.value)}/>
      </Form>);
    return(
      <Modal contentClassName={"modalT"} isOpen={this.props.modal1} toggle={() => {this.props.toggleMod('1')}}>
        <div className="my-modal-window">
          <ModalHeader toggle={() => {this.props.toggleMod('1')}}>Add a new place to your trip!</ModalHeader>
          <ModalBody>
            <Card>
              <CardBody>
                {forms}
                {this.renderForm()}
              </CardBody>
            </Card>
          </ModalBody>
        </div>
      </Modal>
    )
  }
}

export default Add;