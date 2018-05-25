import React from 'react';
import ReactDOM from 'react-dom';

import {Grid,Row,Col,Navbar} from 'react-bootstrap';
import '../../less/lib/less/bootstrap.less';
import FooterList from './footerlist.jsx';



class Footer extends React.Component {  
    constructor(props){
      super(props);
      
    }

     render() {
        
        return (
            <Grid>
                <Row className="footer-label">
                    <FooterList url='footer.mock.json' />                       
                </Row>
            </Grid>
           
        );
    }
}

export default Footer;