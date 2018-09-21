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
    const buttons = this.props.config.units.map((unit) =>
      <Button
        key={'distance_button_' + unit}
        className='btn-outline-dark unit-button'
        active={this.props.options.unit === unit}
        value={unit}
        onClick={(event) => this.props.updateOptions('distance', event.target.value)}
      >
        {unit.charAt(0).toUpperCase() + unit.slice(1)}
      </Button>
    );

    /*
     * Loads the potential button values for server:port config
     */
    // const server_config_buttons = this.props.config.ports.map((port) =>
    //   <Button
    //     key={'server_port_button' + port}
    //     className='btn-outline-dark unit-button'
    //     active={this.props.options.port === port}
    //     value={port}
    //     onClick={(event) => this.props.updateOptions('port', event.target.value)}
    //     onChange={this.handleChange}
    //     >
    //       {port.charAt(0).toUpperCase() + port.slice(1)}
    //   </Button>
    // );

    return(
      <Card>
        {/*<CardBody>*/}
          {/*<p>Select the options you wish to use.</p>*/}
          {/*<ButtonGroup>*/}
            {/*{buttons}*/}
          {/*</ButtonGroup>*/}
        {/*</CardBody>*/}

        {/* Adds a text area section for inputting server:port */}
        <form onSubmit={this.handleSubmit}>
            <textarea value={this.state.port} onChange={this.handleChange} />
            <Button type="submit">Submit</Button>
        </form>
      </Card>
    )
  }
}

export default Options;