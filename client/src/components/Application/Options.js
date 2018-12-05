
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
        this.renderUnits = this.renderUnits.bind(this);
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

    renderUnits(){
      return(
        this.props.config.units.map((unit) =>
          <Button
            key={'distance_button_' + unit}
            className='btn-outline-dark unit-button'
            active={this.props.options.units === unit}
            value={unit}
            onClick={(event) => {this.props.updateOptions('units', event.target.value); this.toggle(event.target.value); this.props.setCookie("units", event.target.value, 365)}}
          >
            {unit.charAt(0).toUpperCase() + unit.slice(1)}
          </Button>
        )
    );
    }

    renderDefined(){
      let arr = ['unitName','unitRadius'];
      return(
        <Form inline>
          {arr.map((value) =>
            <FormGroup key={value+1}>
              <Input key={value+2} name={value} placeholder={value.substr(4,value.length)}
                     onChange={(event) => {this.props.updateOptions(value, event.target.value); this.props.setCookie(value, event.target.value, 365)}}
              />
            </FormGroup>
          )}
        </Form>
      )
    }

    render() {
        return(
            <div>
                <p>Select the options you wish to use.</p>
                <p>Units</p>
                {this.renderUnits()}
                <p>{"\n"}</p>
                  <Collapse isOpen={this.state.collapse}>
                    {this.renderDefined()}
                  </Collapse>
            </div>
        )
    }
}

export default Options;
