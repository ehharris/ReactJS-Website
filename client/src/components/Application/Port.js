import React, {Component} from 'react'
import { Card, CardHeader, CardBody, Form, Input } from 'reactstrap'
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
            port: '31410'
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
                    <ButtonGroup>
                        <Input onChange={this.changeServer} name="Change Server" type="text" value={this.state.server}/>
                        <Input onChange={this.changePort} name="Change Port" type="text" value={this.state.port}/>
                        <Button onClick={this.update} type="button" className='btn-outline-dark unit-button'>
                            Change Server
                        </Button>
                    </ButtonGroup>
                </CardBody>
            </Card>
        )
    }
}

export default Port;