
import React, {Component} from 'react'
import { Card, CardBody, Collapse } from 'reactstrap'
import { ButtonGroup, Button, Form, FormGroup, Label, Input } from 'reactstrap'

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
class Options extends Component{
    constructor(props) {
        super(props);
        this.toggle = this.toggle.bind(this);
        this.state = { collapse: false };
    }

    toggle(event) {
      if(event === "user defined"){
        this.setState({ collapse: true });
      } else {
        this.setState({ collapse: false });
      }

    }

    render() {
        const units = this.props.config.units.map((unit) =>
            <Button
                key={'distance_button_' + unit}
                className='btn-outline-dark unit-button'
                active={this.props.options.units === unit}
                value={unit}
                onClick={(event) => {this.props.updateOptions('units', event.target.value); this.toggle(event.target.value)}}
            >
                {unit.charAt(0).toUpperCase() + unit.slice(1)}
            </Button>
        );

        const userDefinedOptions =
            <Form inline>
              <FormGroup>
                <Input
                  type="unitName"
                  name="unitName"
                  id="unitName"
                  placeholder="Unit Name"
                  onChange={(event) => this.props.updateOptions('unitName', event.target.value)}
                />
              </FormGroup>
              <FormGroup>
                <Input
                  type="unitRadius"
                  name="unitRadius"
                  id="exampleEmail"
                  placeholder="Radius of Earth"
                  onChange={(event) => this.props.updateOptions('unitRadius', event.target.value)}
                />
              </FormGroup>
            </Form>


        return(
                <div>
                    <p>Select the options you wish to use.</p>
                    <p>Units</p>
                      <ButtonGroup>
                        {units}
                      </ButtonGroup>
                    <p>{"\n"}</p>
                      <Collapse isOpen={this.state.collapse}>
                        {userDefinedOptions}
                      </Collapse>
                </div>
        )
    }
}

export default Options;
