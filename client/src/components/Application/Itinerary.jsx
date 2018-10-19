import React,{Component} from 'react'
import {Card, CardBody} from "reactstrap";


class ItineraryTable extends Component {
    constructor(props) {
        super(props);
        this.setOptionLatitude = this.setOptionLatitude.bind(this);
        this.setOptionLongitude = this.setOptionLongitude.bind(this);
        this.state = {
            trip : [],
            places : [],
            data : [],
            optionLatitude: 'false',
            optionLongitude: 'false'
        };
        //this.state.trip = this.props.data;
    }

    //builds distances uses by itinerary
    buildDistance() {

        this.state.trip = this.props.data;
        if (this.state.trip.distances !== 'undefined') {

        this.state.trip.distances.map((key, index, array) => {
            index > 0 ? this.state.trip.distances[index] += this.state.trip.distances[index - 1] : this.state.trip.distances[0]
        });
        }
        //this.state.set = 'true';

            //this.state.data.push(this.props.data.distances[i]);


    }

    setOptionLatitude(event) {
        this.setState({optionLatitude: 'true'});
    }

    setOptionLongitude(event) {
        this.setState({optionLongitude: 'true'});
    }

    resetOptions(event) {
        this.setState({optionLatitude: 'false'});
        this.setState({optionLongitude: 'false'});
    }

    render() {
        this.buildDistance();

        let latitudeHeader;
        let latitudeData;
        if(this.state.optionLatitude === 'true') {
            latitudeHeader = <th>Latitude</th>;
            if(this.state.places.length > 0) {
                latitudeData =
                    <td key={this.state.places[index].latitude}>
                        {this.state.places[index].latitude}
                    </td>;
            }
        }

        let longitudeHeader;
        let longitudeData;
        if(this.state.optionLongitude === 'true') {
            longitudeHeader = <th>Longitude</th>;
            if(this.state.places.length > 0) {
                longitudeData =
                    <td key={this.state.places[index].longitude}>
                        {this.state.places[index].longitude}
                    </td>;
            }
        }

        return (
            <Card>
                <CardBody>
                    <p className="lead">"Your Itinerary"</p>
                    <table>
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Places</th>
                            <th>Distance From Start</th>
                            {latitudeHeader}
                            {longitudeHeader}
                        </tr>
                        </thead>
                        <tbody>
                        {this.state.trip.places.map((key,index,array)=> (
                                <tr>
                                    <td key='#' > {index + 1} </td>
                                    <td key={key.name}>
                                        {key.name}
                                    </td>
                                    <td key={this.state.trip.distances[index]}>
                                        {this.state.trip.distances[index]}
                                    </td>
                                    {latitudeData}
                                    {longitudeData}
                                </tr>
                            ))}
                        </tbody>
                    </table>

                    <div>Select additional options for itinerary: </div>
                    <br />
                    <button value="latitude" className="btn-outline-dark unit-button" onClick={this.setOptionLatitude}>Show Latitude</button>
                    <button value="longitude" className="btn-outline-dark unit-button" onClick={this.setOptionLongitude}>Show Longitude</button>
                    {/*<button value="clear" className="btn-outline-dark unit-button" onClick={this.resetOptions}>Reset Options</button>*/}
                </CardBody>
            </Card>
        )
    }
}
export default ItineraryTable;