import React, {Component} from 'react'
import { Card, CardHeader, CardBody } from 'reactstrap'

/* Allows the user to search from database.
 * Allows the user to search from database used by the application via inputs.
 */
class Search extends Component{
    constructor(props) {
        super(props);
        this.updateSearch = this.updateSearch.bind(this);
        this.updateLimit = this.updateLimit.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.state = {
            search: '',
            limit: '',
            results: ''
        };
    }

    updateSearch(event) {
        this.setState({search: event.target.value});
    }

    updateLimit(event) {
        this.setState({limit: event.target.value});
    }

    handleSubmit(event) {
        event.preventDefault();
        fetch('http://' + this.props.server + ":" + this.props.port + "/search", {
            method: 'POST',
            body: JSON.stringify({
                "version": 3,
                "type": "search",
                "match": this.state.search,
                "limit": this.state.limit
            }),
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then(response => response.json())
            .then(resData => this.setState({results: JSON.stringify(resData)}));
    }

    render() {

        return(
            <Card>
                <CardBody>
                    <form onSubmit={this.handleSubmit}>
                          <div> Search trip destinations: </div>
                          <input name="Search Entry" type="text" value={this.state.search} onChange={this.updateSearch} />
                          <div> Enter number to limit search results or leave empty to display all results: </div>
                          <input name="Limit Field" type="text" value={this.state.limit} onChange={this.updateLimit} />
                          <button value="submit" className="btn-outline-dark unit-button" onClick={this.handleSubmit}>Submit</button>
                    </form>

                    <div>Data Received: </div><br />
                    {this.state.results}
                </CardBody>
            </Card>
        )
    }
}

export default Search;