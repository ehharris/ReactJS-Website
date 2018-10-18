import React,{Component} from 'react'
import {Card, CardBody} from "reactstrap";


class ItineraryTable extends Component {
    constructor(props) {
        super(props);
        this.state = {
            trip : [],
            places : [],
            data : [],
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
    render() {
        this.buildDistance();
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
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </CardBody>
            </Card>
        )
    }
}
export default ItineraryTable;