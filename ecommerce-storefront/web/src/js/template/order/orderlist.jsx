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
        let token = '95307c5f-abaf-4991-94a4-4a05f11a481a';
        let path= 'http://dillonzhang:8902/microservice.order.service/orderapi/order/1?access_token='
        let url = path + token ;
          {/*fetch(`./orderlist.mock.json`,{*/}
          fetch(url,{
                method: 'get',               
                dataType: "json",
            }).then(res => {
                res.json().then(function(data){                   
                    {/*const orderList = data;*/ }
                    const orderList = Array.of(data); 
                    _this.setState({ orderList });


                    if(data.code == 401){
                        console.log("2323232323------错了～")
                    }
                })
            }).catch(function(err){
                    console.log("Fetch错误:"+err);
            }); 
          }

     render() {          
        return (
            <div className=''>                
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
             </div>
            
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