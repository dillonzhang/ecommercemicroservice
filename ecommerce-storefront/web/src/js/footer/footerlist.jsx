import React from 'react';
import ReactDOM from 'react-dom';

import {Grid,Row,Col} from 'react-bootstrap';
import '../../less/lib/less/bootstrap.less';
import './footer.less';


class FooterList extends React.Component {  
    constructor(props){
      super(props);
      this.state = {
        footerList: [],
      }
    }
    componentWillMount(){         
        var _this = this; 
      fetch(`./footer.mock.json`,{
            method: 'get',               
            dataType: "json",
        }).then(res => {
            res.json().then(function(data){                   
                const footerList = data; 
                _this.setState({ footerList });
            })
            }               
        ).catch(function(err){
                console.log("Fetch错误:"+err);
        }); 
      }

     render() {          
        return (
            <Row className="footer-list">
                { this.state.footerList.map((footer,index) => (
                    <Col xs={12} sm={3} key={index} className='footer-list-layout'>
                        <div className='footer-list-title'>{footer.title}</div>
                        <div className='footer-list-item'>
                        { footer.list.map((listitem,index) => (
                            <a href='#' key={index}>{listitem}</a>
                        ))}
                        </div>
                    </Col>
                ))}
         
             </Row> 
            
        );
    }
}

export default FooterList;