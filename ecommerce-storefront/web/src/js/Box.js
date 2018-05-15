import React from 'react';
import ReactDOM from 'react-dom';
import { Button,Alert ,Grid,Row,Col} from 'react-bootstrap';

import Test from './test.jsx';
import axios from 'axios';

class Box extends React.Component {  
    constructor(props){
      super(props);
      this.state = {
        persons: [],
        like: true,
        customer: ''
      }
      this.likeButton = this.likeButton.bind(this);
    }

    likeButton(){
        this.setState(( prevState )=>{
            like: !this.prevState.like
        });
    }
    componentDidMount() {
        // axios.get(`http://jsonplaceholder.typicode.com/users`)
        //   .then(res => {
        //     const persons = res.data;
        //     this.setState({ persons });
        //   })
          // axios.get(`http://dillonzhang:8102/customerapi/customer/20`)
          // .then(res => {
          //   const customer = res.data;
          //   this.setState({ customer });
          // })

          var _this = this; 
          fetch(`http://jsonplaceholder.typicode.com/users`,{
                method: 'get',               
                dataType: "json",
            }).then(res => {
                res.json().then(function(data){                   
                    const persons = data; 
                    _this.setState({ persons });
                })
                }               
            ).catch(function(err){
                    console.log("Fetch错误:"+err);
            });
            fetch(`http://dillonzhang:8102/customerapi/customer/20`,{
                method: 'get',               
                dataType: "json",
                mode: 'cors',
                // mode: "no-cors",
                headers:{
                    // 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                    // 'Content-Type': 'application/x-www-form-urlencoded',
                    //"Content-Type": 'application/json',
                    // "Accept": 'application/json',
                    // "Origin": '*',
                    // "Access-Control-Allow-Origin": '*'
                },
            }).then(res => {
                res.json().then(function(data){
                    console.log(data);                   
                })
                }               
            ).catch(function(err){
                    console.log("dillonzhang:8102错误:"+err);
            });
      }
     render() {
        
        return (
            <div className='box'>
                 <Row className="show-grid">
                    { this.state.persons.map(person => <Col xs={6} md={4}>{person.name}</Col>)}
                  </Row>   
                  {/*
                        <ul>
                            { this.state.persons.map(person => <li>{person.name}</li>)}
                        </ul>  
                    */}           
                
                <p>{this.state.customer.id}</p>
                <Test name="test" />
            </div>
        );
    }
}

export default Box;