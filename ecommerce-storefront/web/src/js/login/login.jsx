import React from 'react';
import ReactDOM from 'react-dom';

import '../../less/lib/less/bootstrap.less';
import {Grid,FormGroup,FormControl,Button,Form,Col,Checkbox} from 'react-bootstrap';

import './login.less';

class Login extends React.Component {  
    constructor(props){
      super(props);
      this.state ={
        userName: null,
        passWord: null
      }
      this.handleChange = this.handleChange.bind(this);
      this.signIn = this.signIn.bind(this);
    }
    handleChange(ev) {
        this.setState({
          userName: ev.target.value
        })
        {/*alert(this.state.userName + "+++++++++" + ev.target.value);*/}
    }

    handleChangePassword(ev) {
        this.setState({
          passWord: ev.target.value
        })
      {/*alert(this.state.passWord + "+++++++++" + ev.target.value);*/}
    }

    signIn() {       
      var url = "http://dillonzhang:8902/microservice.customer.service/loginapi/login";
      var formData = new FormData();
      formData.append('client_id','client_1');
      formData.append('client_secret', '123456');
      formData.append('grant_type', 'client_credentials');
      var userdata = JSON.stringify({
        "customerEmail" : this.state.userName,
        "customerPwd" : this.state.passWord,
      })


      fetch(url, {
        method: 'POST',  
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        }, 
        body: userdata,
        
      }).then(function (res) {
          return res.json();
      }).then(function (json) {
          localStorage.setItem('accessToken', JSON.stringify(json.accessToken));          
          var accessToken = JSON.parse(localStorage.getItem('accessToken'));
          if (accessToken != "" || accessToken != undefined) {
              alert("-----正确")
          }else{
              alert("------错了～")
          }
      }).catch(function(err){
              console.log("Fetch错误1111:"+err);
      });

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
                    <input className='form-control' type="text" placeholder="Email" value={this.state.value} onChange= {(ev) => this.handleChange(ev)}/>
                  </Col>
                </FormGroup>

                <FormGroup controlId="formHorizontalPassword">
                  <Col sm={2}>
                    Password
                  </Col>
                  <Col sm={10}>
                    <input className='form-control' type="password" placeholder="Password" onChange= {(ev) => this.handleChangePassword(ev)}/>
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
                    <div className='btn btn-default' onClick={this.signIn}>Sign in</div>
                  </Col>
                </FormGroup>
              </Form>
            </Grid>
        );
    }
}

export default Login;