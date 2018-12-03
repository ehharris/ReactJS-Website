
import React, {Component} from 'react'
import { CardImg, Card, CardGroup, CardBody, CardTitle, CardSubtitle, CardText} from 'reactstrap'
import { ButtonGroup, Button, Form, FormGroup, Label, Input } from 'reactstrap'
import avatarAntonio from './Resource/t10-Antonio.jpg';
import avatarEli from './Resource/t10-Eli.jpg';
import avatarNick from './Resource/t10-Nick.jpg';
import avatarTyler from './Resource/t10-Tyler.jpg';

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
class Dev extends Component{
  constructor(props) {
    super(props);
  }

  render() {
    return(
      <CardGroup>
        <Card body className="text-center" outline color="secondary">
          <CardImg className={"avatarborder"} src={avatarAntonio}/>
          <CardBody>
            <CardTitle>Antonio Segovia Maldonado</CardTitle>
          </CardBody>
        </Card>
        <Card body className="text-center" outline color="secondary">
          <CardImg className={"avatarborder"} src={avatarEli}/>
          <CardBody>
            <CardTitle>Eli Harris</CardTitle>
          </CardBody>
        </Card>
        <Card body className="text-center" outline color="secondary">
          <CardImg className={"avatarborder"} src={avatarNick}/>
          <CardBody>
            <CardTitle>Nick Kaliher</CardTitle>
          </CardBody>
        </Card>
        <Card body className="text-center" outline color="secondary">
          <CardImg className={"avatarborder"} src={avatarTyler}/>
          <CardBody>
            <CardTitle>Tyler Dansby</CardTitle>
          </CardBody>
        </Card>
      </CardGroup>

    )
  }
}

export default Dev;
