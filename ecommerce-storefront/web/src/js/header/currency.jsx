import React from 'react';
import ReactDOM from 'react-dom';

import './searchbar.less';


class Currency extends React.Component {  
    constructor(props){
      super(props);
      
    }

     render() {        
        return (            
            <div className='currency-column'>  
                <a href='#' className='currency-column-item'>Help</a>
                <a href='#' className='currency-column-item'>Contact Us</a>                
                <a className='currency-column-item sessionSettings_button'>CN - £GBP</a>               
            </div>
        );
    }
}


export default Currency;