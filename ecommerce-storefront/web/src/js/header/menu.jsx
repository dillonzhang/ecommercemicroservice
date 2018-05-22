import React from 'react';
import ReactDOM from 'react-dom';

import '../../less/lib/less/bootstrap.less';
import {Grid,Row,Col} from 'react-bootstrap';

import './menu.less';


class MenuLayout extends React.Component {
  toggeClick(ev){
    this.props.onChangeIndex(ev);
  }
  toggeHide(ev){
    this.props.onHideIndex(ev);
  }
  render(){
    return(      
        <div className={this.props.active ? 'menu-toggle active' :'menu-toggle'} onMouseEnter = {ev => this.toggeClick(ev)} onMouseLeave={ev => this.toggeHide(ev)}>
          <div className="menu-name">{this.props.item.title}</div>
          <div className="menu-list-wrap">
            <Grid>
              { this.props.item.menuList.map((list,index) =>(  
                  <div className='sub-list-wrap' key={index}>
                      <div className='sub-title'>
                        { list.submenuTitle }
                      </div>
                      <div className='sub-list-layout'>
                        {list.submenuList.map((sublist,index) => (
                            <a key={index} href={sublist.href}>{sublist.name}</a>
                        ))}
                      </div>
                  </div>     
              ))}
            </Grid>  
          </div>
        </div>      
    )
  } 
}

class Menu extends React.Component {  
    constructor(props){
    super(props);
    this.state = {
      current: null,
      footerList: [], 
    }    
  }
  handleChange(index){     
    this.setState({
      current: index
    })  
  }
  hideIndex(index){
    this.setState({
      current: null
    })
  }
   componentWillMount(){         
        var _this = this; 
      fetch(`./menu.mock.json`,{
            method: 'get',               
            dataType: "json",
        }).then(res => {
            res.json().then(function(data){                   
                const footerList = data; 
                _this.setState({ footerList });
                console.log(footerList);
            })
            }               
        ).catch(function(err){
                console.log("Fetch错误:"+err);
        }); 
      }

     render() {       
        let menuList = this.state.footerList.map((item,index) => {
          return <MenuLayout active={this.state.current == index} item ={item} key={index} onChangeIndex={() => this.handleChange(index)} onHideIndex= {() => this.hideIndex(index)}/>
        })   
        return (    
          <div className='menu-wrap'>
              {menuList}
          </div>   
        );
    }
}

export default Menu;