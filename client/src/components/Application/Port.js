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
            data: null
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

        fetch(this.state.port, {
            method: 'POST',
            body: JSON.stringify(this.state),
            headers: {
                'content-type': 'application/json'
            },
            mode: "no-cors"
        })
            .then(response => response.json())
            .then(data => this.setState({data}));
    }

    //TODO add test case (.then function)

    render() {
        return(
            <Card>
                {/* Adds a text area section for inputting server:port */}
                <form onSubmit={this.handleSubmit}>
                    <textarea value={this.state.port} onChange={this.handleChange} />
                    <Button type="submit">Submit</Button>
                </form>
            </Card>
        )
    }
}

export default Port;