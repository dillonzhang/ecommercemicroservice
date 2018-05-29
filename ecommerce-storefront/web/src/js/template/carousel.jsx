import React from 'react';
import ReactDOM from 'react-dom';

import '../../less/lib/less/bootstrap.less';
import './carousel.less';

import {Grid,Row,Carousel} from 'react-bootstrap';



class CarouselItem extends React.Component {  
    constructor(props){
      super(props);
      
    }

     render() {        
        return (
           <Grid className="banner-img">
           <Row>
            <Carousel>
              <Carousel.Item>
                <img className="banner-img" src="http://via.placeholder.com/1170x500" />
                <Carousel.Caption>
                  <h3>First slide label</h3>
                  <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
                </Carousel.Caption>
              </Carousel.Item>
              <Carousel.Item>
                <img className="banner-img" src="http://via.placeholder.com/1170x500" />
                <Carousel.Caption>
                  <h3>Second slide label</h3>
                  <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                </Carousel.Caption>
              </Carousel.Item>
              <Carousel.Item>
                <img className="banner-img" src="http://via.placeholder.com/1170x500" />
                <Carousel.Caption>
                  <h3>Third slide label</h3>
                  <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>
                </Carousel.Caption>
              </Carousel.Item>
            </Carousel>
            </Row>
          </Grid>
        );
    }
}

export default CarouselItem;