import React, {Component} from 'react'
import {Card, CardHeader, CardBody, Alert} from 'reactstrap'

export default class Info extends Component {

  constructor(props) {
    super(props);
    this.onDismiss = this.onDismiss.bind(this);
    this.state = {
      visible: true,
    }
  }

  onDismiss() {
    this.setState({ visible: false });
  }

  render() {
    return (
      <div>
        <Alert color="info" isOpen={this.state.visible} toggle={this.onDismiss}>
          <p className="lead">"Want to travel far and wide?"</p>
          <ol >
            <li>
              Choose options for trip planning, information to display about locations,
              and how the trip map and itinerary should be saved.</li>
            <li>
              Choose your destinations by loading existing sets of destinations or
              find more in an extensive database of locations worldwide.</li>
            <li>
              Plan the trip with the options you selected.
              Review and revise the trip origin and order.
              Save the trip map and itinerary for future reference.</li>
          </ol>
        </Alert>
      </div>
    )
  }
}