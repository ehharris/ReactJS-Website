import React, {Component} from 'react'
import { Card, CardHeader, CardBody, Button, Dropdown, DropdownMenu, DropdownToggle, DropdownItem, Form, Input, Table } from 'reactstrap';
import { request } from '../../api/api';
import { get_config } from '../../api/api';

/* Allows the user to search from database.
 * Allows the user to search from database used by the application via inputs.
 */
class Search extends Component{
    constructor(props) {
        super(props);
        this.updateSearch = this.updateSearch.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.updatePlace = this.updatePlace.bind(this);
        //this.buildFilters = this.buildFilters.bind(this);
        // this.sendToParent = this.sendToParent.bind(this);
        this.state = {
            search: '',
            results: [],
            place: {
                id: '',
                name: '',
                latitude: 0,
                longitude: 0,
            },
            filters: []
        };
    }

    updateSearch(event) {
        this.setState({search: event.target.value});
    }

    handleSubmit(event) {
        event.preventDefault();
        request({ "version": 4, "type": "search", "match": this.state.search  }, "search", this.props.port, this.props.server).then((resData) => this.setState({results: resData}));
    }

    // buildIdResults() {
    //     let idData = [];
    //     if(this.state.results.places) {
    //         this.state.results.places.map((key, index, array) => {
    //             if(index > 0) {
    //                 idData[index] = this.state.results.places[index].id;
    //             }
    //         });
    //     }
    //     return idData;
    // }

    buildNameResults() {
        let nameData = [];
        if(this.state.results.places) {
            this.state.results.places.map((key, index, array) => {
                if (index > 0) {
                    nameData[index] = this.state.results.places[index].name;
                }
            });
        }
        return nameData;
    }
    //
    // buildLatitudeResults() {
    //     let latitudeData = [];
    //     if(this.state.results.places) {
    //         this.state.results.places.map((key, index, array) => {
    //             if(index > 0) {
    //                 latitudeData[index] = this.state.results.places[index].latitude;
    //             }
    //         });
    //     }
    //     return latitudeData;
    // }
    //
    // buildLongitudeResults() {
    //     let longitudeData = [];
    //     if(this.state.results.places) {
    //         this.state.results.places.map((key, index, array) => {
    //             if(index > 0) {
    //                 longitudeData[index] = this.state.results.places[index].longitude;
    //             }
    //         });
    //     }
    //     return longitudeData;
    // }

    //add method that sends to props

    updatePlace(event) {
        let index = event.target.value;
        let place = this.state.results.places[index];
        let trip = this.props.trip;

        if(trip.places){
            trip.places = [place];
            console.log("If");
            console.log(trip.places);
        }
        else {
            trip.places.push(place);
            console.log("else");
            console.log(trip.places);
        }

        this.props.updateBasedOnResponse(trip);

        // this.setState({place: {id: this.state.results.places[index].id,
        //                         name: this.state.results.places[index].name,
        //                         latitude: this.state.results.places[index].latitude,
        //                         longitude: this.state.results.places[index].longitude}});
        // this.sendToParent();
    }

    render() {
        //let idData = this.buildIdResults();
        let nameData = this.buildNameResults();
        //let latitudeData = this.buildLatitudeResults();
        //let longitudeData = this.buildLongitudeResults();
        //this.buildFilters();
        //console.log(this.state.filters);

        let resultsNotEmpty = false;
        if(this.state.results.places) {
            resultsNotEmpty = true;
        }

        return(
            <Card>
                <CardBody>
                    <Form onSubmit={this.handleSubmit}>
                        <div> Search trip destinations: </div>
                        <Input name="Search Entry" type="text" value={this.state.search} onChange={this.updateSearch} />

                        {/*<div>Choose filters: </div>*/}
                        {/*<Dropdown isOpen={this.state.dropdownOpen} toggle={this.toggle}>*/}
                            {/*<DropdownToggle caret>*/}
                                {/*Continents*/}
                            {/*</DropdownToggle>*/}
                            {/*<DropdownMenu>*/}
                                {/*<DropdownItem>Item</DropdownItem>*/}
                            {/*</DropdownMenu>*/}
                        {/*</Dropdown>*/}


                        <Button value="submit" className="btn-outline-dark unit-button" onClick={this.handleSubmit}>Submit</Button>
                    </Form>

                    <div>
                        {resultsNotEmpty ? (
                            <Table>
                                <thead>
                                <tr>
                                    {/*<th>Id</th>*/}
                                    <th>Name</th>
                                    {/*<th>Latitude</th>*/}
                                    {/*<th>Longitude</th>*/}
                                    <th>Add</th>
                                </tr>
                                </thead>
                                <tbody>
                                {this.state.results.places.map((key,index,array)=> (
                                    <tr>
                                        {/*<td key={idData[index]}>*/}
                                            {/*{idData[index]}*/}
                                        {/*</td>*/}
                                        <td key={nameData[index]}>
                                            {nameData[index]}
                                        </td>
                                        {/*<td key={latitudeData[index]}>*/}
                                            {/*{latitudeData[index]}*/}
                                        {/*</td>*/}
                                        {/*<td key={longitudeData[index]}>*/}
                                            {/*{longitudeData[index]}*/}
                                        {/*</td>*/}
                                        <td>
                                            <Button value={index} type="button" className="btn-outline-dark unit-button" onClick={this.updatePlace}>+</Button>
                                        </td>
                                    </tr>
                                ))}
                                </tbody>
                            </Table>
                        ) : (
                            <div></div>
                        )}

                    </div>
                </CardBody>
            </Card>
        )
    }
}

export default Search;