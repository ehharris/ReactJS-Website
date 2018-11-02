import React, {Component} from 'react'
import { Card, CardBody, Collapse } from 'reactstrap'
import { ButtonGroup, Button, Form, FormGroup, Label, Input } from 'reactstrap'

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
class Optimization extends Component{
  constructor(props) {
    super(props);
  }

  
  render() {

    const optimizations = this.props.config.optimization.map((label) =>
      <Button
        key={'optimization_button_' + label.label}
        className='btn-outline-dark unit-button'
        active={this.props.options.optimization === label.label}
        value={label.label}
        onClick={(event) => {this.props.updateOptions('optimization', event.target.value)}}
      >
        {label.label.charAt(0).toUpperCase() + label.label.slice(1)}
      </Button>
    );

    return(
        <CardBody>
          <p>Optimization</p>
          <ButtonGroup>
            {optimizations}
          </ButtonGroup>
        </CardBody>
    )
  }
}

export default Optimization;