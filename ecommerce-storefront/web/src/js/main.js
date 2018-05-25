import '../less/main.less';
import React from 'react';
import ReactDOM from 'react-dom';

import {Grid,Row,Col} from 'react-bootstrap';

import Box from './Box.js';
import Header from './header/index.jsx';
import Login from './login/login.jsx';
import CarouselItem from './template/Carousel.jsx';
import OrderList from './template/order/orderlist.jsx';
import CategorySelect from './template/CategorySelect/categorySelect.jsx';
import Footer from './footer/index.jsx';
ReactDOM.render(
    <div>
        <Header />
        <Login />
        {/*<CarouselItem />*/}
        <Grid>
            <Row>
                <Col sm={3}>
                    <CategorySelect />    
                </Col>
                <Col sm={9}>
                    <OrderList />
                </Col>
            </Row>
        </Grid>
        <Footer />
    </div>,
    document.getElementById('container')
);