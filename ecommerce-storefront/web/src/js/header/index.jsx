import React from 'react';
import ReactDOM from 'react-dom';

import {Grid,Row,Col,Navbar} from 'react-bootstrap';

import './index.less';
import Logo from './logo.jsx';
import Menu from './menu.jsx';
import SearchBar from './searchbar.jsx';
import Currency from './currency.jsx';
import HeaderAccount from './account.jsx';
import HeaderBag from './bag.jsx';



class Header extends React.Component {  
    constructor(props){
      super(props);
      
    }

     render() {
        
        return (  
            <header>          
                  <Grid>
                      <Row>                    
                          <div className='header-top'>
                              <Col xs={6} md={4}>
                                  <a href="#home"><Logo /></a>
                              </Col>
                              <Col xs={6} md={4}>
                                  <Currency />
                              </Col>
                              <Col xs={6} md={4}>
                                  <SearchBar />
                              </Col>
                          </div>
                          <div className='nav-menu-account clearfix'>
                              <Col xs={6} md={8} className='menu-layout'>
                                  <Menu />
                              </Col>
                              <Col xs={6} md={4}>
                                <div className='header-unit-wrap'>
                                  <HeaderAccount />
                                  <HeaderBag />
                                </div>
                              </Col>
                          </div>
                      </Row>
                  </Grid>
              </header>
        );
    }
}

export default Header;