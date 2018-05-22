import React from 'react';
import ReactDOM from 'react-dom';

import {Image,Thumbnail} from 'react-bootstrap';



class Logo extends React.Component {  
    constructor(props){
      super(props);
      
    }

     render() {        
        return (            
            <Image src="http://via.placeholder.com/265x40" />
        );
    }
}

export default Logo;