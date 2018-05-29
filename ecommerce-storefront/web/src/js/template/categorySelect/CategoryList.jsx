import React from 'react';
import ReactDOM from 'react-dom';

import {Grid,Row,Col} from 'react-bootstrap';
import '../../../less/lib/less/bootstrap.less';

import './categorySelect.less';

class CategoryList extends React.Component {
    constructor(props){
        super(props);     
    }
   
    render(){

        return (
            <li> 
                <label>
                    <input type='checkbox' className='category-item-input' />
                    <span className='category-item-name'>{this.props.item.categoryName} </span>
                    <span className='category-item-num'>( {this.props.item.categoryNum} ) </span>
                </label>
            </li>     
        )
    }
}


export default CategoryList;