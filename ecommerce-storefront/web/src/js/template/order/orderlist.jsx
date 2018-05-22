import React from 'react';
import ReactDOM from 'react-dom';

import {Grid,Row,Col} from 'react-bootstrap';
import '../../../less/lib/less/bootstrap.less';

import './order.less';

class OrderList extends React.Component {  
    constructor(props){
      super(props);
      this.state = {
        orderList: [],
      }
    }
    componentWillMount(){         
        var _this = this; 
          fetch(`./orderlist.mock.json`,{
                method: 'get',               
                dataType: "json",
            }).then(res => {
                res.json().then(function(data){                   
                    const orderList = data; 
                    _this.setState({ orderList });
                })
                }               
            ).catch(function(err){
                    console.log("Fetch错误:"+err);
            }); 
          }

     render() {          
        return (
            <Grid>                
                {this.state.orderList.map((list,index,k) => (                         
                     <div key={index} className='order-list-layout clearfix'>  
                        <div className='order-list-title clearfix'>
                            <Col className='' sm={1}>{ this.props.customerId }</Col>   
                            <Col className='list-name' sm={2}>{ this.props.name }</Col>                                
                            <Col className='list-entry' sm={7}>{ this.props.orderEntries}</Col>
                            <Col className='list-totalPrice' sm={2}>{ this.props.totalPrice}</Col>
                        </div>    
                        <div className='order-list-wrap'>  
                            <Col className='list-name' sm={1}>{ list.customerId }</Col>                    
                            <Col className='list-name' sm={2}>{ list.name }</Col>                                
                            <Col className='list-entry' sm={7}>
                                { list.orderEntries.map((entries,index) => (
                                    <div className='list-entry-item' key={index}>
                                        <div className='productId'>{this.props.productId} -- {entries.id}</div>
                                        <div className='totalPrice'>{this.props.orderEntriesTotalPrice} -- {entries.totalPrice}</div>
                                    </div>
                                ))}
                            </Col>
                            <Col className='list-totalPrice' sm={2}>{ list.totalPrice}</Col>
                        </div>
                    </div>
                ))} 
             </Grid>
            
        );
    }
}
OrderList.defaultProps = {
    name: 'name',
    totalPrice: 'totalPrice',
    customerId: 'Id',
    orderEntries: 'orderEntries',
    productId: 'productId',
    orderEntriesTotalPrice: 'orderEntriesTotalPrice',

}
export default OrderList;