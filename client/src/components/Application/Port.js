import React, {Component} from 'react'
import { Card, CardHeader, CardBody } from 'reactstrap'
import { ButtonGroup, Button } from 'reactstrap'

/* Allows the user to change the parameters for server
 * and port configuration and desired microservice.
 * Allows the user to set the options used by the application via a set of inputs and buttons.
 */
class Port extends Component{
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.changeServer = this.changeServer.bind(this);
        this.changePort = this.changePort.bind(this);
        this.state = {
            server: '',
            port: ''
        };
    }



    changeServer(event) {
        this.setState({server: event.target.value});
    }

    changePort(event) {
        this.setState({port: event.target.value});
    }

    handleSubmit(event) {
        event.preventDefault();

        this.props.updateServer(this.state.server);
        this.props.updatePort(this.state.port);
        console.log(this.props.server);

            if(this.props.server !== "" && this.state.port !== "") {
                    fetch('http://' + this.props.server + ":" + this.props.port + "/about", {
                        method: 'GET'
                    })
                        .then(response => response.text())
                        .then(resData => this.setState({data: resData}));
            }


    }


    changeService(event) {
        this.setState({service: event.target.value});
    }

    render() {
        return(
            <Card>
                <CardBody>
                <form onSubmit={this.handleSubmit}>
                    <div>Change Server: </div>
                    <input name="Change Server" type="text" value={this.state.server} onChange={this.changeServer} />
                    <br />
                    <div>Change Port: </div>
                    <input name="Change Port" type="text" value={this.state.port} onChange={this.changePort} />
                    <br />
                    <button value="submit">Change Server and Port</button>

                </form>
                    <div>Data Received: </div><br />
                    {this.state.data}
                </CardBody>
            </Card>
        )
    }
}

export default Port;