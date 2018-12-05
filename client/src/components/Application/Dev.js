
import React, {Component} from 'react'
import { CardImg, Card, CardGroup, CardBody, CardTitle, ModalFooter, CardText} from 'reactstrap'
import { Modal, Button, ButtonGroup, ModalBody, ModalHeader, Col } from 'reactstrap'
import avatarAntonio from './Resource/t10-Antonio.jpg';
import avatarEli from './Resource/t10-Eli.jpg';
import avatarNick from './Resource/t10-Nick.jpg';
import avatarTyler from './Resource/t10-Tyler.jpg';
import avatarDave from './Resource/Dave-Matthews.jpg';

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
class Dev extends Component{
  constructor(props) {
    super(props);
    this.state = {
      modal: false
    };

    this.toggle = this.toggle.bind(this);
  }

  toggle() {
    this.setState({
      modal: !this.state.modal
    });
  }

  getName(input){
    let name = "";
    if(input === avatarAntonio){
      name = "Antonio Segovia Maldonado";
    } else if(input === avatarEli){
      name = "Eli Harris";
    } else if(input === avatarNick){
      name = "Nick Kaliher";
    } else {
      name = "Tyler Dansby";
    }
    return(name)
  }

  renderManager(){
    return(
      <div>
        <div>
          <Col><Button className='btn-outline-dark unit-button' onClick={this.toggle}>Meet the Manager</Button></Col>
        </div>
        <Modal contentClassName={"modalT"} isOpen={this.state.modal} toggle={this.toggle}>
         <ModalHeader toggle={this.toggle}>Manager</ModalHeader>
          <ModalBody>
            <img src={avatarDave} className={"avatarborder"} height={480} width={280}/>
          </ModalBody>
          <ModalFooter>Dave Matthews</ModalFooter>
        </Modal>
      </div>
    )
  }

  render() {
    let fileArr = [avatarAntonio, avatarEli, avatarNick, avatarTyler];
    const cardRend = fileArr.map((input) =>
      <Card outline color="white" key={input}>
        <CardImg className={"avatarborder"} src={input} key={input+1}/>
        <CardBody key={input+2}>
          <CardTitle className={"headerText"} key={input+3}>{this.getName(input)}</CardTitle>
        </CardBody>
      </Card>
    );
    return(
      <Card body className="text-center" outline color="secondary">
        <CardGroup>
          {cardRend}
        </CardGroup>
        <hr/>
        {this.renderManager()}
      </Card>
    )
  }
}

export default Dev;
