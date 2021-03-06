import React, {Component} from 'react'
import {Input, Button, CardBody, ButtonToolbar, Card} from 'reactstrap';


class File extends Component{
    constructor(props) {
        super(props);
        this.uploadFile = this.uploadFile.bind(this);
        this.save = this.save.bind(this);
        this.state = {
            file: null,
            rawFile: ""
        }
    }

    //Function taken from Piazza. Post - "GUIDE: How to read a file from file input" by Caleb Carlson.
    uploadFile(event){
        let file = event.target.files[0]; // first file in the FileList
        this.setState({file: file});
        if(file){
            var reader = new FileReader();
            reader.onload = function(event){
                this.setState({rawFile: event.target.result});
                let parseResult = JSON.parse(event.target.result);
                this.props.updateBasedOnResponse(parseResult);
            }.bind(this);
            reader.readAsText(file);

        }
    }

    //Download function help using code from this site:
    //https://ourcodeworld.com/articles/read/189/how-to-create-a-file-and-generate
    //-a-download-with-javascript-in-the-browser-without-a-server
    save() {
        var element = document.createElement('a');
        element.setAttribute('href', 'data:text/plain;charset=utf-8,' +
            encodeURIComponent(JSON.stringify(this.props.trip)));
        element.setAttribute('download', this.props.trip.title+".json");
        element.style.display = 'none';
        document.body.appendChild(element);
        element.click();
        document.body.removeChild(element);
    }

    render() {

        return(
            <Card>
                <CardBody>
                    <p>Upload a trip if you already have one.</p>
                    <ButtonToolbar>
                        <Input type="file" title="input" accept=".json" onChange={this.uploadFile} className="inputfile"/>
                    </ButtonToolbar>
                    &nbsp;
                    <ButtonToolbar>
                        <Button onClick={this.save} type="button" className='btn-outline-dark unit-button'>
                            Save Trip
                        </Button>
                    </ButtonToolbar>
                </CardBody>
            </Card>
        )

    }
}

export default File;
