import React from 'react';
import ReactDOM from 'react-dom';

import {Grid,Row,Col} from 'react-bootstrap';
import '../../../less/lib/less/bootstrap.less';

import './categorySelect.less';
import Category from './Category.jsx';

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
      }))
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
            <div>
                <div className='category-filter'>
                
                </div>
                <div className='category-select-wrap'> 
                    {this.state.categorySelect.map((category,index) => (
                        <Category category={category} key={index} />  
                    ))}
                </div>
            </div>
        );
    }
}
export default CategorySelect;

CategorySelect.defaultProps = {
    'clear': 'clear'    
}