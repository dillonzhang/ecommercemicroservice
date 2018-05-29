import React from 'react';
import ReactDOM from 'react-dom';

import {Grid,Row,Col} from 'react-bootstrap';
import '../../../less/lib/less/bootstrap.less';

import './categorySelect.less';

class CategoryList extends React.Component {
    constructor(props){
        super(props)
    }
   
    render(){

        return (
            <li> 
                <label>
                    <input type='checkbox' />
                    {this.props.item.categoryName} 
                    ( {this.props.item.categoryNum} ) 
                </label>
            </li>     
        )
    }
}

class CategorySelect extends React.Component {  
    constructor(props){
      super(props);
      this.state = {
        categorySelect: [],
        show: true, 
        active: false,
        current: null     
      }
      this.handleClick = this.handleClick.bind(this);
    }
    handleClick(index){     
      this.setState((prevState) =>({
        show: !prevState.show,
        active: !prevState.active,
        current: index  
      }));
      console.log(index);
    }

    componentWillMount(){         
        var _this = this; 
        fetch(`./categorySelect.mock.json`,{
            method: 'get',               
            dataType: "json",
        }).then(res => {
            res.json().then(function(data){                   
                const categorySelect = data; 
                _this.setState({ categorySelect });
                console.log(categorySelect);
            })
            }               
        ).catch(function(err){
                console.log("Fetch错误:"+err);
        }); 
    }
  
    
     render() {         
        return (
            <div className='category-select-wrap'> 
                    {this.state.categorySelect.map((category,index) => (
                        <div className='category-layout' key={index}>
                            <div className='category-layout-title clearfix'>
                                <span className='name'> {category.title} </span>
                                <span className={this.state.active && (this.state.current == index) ? 'clear active' : 'clear'}  onClick={() => this.handleClick(index) } > </span>
                            </div>
                            <ul className= {this.state.active && (this.state.current == index) ? 'category-list-wrap hide' : 'category-list-wrap'}>
                                {category.categoryList.map((list,index) => (                                            
                                    <CategoryList key={index} item={list} />                  
                                ))}
                             </ul>
                        </div>   
                    ))}
            </div>
        );
    }
}
export default CategorySelect;

CategorySelect.defaultProps = {
    'clear': 'clear'    
}