import React, {Component} from 'react'
import {Card, CardBody, Button, InputGroup, DropdownMenu, DropdownToggle,
  DropdownItem, Form, Input, Table, ModalHeader, ModalBody, Modal
} from 'reactstrap';
import { request } from '../../api/api';

/* Allows the user to search from database.
 * Allows the user to search from database used by the application via inputs.
 */
class Search extends Component{
    constructor(props) {
        super(props);
        this.updateSearch = this.updateSearch.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.updatePlace = this.updatePlace.bind(this);
        this.toggle = this.toggle.bind(this);
        this.state = {
            search: '',
            results: [],
            place: {
                id: '',
                name: '',
                latitude: 0,
                longitude: 0,
            },
            filters: [],
            dropDownOpen: false
        };
    }

    toggle() {
        this.setState({dropDownOpen: !this.state.dropDownOpen});
    }

    updateSearch(event) {
        this.setState({search: event.target.value});
    }

    handleSubmit(event) {
        event.preventDefault();
        request({ "version": 4, "type": "search", "match": this.state.search}, "search", this.props.port, this.props.server).then((resData) => this.setState({results: resData}));
    }

    buildNameResults() {
        let nameData = [];
        if(this.state.results.places) {
            this.state.results.places.map((key, index) => {
                if (index >= 0) {
                    nameData[index] = this.state.results.places[index].name;
                }
            });
        }
        return nameData;
    }

    buildFilterNames() {
        let filterNames = [];
        if(this.props.config.filters) {
            this.props.config.filters.map((key, index) => {
                filterNames[index] = this.props.config.filters[index].name;
            });
        }
        return filterNames;
    }

    //add method that sends to parent
    updatePlace(event) {
        let index = event.target.value;
        let place = this.state.results.places[index];

        let places = this.props.places;
        places.push(place);
        this.props.updatePlaces(places);
    }

    renderCard(){
      let filterNames = this.buildFilterNames();
      return(
        <Card>
          <CardBody>
            <p> Search trip destinations: </p>
            <Form>
              <InputGroup>
                <Input name="Search Entry" type="text" value={this.state.search} onChange={this.updateSearch} />
                <Button value="submit" className="btn-outline-dark unit-button" onClick={this.handleSubmit}>Submit</Button>
              </InputGroup>
            </Form>
            {this.renderTable()}
          </CardBody>
        </Card>
      )
    }

    renderTable(){
      let nameData = this.buildNameResults();
      if(this.state.results.places){
        return(
          <Table>
            <thead>
            <tr>
              <th>Name</th>
              <th>Add</th>
            </tr>
            </thead>
            <tbody>
            {this.state.results.places.map((key,index)=> (
              <tr>
                <td key={nameData[index]}>{nameData[index]}</td>
                <td><Button value={index} type="button" className="btn-outline-dark unit-button" onClick={this.updatePlace}>+</Button></td>
              </tr>
            ))}
            </tbody>
          </Table>
        )
      }
    }

    renderModHead(){
      return(
        <ModalHeader toggle={() => {this.props.toggleMod('2')}}>Search worldwide for a new place!</ModalHeader>
      )
    }

    render() {
        return(
          <Modal toggle={() => {this.props.toggleMod('2')}} isOpen={this.props.modal2} contentClassName={"modalT"}>
            {this.renderModHead()}
            <ModalBody>
              {this.renderCard()}
            </ModalBody>
          </Modal>
        )
    }
}

export default Search;