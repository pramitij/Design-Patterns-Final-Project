import axios from 'axios';
import React, {useEffect, useState} from 'react'
import { Card, Col, Container, Row } from 'react-bootstrap';
// import { Link } from 'react-router-dom';
import { URLS } from '../routes';
import displayToast from '../utils/displayToast';

import '../styles/home.scss';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCoffee, faFileInvoiceDollar, faFileAlt, faShoppingBasket, faUserTie, faDollarSign, faBalanceScale, faWeightHanging, faSpinner, faUserAlt } from '@fortawesome/free-solid-svg-icons'

// const DATA = [
//     {
//         id : 1,
//         title : "Purchase Orders",
//         count : 10,
//         icon : faFileInvoiceDollar
//     },
//     {
//         id : 2,
//         title : "Invoices",
//         count : 8,
//         icon : faFileAlt
//     },
//     {
//         id : 3,
//         title : "Products",
//         count : 5,
//         icon : faCoffee
//     },
//     {
//         id : 4,
//         title : "Buyers",
//         count : 3,
//         icon : faShoppingBasket
//     },
//     {
//         id : 5,
//         title : "Employees",
//         count : 3,
//         icon : faUserTie
//     },
//     {
//         id : 6,
//         title : "Total Amount",
//         count : 190000,
//         icon : faDollarSign
//     },
//     {
//         id : 7,
//         title : "Total Quantity",
//         count : 504,
//         icon : faBalanceScale
//     },
//     {
//         id : 8,
//         title : "Total Orders",
//         count : 40,
//         icon : faWeightHanging
//     },
// ];

function Home() {
    
    // const DATA = [
    //     {
    //         id : 1,
    //         title : "Purchase Orders",
    //         count : products.filter,
    //         icon : faFileInvoiceDollar
    //     },
    //     {
    //         id : 2,
    //         title : "Invoices",
    //         count : 8,
    //         icon : faFileAlt
    //     },
    //     {
    //         id : 3,
    //         title : "Products",
    //         count : 5,
    //         icon : faCoffee
    //     },
    //     {
    //         id : 4,
    //         title : "Buyers",
    //         count : 3,
    //         icon : faShoppingBasket
    //     },
    //     {
    //         id : 5,
    //         title : "Employees",
    //         count : 3,
    //         icon : faUserTie
    //     },
    //     {
    //         id : 6,
    //         title : "Total Amount",
    //         count : 190000,
    //         icon : faDollarSign
    //     },
    //     {
    //         id : 7,
    //         title : "Total Quantity",
    //         count : 504,
    //         icon : faBalanceScale
    //     },
    //     {
    //         id : 8,
    //         title : "Total Orders",
    //         count : 40,
    //         icon : faWeightHanging
    //     },
    // ];
    const [products, setProducts] = useState([]);
    const [DATA,setData] =useState([]);
    // const [currentProduct, setCurrentProduct] = useState(null);

    // const [show, setShow] = useState(false);
    // const handleClose = () => {
    //     setShow(false);
    //     setCurrentProduct(null);
    // }

    // const handleShow = () => setShow(true);

    useEffect(() => {
        let isActive = true;

        if(isActive){
            fetchProducts();
        }
        return () => {
            isActive = false;
        }
    }, []);

    

    const fetchProducts = async () => {
        const url = URLS.GET_ALL_DATA_COUNTS;
        axios.get(url)
              .then(function (response) {
                console.log(response);
                
                setProducts(response.data);
                setData([
                    {
                        id : 3,
                        title : "Products",
                        count : response.data["products"],
                        icon : faSpinner
                    },
                    {
                        id : 1,
                        title : "Orders",
                        count : response.data["purchaseOrders"],
                        icon : faFileInvoiceDollar
                    },
                    {
                        id : 2,
                        title : "Invoices Generated",
                        count : response.data["invoices"],
                        icon : faFileAlt
                    },
                    
                    {
                        id : 4,
                        title : "Customers",
                        count :  response.data["buyers"],
                        icon : faUserAlt
                    },
                    {
                        id : 5,
                        title : "Employees",
                        count : response.data["employees"],
                        icon : faUserTie
                    },
                    
                    
                ]);
              })
              .catch(function (error) {
                console.log(error);
                displayToast({type : "error", msg : "Oops! Something went wrong"});
              });
    }

    const setdata = async () =>{
        // console.log(this.useState.products);
        // console.log(products);
     
        // console.log(products.data.buyers);
    }
    return (
        <Container>    
            <h1 align="center">Inventory Management</h1>
            <Row className="container-main">
            
            {/* <Col></Col> */}
            <Col>
            <ul class="list-group list-group-numbered col-mg-3">
                    {DATA.map((item, i) => {
                    let color = 'primary';

                    if(i === 1 || i === 5){
                        color = "info";
                    }else if(i === 2 || i === 6){
                        color = "success";
                    }else if(i === 3 || i === 7){
                        color = "warning";
                    }
                    // var count;
                    // if(item.title == 'Buyers'){
                    //     count = products.buyers;
                    // }
                    // else if(item.title == 'Purchase Orders'){
                    //     count = products.buyers;
                    // }
                    return(
                        
                        <li class="list-group-item d-flex justify-content-between align-items-center">
                            {item.title}
                            <span class="badge bg-primary rounded-pill">{item.count}</span>
                        </li> 
                        )   
                    })}
                </ul>
                </Col>
            </Row> 
        </Container>
    )
}

export default Home;
