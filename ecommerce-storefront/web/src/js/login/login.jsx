import React from 'react';
import ReactDOM from 'react-dom';

import '../../less/lib/less/bootstrap.less';
import {Grid,FormGroup,FormControl,Button,Form,Col,Checkbox} from 'react-bootstrap';



class Login extends React.Component {  
    constructor(props){
      super(props);
      
    }

     render() {        
        return (
          <Grid>
              <Form horizontal>
                <FormGroup controlId="formHorizontalEmail">
                  <Col sm={2}>
                    Email
                  </Col>
                  <Col sm={10}>
                    <FormControl type="email" placeholder="Email" />
                  </Col>
                </FormGroup>

                <FormGroup controlId="formHorizontalPassword">
                  <Col sm={2}>
                    Password
                  </Col>
                  <Col sm={10}>
                    <FormControl type="password" placeholder="Password" />
                  </Col>
                </FormGroup>

                <FormGroup>
                  <Col smOffset={2} sm={10}>
                    <Checkbox className='pull-left'>Remember me</Checkbox>
                    <a className='pull-right' href='#'>Forgotten your password?</a>
                  </Col>
                </FormGroup>

                <FormGroup>
                  <Col smOffset={2} sm={10}>
                    <Button type="submit">Sign in</Button>
                  </Col>
                </FormGroup>
              </Form>
            </Grid>
        );
    }
}

export default Login;