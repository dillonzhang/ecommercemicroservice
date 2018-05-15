import React from 'react';
import ReactDOM from 'react-dom';

import {Grid,Row,Col,Navbar} from 'react-bootstrap';

import Logo from './logo.jsx';
import Menu from './menu.jsx';


class Header extends React.Component {  
    constructor(props){
      super(props);
      
    }

     render() {
        
        return (
            <Navbar>
                <Grid>
                    <Row>
                      <header>
                          <Col xs={6} md={4}>
                              <a href="#home"><Logo /></a>                           
                          </Col>
                          <Col xs={6} md={8}>
                              <Menu />
                          </Col>
                        </header>
                    </Row>
                </Grid>
            </Navbar>
        );
    }
}

export default Header;