import React, {Component} from 'react'
import { Card, CardHeader, CardBody } from 'reactstrap'
import { ButtonGroup, Button } from 'reactstrap'

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
class Port extends Component{
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.state = {
            port: '',
            data: ''
        };
    }

    handleChange(event) {
        this.setState({port: event.target.value});
    }

    handleSubmit(event) {
        event.preventDefault();
        // const data = new FormData(event.target);
        // below used for testing
        console.log(this.state.port);

        fetch('http://' + this.state.port, {
            method: 'GET'
        })
            .then(response => response.text())
            .then(resData => this.setState({data: resData}));
            //.then(resData => console.log(resData));
            //.then(response => this.setState({data: response.text()}));

            //.then(response => this.setState({data: event.target.value}));
        console.log(this.state.data);
    }

    //TODO add test case (.then function)

    render() {
        console.log("Data Received:" + this.state.data);
        return(
            <Card>
                {/* Adds a text area section for inputting server:port */}
                <form onSubmit={this.handleSubmit}>
                    <textarea value={this.state.port} onChange={this.handleChange} />
                    <Button type="submit">Submit</Button>
                </form>
                {this.state.data}
            </Card>
        )
    }
}

export default Port;