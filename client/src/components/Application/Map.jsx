import React, {Component} from 'react'
import {Button, ButtonToolbar} from 'reactstrap'
import "./Itinerary.css";


export default class Map extends Component {
    constructor(props) {
        super(props);
        this.save = this.save.bind(this);
    }

    save(svg) {
        var downloadLink = document.createElement("a");
        downloadLink.href = svg;
        downloadLink.download = this.props.trip.title + " Map.svg";
        document.body.appendChild(downloadLink);
        downloadLink.click();
        document.body.removeChild(downloadLink);
    }

    render() {
        let svg = "data:image/svg+xml;charset+utf-8,".concat(encodeURIComponent(this.props.trip.map));
        return (
            <div>
                <img className="mapborder" src={svg} alt="" />
                &nbsp;
                <ButtonToolbar>
                    <Button onClick={() => this.save(svg)} type="button" className='btn-outline-dark unit-button'>
                        Save Map
                    </Button>
                </ButtonToolbar>
            </div>

        )
    }
}