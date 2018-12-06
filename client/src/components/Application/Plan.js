import React, {Component} from 'react'
import {Button, CardBody, Card} from 'reactstrap';


class Plan extends Component{
  constructor(props) {
    super(props);
    this.plan = this.plan.bind(this);

  }

  plan(){
    if(this.props.trip.places.length >= 1){
      this.props.updateBasedOnResponse(this.props.trip);
    }
  }

  render() {

    return(
      <Card>
        <CardBody>
          <Button block onClick={this.plan} size="lg" type="button" className='darkButtonAlt'>
            Plan
          </Button>
        </CardBody>
      </Card>
    )

  }
}

export default Plan;