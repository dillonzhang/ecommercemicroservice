import React from 'react';
import ReactDOM from 'react-dom';

import '../../less/lib/less/bootstrap.less';
import {Grid,FormGroup,FormControl,Button,Form,Col,Checkbox} from 'react-bootstrap';

import './login.less';

class Login extends React.Component {  
    constructor(props){
      super(props);
      
    }

    componentWillMount(){         
        var _this = this; 
        fetch(`./categorySelect.mock.json`,{
            method: 'get',               
            dataType: "json",
        }).then(res => {
            res.json().then(function(data){                   
                const categorySelect = data; 
                _this.setState({ categorySelect });
                console.log(categorySelect);
            })
            }               
        ).catch(function(err){
                console.log("Fetch错误:"+err);
        }); 




        let url = "http://dillionzhangt:8902/microservice.auth.service/oauth/token";

        let formData = new FormData();
        formData.append('client_id','client_2');
        formData.append('client_secret', '123456');
        formData.append('grant_type', 'password');
        formData.append('username', this.state.userName);
        formData.append('password', this.state.passWord);
        formData.append('scope', 'select');
        

        fetch(url,{
            method: 'post',
            body: formData,
        }).then(function (res) {
            return res.json();
        }).then(function (json) {
            if (json.code == "200") {
                console.log("232323233-----正确")
            }else if (json.code == "400") {
                console.log("2323232323------错了～")
            }
        })

    }

     render() {        
        return (
          <Grid className='login-layout'>
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