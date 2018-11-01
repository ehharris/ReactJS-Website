import React, {Component} from 'react'
import {Card, CardBody} from 'reactstrap'


export default class Map extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        //console.log(this.props.trip.map);
        let svg = "data:image/svg+xml;charset+utf-8,".concat(encodeURIComponent(this.props.trip.map));
        return (
            <div>
                <Card>
                    <CardBody>
                        <p className="lead">"A Map of the Whole Wide Colorado"</p>
                        <img src={svg} alt="" />
                    </CardBody>
                </Card>
            </div>
        )
    }
}