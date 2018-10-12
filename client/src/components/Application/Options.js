
import React, {Component} from 'react'
import { Card, CardHeader, CardBody } from 'reactstrap'
import { ButtonGroup, Button } from 'reactstrap'

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
class Options extends Component{
    constructor(props) {
        super(props);
    }

    render() {
        const buttons0 = this.props.config.units.map((unit) =>
            <Button
                key={'distance_button_' + unit}
                className='btn-outline-dark unit-button'
                active={this.props.options.units === unit}
                value={unit}
                onClick={(event) => this.props.updateOptions('units', event.target.value)}
            >
                {unit.charAt(0).toUpperCase() + unit.slice(1)}
            </Button>
        );

        const buttons1 = this.props.config.optimization.map((label) =>
            <Button
                key={'optimization_button_' + label.label}
                className='btn-outline-dark unit-button'
                active={this.props.options.optimization === label.label}
                value={label.label}
                onClick={(event) => this.props.updateOptions('optimization', event.target.value)}
            >
                {label.label.charAt(0).toUpperCase() + label.label.slice(1)}
            </Button>
        );

        return(
            <Card>
                <CardBody>
                    <p>Select the options you wish to use.</p>
                    <p>Units</p>
                      <ButtonGroup>
                        {buttons0}
                      </ButtonGroup>
                    <p>{"\n"}</p>
                    <p>Optimization</p>
                      <ButtonGroup>
                        {buttons1}
                      </ButtonGroup>
                </CardBody>
            </Card>
        )
    }
}

export default Options;
