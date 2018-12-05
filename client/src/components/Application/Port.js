import React, {Component} from 'react'
import {Card, CardBody, Input, InputGroup, Form, Label} from 'reactstrap'
import { ButtonGroup, Button } from 'reactstrap'

/* Allows the user to change the parameters for server
 * and port configuration and desired microservice.
 * Allows the user to set the options used by the application via a set of inputs and buttons.
 */
class Port extends Component {
    constructor(props) {
        super(props);
        this.state = {
            server: location.hostname,
            port: location.port
        };
        this.update = this.update.bind(this);
        this.changeServer = this.changeServer.bind(this);
        this.changePort = this.changePort.bind(this);
    }



    changeServer(event) {
        this.setState({server: event.target.value});
    }

    changePort(event) {
        this.setState({port: event.target.value});
    }

    update(){
        this.props.updateServer(this.state.server, this.state.port);
    }

    render() {
        return(
            <Card>
                <CardBody>
                    <Form inline xs="6" sm="6" md="6" lg="6">
                        <Input onChange={this.changeServer} name="Change Server" type="text" value={this.state.server} className="server"/>
                        <Label className="labelport">:</Label>
                        <Input onChange={this.changePort} name="Change Port" type="text" value={this.state.port} className="port"/>
                    </Form>
                    <Button onClick={this.update} type="button" className='btn-outline-dark unit-button'>
                        Change Server and Port
                    </Button>
                </CardBody>
            </Card>
        )
    }
}

export default Port;