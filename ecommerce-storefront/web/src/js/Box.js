import React from 'react';
import ReactDOM from 'react-dom';
import { Button ,Alert ,Grid,Row,Col} from 'react-bootstrap';

import axios from 'axios';

class Box extends React.Component {  
constructor(props){
      super(props);
      this.state = {
        like : false
      }
      this.likeButton = this.likeButton.bind(this);

    }
    likeButton(){
      this.setState({
        like : !this.state.like
      })
    }
     render() {
        let text = this.state.like ? 'like' : 'haven\'t liked';
        return (
            <div className='box'>
                I am a box
                <Button>Default</Button>
                <Button bsStyle='primary'>Primary</Button>
                <Alert bsStyle='warning'>
              <strong>Holy guacamole!</strong> Best check yo self, you're not looking too
              good.
            </Alert>
           <p className ="cursor-pointer" onClick = {this.likeButton}>
                You {text} it.
              </p>

              <Grid>
              <Row className="show-grid">
                <Col xs={12} md={8}>
                  col-md-8 col-xs-12
                </Col>
                <Col xs={6} md={4}>
                  col-md-4 col-xs-6
                </Col>
              </Row>

              <Row className="show-grid">
                <Col xs={6} md={4}>
                  col-md-4 col-xs-6
                </Col>
                <Col xs={6} md={4}>
                  col-md-4 col-xs-6
                </Col>
                <Col xsHidden md={4}>
                  col-md-4 col-xs-6
                </Col>
              </Row>
              
            </Grid>
            </div>
        );
    }
}

export default Box;