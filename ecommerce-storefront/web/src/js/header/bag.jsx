import React from 'react';
import ReactDOM from 'react-dom';


class HeaderBag extends React.Component {  
    constructor(props){
      super(props);
      
    }

     render() {        
        return (            
            <div className='header-bag'>
                <a href='#' className=''>My BAG
                <span className='glyphicon glyphicon-user'></span></a>
            </div>
        );
    }
}

export default HeaderBag;