import React,{Component} from 'react'
import {Card, CardBody} from "reactstrap";


class ItineraryTable extends Component {
    constructor(props) {
        super(props);
        this.setOptionLatitude = this.setOptionLatitude.bind(this);
        this.setOptionLongitude = this.setOptionLongitude.bind(this);
        this.resetOptions = this.resetOptions.bind(this);
        this.reverseTrip = this.reverseTrip.bind(this);
        this.state = {
            trip : {},
            places : [],
            data : [],
            optionLatitude: false,
            optionLongitude: false
        };
        //this.state.trip = this.props.data;
    }

    //builds distances uses by itinerary
    buildDistance() {
        let data = [0];

        this.props.trip.distances.map((key, index, array) => {
            if (index > 0) {
                data[index] = this.props.trip.distances[index - 1] + data[index-1];
            }
        });
        return data;
        //this.state.set = 'true';

        //this.state.data.push(this.props.data.distances[i]);

    }

    setOptionLatitude(event) {
        this.setState({optionLatitude: true});
    }

    setOptionLongitude(event) {
        this.setState({optionLongitude: true});
    }

    resetOptions(event) {
        this.setState({optionLongitude: false});
        this.setState({optionLatitude: false});
    }

    reverseTrip(event) {
        let data = this.props.trip;
        data.places = data.places.reverse();
        this.props.updateBasedOnResponse(data);
    }

    render() {
        let data = this.buildDistance();

        const latitudeStyle = this.state.optionLatitude ? {} : {display: 'none'};
        const longitudeStyle = this.state.optionLongitude ? {} : {display: 'none'};

        return (
            <Card>
                <CardBody>
                    <p className="lead">{this.props.trip.title}</p>
                    <table>
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Places</th>
                            <th>Distance From Start</th>
                            <th style={latitudeStyle}>Latitude</th>
                            <th style={longitudeStyle}>Longitude</th>
                        </tr>
                        </thead>
                        <tbody>
                        {this.props.trip.places.map((key,index,array)=> (
                            <tr key={index}>
                                <td key='#' > {index + 1} </td>
                                <td key={key.name}>
                                    {key.name}
                                </td>
                                <td key={data[index]}>
                                    {data[index]}
                                </td>
                                <td key={key.latitude} style={latitudeStyle}>
                                    {key.latitude}
                                </td>
                                <td key={key.longitude} style={longitudeStyle}>
                                    {key.longitude}
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>

                    <div>Select additional options for itinerary: </div>
                    <br />
                    <button value="latitude" className="btn-outline-dark unit-button" onClick={this.setOptionLatitude}>Show Latitude</button>
                    <button value="longitude" className="btn-outline-dark unit-button" onClick={this.setOptionLongitude}>Show Longitude</button>
                    <button value="clear" className="btn-outline-dark unit-button" onClick={this.resetOptions}>Reset Options</button>
                    <button value="reverse" className="btn-outline-dark unit-button" onClick={this.reverseTrip}>Reverse Trip</button>
                </CardBody>
            </Card>
        )
    }
}
export default ItineraryTable;