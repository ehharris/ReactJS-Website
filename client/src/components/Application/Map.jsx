import React, {Component} from 'react'
import {Card, CardBody} from 'reactstrap'
import "./Itinerary.css";


export default class Map extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        let svg = "data:image/svg+xml;charset+utf-8,".concat(encodeURIComponent(this.props.trip.map));
        return (
            <div>
                <img className="mapborder" src={svg} alt="" />
            </div>
        )
    }
}