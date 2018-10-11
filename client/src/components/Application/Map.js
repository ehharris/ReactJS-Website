import React, {Component} from 'react'
import {Card, CardBody} from 'reactstrap'
//import daMap from './Resources/CObackground.svg'

export default class Map extends Component {
    render() {
        return (
            <div>
                <Card>
                    <CardBody>

                        <p className="lead">"A Map of the Whole Wide Colorado"</p>
                        <img src={require('./Resources/CObackground.svg')}/>

                    </CardBody>
                </Card>
            </div>
        )
    }
}