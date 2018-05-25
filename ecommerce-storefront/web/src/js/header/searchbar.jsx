import React from 'react';
import ReactDOM from 'react-dom';

import './searchbar.less';


class SearchBar extends React.Component {  
    constructor(props){
      super(props);
      
    }

     render() {        
        return (            
            <div className='search-bar clearfix'>
                <input className='form-control search-bar-input' type='text' placeholder={this.props.placeholder} />
                <button className='btn btn-primary search-bar-btn'>{this.props.buttonText}</button>
            </div>
        );
    }
}


SearchBar.defaultProps = {
    placeholder: 'Search for a brand or product…',
    buttonText: 'SEARCH'
}

export default SearchBar;