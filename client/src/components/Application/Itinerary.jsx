import React,{Component} from 'react'


class ItineraryTable extends Component {
    constructor(props) {
        super(props);
        this.state = {
            trip : this.props.data,
            places : this.props.data.places,
            data : [{
                    names: [],
                    distance: []
            }
            ]
        };
    }

    render() {
        //this.state.data.place.names = ["test"];
        //this.state.places.map(place =>
          //  this.state.data.push({
            //place : {
              //  names: place.name,
             //}
            //}));
        //this.state.data.trip.distance = [0];
       // this.state.trip.distances.map(distance => this.state.data.trip.distance.push(distance));
       // this.setState({places : this.props.data});
       // console.log(this.state.data);
        return (
            <table>
                <thead>
                <tr>
                    <th>Places</th>
                    <th>Distance From Start</th>
                </tr>
                </thead>
                <tbody>
                {
                    this.state.places.map(row => (
                        <tr>
                            <td>
                                {row.name}
                            </td>
                        </tr>
                    ))
                }
                </tbody>
            </table>
        )
    }

}
export default ItineraryTable;