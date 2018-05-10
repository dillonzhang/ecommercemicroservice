import React from 'react';
import ReactDOM from 'react-dom';
import { Button,Alert ,Grid,Row,Col} from 'react-bootstrap';

import axios from 'axios';

class Box extends React.Component {  
    constructor(props){
      super(props);
      this.state = {
        persons: [],
        like: true
      }
      this.likeButton = this.likeButton.bind(this);
    }

    likeButton(){
        this.setState(( prevState )=>{
            like: !this.prevState.like
        });
    }
    componentDidMount() {
        axios.get(`https://jsonplaceholder.typicode.com/users`)
          .then(res => {
            const persons = res.data;
            this.setState({ persons });
          })
      }
     render() {
        
        return (
            <div className='box'>
                test
                <ul>
                    { this.state.persons.map(person => <li>{person.name}</li>)}
                </ul>
            </div>
        );
    }
}

export default Box;