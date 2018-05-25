import '../less/main.less';
import React from 'react';
import ReactDOM from 'react-dom';


import OrderList from './template/order/orderlist.jsx';


ReactDOM.render(   
    <OrderList />,
    document.getElementById('container')
);