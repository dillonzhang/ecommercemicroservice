import '../less/main.less';
import React from 'react';
import ReactDOM from 'react-dom';

import Box from './Box.js';
import Header from './header/index.jsx';
import Login from './login/login.jsx';
import CarouselItem from './template/Carousel.jsx';
import Footer from './footer/index.jsx';
ReactDOM.render(
    <div>
        <Header />
        <Login />
        <CarouselItem />
        <Footer />
    </div>,
    document.getElementById('container')
);