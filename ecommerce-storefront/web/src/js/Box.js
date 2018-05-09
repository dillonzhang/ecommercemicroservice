import React from 'react';
import ReactDOM from 'react-dom';

class Box extends React.Component {

    componentWillMount(){  
      $.ajax({
        url: 'http://dillonzhang:8102/customerapi/customer/18',
        type: 'GET',
        dataType: 'json',
        success:function(data){

        }.bind(this),
        console.log('访问一次后台')，
      })
    }
    render() {
        return (
            <div className='box'>
                I am a box
            </div>
        );
    }
}

export default Box;