import React from 'react';
import ReactDOM from 'react-dom';


class HeaderAccount extends React.Component {  
    constructor(props){
      super(props);
      
    }

     render() {        
        return (            
            <div className='header-account'>
                <a href='/login/login.jsx' className=''>My Account 
                <span className='glyphicon glyphicon-user'></span></a>
            </div>
        );
    }
}

export default HeaderAccount;