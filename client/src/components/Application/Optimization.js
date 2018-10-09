import React, {Component} from 'react'
import { Card, CardBody } from 'reactstrap'
import { ButtonGroup, Button } from 'reactstrap'

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

        return(
            <Card>
                <CardBody>
                    <p>Select the length of your trip.</p>
                    <ButtonGroup>
                        <Button
                            className='btn-outline-dark unit-button'
                        >None</Button>
                        <Button
                            className='btn-outline-dark unit-button'
                        >Short Trip</Button>
                        <Button
                            className='btn-outline-dark unit-button'
                        >Shorter Trip</Button>
                        <Button
                            className='btn-outline-dark unit-button'
                        >Shortest Trip</Button>
                    </ButtonGroup>
                </CardBody>
            </Card>
        )
    }
}

export default Optimization;
