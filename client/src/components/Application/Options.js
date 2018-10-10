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
      this.state={
          button1:{
              active:true
          },
          button2:{
              active:false
          },
          button3:{
              active:false
          }
      }
  }


    button1Active(){
        this.setState({
            button1:{
                active:true
            },
            button2:{
                active:false
            },
            button3:{
                active:false
            },
        });
    }

    button2Active(){
        this.setState({
            button1:{
                active:false
            },
            button2:{
                active:true
            },
            button3:{
                active:false
            },
        });
    }

    button3Active(){
        this.setState({
            button1:{
                active:false
            },
            button2:{
                active:false
            },
            button3:{
                active:true
            },
        });
    }

  render() {

    return(
      <Card>
        <CardBody>
          <p>Select the options you wish to use.</p>
          <ButtonGroup>
              <Button
                  key={'button1'}
                  className='btn-outline-dark unit-button'
                  active={this.state.button1.active}
                  onClick={(event) => {this.props.updateOptions('distance', 'miles');this.button1Active()}}
              >
                  Miles
              </Button>
              <Button
                  key={'button2'}
                  className='btn-outline-dark unit-button'
                  active={this.state.button2.active}
                  onClick={(event) => {this.props.updateOptions('distance', 'kilometers');this.button2Active()}}
              >
                  Kilometers
              </Button>
              <Button
                  key={'button3'}
                  className='btn-outline-dark unit-button'
                  active={this.state.button3.active}
                  onClick={(event) => {this.props.updateOptions('distance', 'nautical miles');this.button3Active()}}
              >
                  Nautical Miles
              </Button>
          </ButtonGroup>
        </CardBody>
      </Card>
    )
  }
}

export default Options;