import React, {Component} from 'react'
import {Card, CardHeader, CardBody} from 'reactstrap'

import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
//import '../css/Table.css';
//import '../../node_modules/react-bootstrap-table/css/react-bootstrap-table.css'


// ...
function CustomRow(data){
    return (
        <tr>
            <td>{data}</td>
        </tr>
    );
}

export default class Itinerary extends Component {
    constructor(props) {
        super(props);
        this.state = {
            names: [],
            dist: []
        }
    }

    this.props.data.distances.forEach(function(distances) {
        this.state.dist.push(distances);
    });


    this.props.data.places.forEach(function(places) {
        this.state.names.push(places.name);
    });

    render() {

        var rows = [];
        let i = 0;
        this.state.dist.forEach(function(distances) {
            rows.push(CustomRow(this.state.names[i]))
            rows.push(CustomRow(this.state.dist[i]));
            i++;
        }.bind(this));
        console.log(this.state.dist);
        return (
            <div>
                <Card>
                    <CardBody>
                        <p className="lead">"Itineary"</p>
                        <table>
                            <thead>
                            <tr>
                                <th>Name of place</th>
                                <th>Distance Between</th>
                            </tr>
                            </thead>
                            {rows}
                        </table>
                    </CardBody>
                </Card>
            </div>
        );
    }
}

