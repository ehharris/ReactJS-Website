import React, {Component} from 'react'
import {Input, Button, CardBody, ButtonToolbar, Card,} from 'reactstrap';
import {request} from "../../api/api";


class File extends Component{
    constructor(props) {
        super(props);
        this.uploadFile = this.uploadFile.bind(this);
        this.plan = this.plan.bind(this);
        this.state = {file: null}
    }

    //Function taken from Piazza. Post - "GUIDE: How to read a file from file input" by Caleb Carlson.
    uploadFile(event){
        let file = event.target.files[0]; // first file in the FileList
        this.setState({file: file})
        if(file){
            var reader = new FileReader();
            reader.onload = function(event){
                console.log(event.target.result);
                //Update state with parsed info using updateBasedOnResponse function
                let parseResult = JSON.parse(event.target.result);
                this.props.updateBasedOnResponse(parseResult);
            }.bind(this);
            reader.readAsText(file)
        }
    }

    plan(){
        let responsePlan = request(this.props.trip, 'plan').then(
            res => {this.props.updateBasedOnResponse(res);}
        );
    }

    render() {

        return(
            <Card>
                <CardBody>
                    <p>Upload a file to start planning your trip!</p>
                    <ButtonToolbar>
                        <Input type="file" title="input" accept=".json" onChange={this.uploadFile}/>
                    </ButtonToolbar>
                    &nbsp;
                    <ButtonToolbar>
                        <Button onClick={this.plan} type="button" className='btn-outline-dark unit-button'>
                            Plan
                        </Button>
                    </ButtonToolbar>
                </CardBody>
            </Card>
        )

    }
}

export default File;
