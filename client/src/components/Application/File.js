import React, {Component} from 'react'
import {
    Container, Col, Form,
    FormGroup, Label, Input,
    Button, CardBody, ButtonGroup, Card,
} from 'reactstrap';2
import App from './Application'

class File extends Component{
    constructor(props) {
        super(props);
        this.changefileName=this.changefileName.bind(this);
        this.state = {
            config: null,
            trip: {
                type: "trip",
                title: "",
                options : {
                    unit: "miles"
                },
                places: [],
                distances: [],
                map: '<svg width="1920" height="20" xmlns="http://www.w3.org/2000/svg" xmlns:svg="http://www.w3.org/2000/svg"><g></g></svg>'
            }
        };

    }

    //Method taken from piazza - Caleb Carlson
    changefileName(event){
        let file = event.target.files[0]; // first file in the FileList
        if (file) {
            let reader = new FileReader();
            reader.onload = function(event) {
                
                let newfile = JSON.parse(event.target.result);
                
                return function(e){
                    this.setState({trip:newfile});
                }

            };
            reader.readAsText(file);
        }


}


    render() {



        return(
            <Card>
                <CardBody>
                    <Form>
                        <FormGroup>
                            <Label for="exampleFile">File</Label>
                            <Input type="file" name="file" id="exampleFile" onChange={this.changefileName}/>
                            {/*<Button*/}
                                {/*onClick={(event) => this.props.uploadFile(event)}*/}
                            {/*>*/}
                                {/*Submit*/}
                            {/*</Button>*/}
                        </FormGroup>

                    </Form>
                </CardBody>
            </Card>
        )
    }
}

export default File;
