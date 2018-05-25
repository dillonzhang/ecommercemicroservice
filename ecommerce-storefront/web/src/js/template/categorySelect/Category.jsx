import React from 'react';
import ReactDOM from 'react-dom';

import {Grid,Row,Col} from 'react-bootstrap';
import '../../../less/lib/less/bootstrap.less';

import './categorySelect.less';
import CategoryList from './categoryList.jsx';

class Category extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            active: false
        }
        this.handleClick = this.handleClick.bind(this);
    }
  handleClick(){     
      this.setState((prevState) =>({
        active: !prevState.active
      }))
    }
   
    render(){

        return (
             <div className='category-layout'>
                <div className='category-layout-title clearfix'>
                    <span className='name'> {this.props.category.title} </span>
                    <span className={this.state.active ?'clear active' : 'clear'}  onClick={this.handleClick} > </span>
                </div>
                <ul className={this.state.active ?'category-list-wrap hide' :'category-list-wrap' }>
                    {this.props.category.categoryList.map((list,index) => (                                            
                        <CategoryList key={index} item={list} />                  
                    ))}
                 </ul>
            </div>    
        )
    }
}


export default Category;