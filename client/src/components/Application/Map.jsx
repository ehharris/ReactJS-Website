import React, {Component} from 'react'
import {Card, CardBody} from 'reactstrap'
//import daMap from './Resources/CObackground.svg'
//import { get_port } from '../../api/api.js';

export default class Map extends Component {
    constructor(props) {
        super(props);
        this.state = {
            map : ''
        };
        this.state.trip = this.props.trip;
    }

    buildThatMap(){
        fetch('http://' + location.hostname + ":31410/map", {
            method: 'POST',
            body: JSON.stringify({
                places: this.state.trip.places,
            }),
            headers: {
                'Content-Type': 'applications/json',
                'Accept': 'applications/json'
            }
        })
            .then(response => response.json())
            .then(resData => this.setState({map: JSON.stringify(resData)}));
    }
    render() {
        //console.log(this.state.trip);
        this.buildThatMap();
        return (
            <div>
                <Card>
                    <CardBody>
                        <p className="lead">"A Map of the Whole Wide Colorado"</p>
                        <img src={require('../../../../Resources/CObackground.svg')}/>
                    </CardBody>
                </Card>
            </div>
        )
    }
}