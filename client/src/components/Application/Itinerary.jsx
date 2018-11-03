import React,{Component} from 'react'
import {Card, CardBody, Form, FormGroup, Button, Input, Label, Table} from "reactstrap";


class ItineraryTable extends Component {
    constructor(props) {
        super(props);
        this.setOptionLatitude = this.setOptionLatitude.bind(this);
        this.setOptionLongitude = this.setOptionLongitude.bind(this);
        this.resetOptions = this.resetOptions.bind(this);
        this.reverseTrip = this.reverseTrip.bind(this);
        this.changeStart = this.changeStart.bind(this);
        this.removePlace = this.removePlace.bind(this);
        this.state = {
            optionLatitude: false,
            optionLongitude: false
        };
        //this.state.trip = this.props.data;
    }

    //builds distances uses by itinerary
    buildDistance() {
        let data = [0];

        this.props.trip.distances.map((key, index) => {
            if (index > 0) {
                data[index] = this.props.trip.distances[index - 1] + data[index-1];
            }
        });
        return data;
        //this.state.set = 'true';

        //this.state.data.push(this.props.data.distances[i]);

    }

    setOptionLatitude(event) {
        this.setState({optionLatitude: !this.state.optionLatitude});
    }

    setOptionLongitude(event) {
        this.setState({optionLongitude: !this.state.optionLongitude});
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

    changeStart(index) {
        let data = this.props.trip;
        let length = data.places.length;
        for(let i = 0; i < index; i++){
            let temp = data.places[0];
            for (let j = 0; j < length - 1; j++){
                data.places[j] = data.places[j+1];
            }
            data.places[length-1] = temp;
        }
        this.props.updateBasedOnResponse(data);
    }

    removePlace(index){
        if (this.props.trip.places.length > 1) {
            let data = this.props.trip;
            data.places.splice(index, 1);
            this.props.updateBasedOnResponse(data);
        }
    }

    render() {
        let data = this.buildDistance();

        const latitudeStyle = this.state.optionLatitude ? {} : {display: 'none'};
        const longitudeStyle = this.state.optionLongitude ? {} : {display: 'none'};

        return (
            <Card>
                <CardBody>
                    <p className="lead">{this.props.trip.title}</p>
                    <Table striped>
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Place</th>
                            <th>Total Distance</th>
                            <th style={latitudeStyle}>Latitude</th>
                            <th style={longitudeStyle}>Longitude</th>
                            <th>Change Start City</th>
                            <th>Remove City</th>
                        </tr>
                        </thead>
                        <tbody>
                        {this.props.trip.places.map((key,index)=> (
                            <tr key={index}>
                                <td key={'#' + index}>
                                    {index + 1} </td>
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
                                <td key={index+1}>
                                    <Button value="start" onClick={() => this.changeStart(index)}>Start Here</Button>
                                </td>
                                <td key = {"remove" + index}>
                                    <Button value="bye" onClick={() => this.removePlace(index)}>&times;</Button>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </Table>

                    <div>Select additional options for itinerary: </div>
                    <Form>
                        <FormGroup check inline>
                            <Label check>
                                <Input type="checkbox" onClick={this.setOptionLatitude}/> Latitude
                            </Label>
                        </FormGroup>
                        <FormGroup check inline>
                            <Label check>
                                <Input type="checkbox" onClick={this.setOptionLongitude}/> Longitude
                            </Label>
                        </FormGroup>
                    </Form>
                    <Button value="reverse" className="btn-outline-dark unit-button" onClick={this.reverseTrip}>Reverse Trip</Button>
                </CardBody>
            </Card>
        )
    }
}
export default ItineraryTable;