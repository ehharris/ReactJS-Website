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
        this.state.trip = this.props.data;
    }

    //builds distances uses by itinerary
    buildDistance(){
        this.state.trip.distances.map((key, index, array) => {
            index>0?this.state.trip.distances[index]+=this.state.trip.distances[index -1]:this.state.trip.distances[0]
        });
        this.state.set = 'true';

            //this.state.data.push(this.props.data.distances[i]);


    }
    render() {
        //this.state.data.place.names = ["test"];
        if (typeof this.state.set === 'undefined') {
            this.buildDistance();
       }
       // this.buildDistance();
        //console.log(this.props.data.distances);
        //console.log(this.state.data);

        //console.log (this.state.data);
       // this.state.places.map(place => this.state.data.push({
            //names : place.name,
         //   distance : this.state.trip.distances

        //}));


        //this.state.data.trip.distance = [0];
       // this.state.trip.distances.map(distance => this.state.data.trip.distance.push(distance));
       // this.setState({places : this.props.data});
      // console.log(this.state.data);
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
                        {
                            this.state.trip.places.map((key,index,array)=> (
                                <tr>
                                    <td key='#' > {index + 1} </td>
                                    <td key={key.name}>
                                        {key.name}
                                    </td>
                                    <td key={this.state.trip.distances[index]}>
                                        {this.state.trip.distances[index]}
                                    </td>
                                </tr>
                            ))
                        }
                        </tbody>
                    </table>
                </CardBody>
            </Card>
        )
    }

}
export default ItineraryTable;