import React, {Component} from 'react'
import {
    Container, Col, Form,
    FormGroup, Label, Input,
    Button, CardBody, ButtonGroup, Card,
} from 'reactstrap';2

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
class File extends Component{
    constructor(props) {
        super(props);
    }

    render() {


        return(
            <Card>
                <CardBody>
                    <Form>
                        <FormGroup>
                            <Label for="exampleFile">File</Label>
                            <Input type="file" name="file" id="exampleFile" />
                        </FormGroup>
                        <ButtonGroup>
                            <Button
                                key={'distance_button_'}
                                className='btn-outline-dark unit-button'
                                onClick={(event) => this.props.updateFile(file)}
                            >
                                Submit
                            </Button>
                        </ButtonGroup>
                    </Form>
                </CardBody>
            </Card>
        )
    }
}

export default File;
