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
        this.changeService = this.changeService.bind(this);
        this.changeName = this.changeName.bind(this);
        this.changeRequestBodyText = this.changeRequestBodyText.bind(this);
        this.state = {
            server: '',
            port: '',
            service: '',
            name: '',
            data: '',
            requestBody: ''
        };
    }

    changeServer(event) {
        this.setState({server: event.target.value});
    }

    changePort(event) {
        this.setState({port: event.target.value});
    }

    changeService(event) {
        this.setState({service: event.target.value});
    }

    changeName(event) {
        this.setState({name: event.target.value});
    }

    changeRequestBodyText(event) {
        this.setState({requestBody: event.target.value});
    }


    handleSubmit(event) {
        event.preventDefault();
        if(this.state.server !== "" && this.state.port !== "") {
            if (this.state.service === "/about" || this.state.service === "/team") {
                fetch('http://' + this.state.server + ":" + this.state.port + this.state.service, {
                    method: 'GET'
                })
                    .then(response => response.text())
                    .then(resData => this.setState({data: resData}));
            }

            if (this.state.service === "/echo" || this.state.service === "/config") {
                fetch('http://' + this.state.server + ":" + this.state.port + this.state.service, {
                    method: 'GET'
                })
                    .then(response => response.json())
                    .then(resData => this.setState({data: JSON.stringify(resData)}));
            }

            if (this.state.service === "/hello") {
                if (this.state.name !== "") {
                    fetch('http://' + this.state.server + ":" + this.state.port + this.state.service + "/" + this.state.name, {
                        method: 'GET'
                    })
                        .then(response => response.text())
                        .then(resData => this.setState({data: resData}));
                }
                else {
                    this.setState({data: "Please enter a name for the /hello microservice"});
                }
            }

            if (this.state.service === "/plan" || this.state.service === "/distance") {
                if (this.state.requestBody !== "") {
                    console.log(this.state.requestBody);
                    fetch('http://' + this.state.server + ":" + this.state.port + this.state.service, {
                        method: 'POST',
                        body: this.state.requestBody
                    })
                        .then(response => response.json())
                        .then(resData => this.setState({data: JSON.stringify(resData)}));
                }
                else {
                    this.setState({data: "Please input JSON when using /plan or /distance microservice"});
                }
            }
        }
        else {
            this.setState({data: "Please input server and/or port"});
        }
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
                    <div>Enter Name for /hello: </div>
                    <input name="Enter Name" type="text" value={this.state.name} onChange={this.changeName} />
                    <br/>
                    <div>Enter Text for /plan or /distance: </div>
                    <input name="Enter Text" type="text" value={this.state.requestBody} onChange={this.changeRequestBodyText} />
                    <br/>
                    <div>Select Microservice: </div>
                    <button value="/about" className="btn-outline-dark unit-button" onClick={this.changeService}>/about</button>
                    <button value="/echo" className="btn-outline-dark unit-button" onClick={this.changeService}>/echo</button>
                    <button value="/team" className="btn-outline-dark unit-button" onClick={this.changeService}>/team</button>
                    <button value="/config" className="btn-outline-dark unit-button" onClick={this.changeService}>/config</button>
                    <button value="/hello" className="btn-outline-dark unit-button" onClick={this.changeService}>/hello</button>
                    <button value="/plan" className="btn-outline-dark unit-button" onClick={this.changeService}>/plan</button>
                    <button value="/distance" className="btn-outline-dark unit-button" onClick={this.changeService}>/distance</button>
                    <br/>

                </form>
                    <div>Data Received: </div><br />
                    {this.state.data}
                </CardBody>
            </Card>
        )
    }
}

export default Port;