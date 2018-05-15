import React from 'react';
import ReactDOM from 'react-dom';

import '../../less/lib/less/bootstrap.less';
import {Nav,NavItem,NavDropdown,MenuItem,FormGroup,FormControl,Button,Navbar} from 'react-bootstrap';



class Menu extends React.Component {  
    constructor(props){
      super(props);
      
    }

     render() {        
        return (
            <Nav>
              <NavItem eventKey={1} href="#">
                Home
              </NavItem>
              <NavItem eventKey={2} href="/login/index.jsx">
                login
              </NavItem>
              <NavDropdown eventKey={3} title="Dropdown" id="basic-nav-dropdown">
                <MenuItem eventKey={3.1}>Action</MenuItem>
                <MenuItem eventKey={3.2}>Another action</MenuItem>
                <MenuItem eventKey={3.3}>Something else here</MenuItem>
                <MenuItem divider />
                <MenuItem eventKey={3.4}>Separated link</MenuItem>
              </NavDropdown>
            </Nav>
        );
    }
}

export default Menu;