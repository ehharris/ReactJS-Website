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
            name: 'stranger',
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
        // const data = new FormData(event.target);
        // below used for testing
        console.log("HandleSubmit Data: ");
        console.log(this.state.server);
        console.log(this.state.port);
        console.log(this.state.service);

        if (this.state.service === "/about" || this.state.service === "/team") {
            fetch('http://' + this.state.server + ":" + this.state.port + this.state.service, {
                method: 'GET'
            })
                .then(response => response.text())
                .then(resData => this.setState({data: resData}));
            //.then(resData => console.log(resData));
            //.then(response => this.setState({data: response.text()}));

            //.then(response => this.setState({data: event.target.value}));
            console.log("Data Received in Fetch:" + this.state.data);
            console.log("Success! You made it!")
        }

        if(this.state.service === "/echo" || this.state.service === "/config") {
            fetch('http://' + this.state.server + ":" + this.state.port + this.state.service, {
                method: 'GET'
            })
                .then(response => response.json())
                .then(resData => this.setState({data: JSON.stringify(resData)}));
        }

        if(this.state.service === "/hello") {
            fetch('http://' + this.state.server + ":" + this.state.port + this.state.service + "/" + this.state.name, {
                method: 'GET'
            })
                .then(response => response.text())
                .then(resData => this.setState({data: resData}));
        }

        if(this.state.service === "/plan" || this.state.service === "/distance") {
            fetch('http://' + this.state.server + ":" + this.state.port + this.state.service, {
                method: 'POST',
                body: this.state.requestBody
            })
                .then(response => response.json())
                .then(resData => this.setState({data: JSON.stringify(resData)}));
        }


        console.log("Data Received: " + this.state.data);
    }

    render() {

        return(
            <Card>
                <CardBody>
                <form onSubmit={this.handleSubmit}>
                    <div>Change Server: </div>
                    <input name="Change Server" type="text" value={this.state.server} onChange={this.changeServer} />
                    {this.state.server}
                    <br /><br />
                    <div>Change Port: </div>
                    <input name="Change Port" type="text" value={this.state.port} onChange={this.changePort} />
                    {this.state.port}
                    <br />
                    <div>Enter Name: </div>
                    <input name="Enter Name" type="text" value={this.state.name} onChange={this.changeName} />
                    <br/>
                    <div>Upload File or Enter Text: </div>
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
                    {this.state.service}
                    {/*<Button type="submit">Submit</Button>*/}
                </form>
                    {this.state.data}
                </CardBody>
            </Card>
        )
    }
}

export default Port;