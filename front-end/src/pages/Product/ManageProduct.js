import axios from 'axios';
import React, {useEffect, useState} from 'react'
import { Container, Row, Button, Col, Table, Modal } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { URLS } from '../../routes';
import displayToast from '../../utils/displayToast';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faPen, faTrash, faPenSquare } from '@fortawesome/free-solid-svg-icons';

function ManageProduct() {
    const [products, setProducts] = useState([]);
    const [currentProduct, setCurrentProduct] = useState(null);

    const [show, setShow] = useState(false);
    const handleClose = () => {
        setShow(false);
        setCurrentProduct(null);
    }

    const handleShow = () => setShow(true);

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
        const url = URLS.GET_ALL_PRODUCTS;
        axios.get(url)
              .then(function (response) {
                // console.log(response);
                setProducts(response.data);
              })
              .catch(function (error) {
                console.log(error);
                displayToast({type : "error", msg : "Oops! Something went wrong"});
              });
    }

    const deleteProductConfirmation = (b) =>{
        setCurrentProduct(b);
        handleShow();
    }

    const deleteBuyer = async () => {
        const url = URLS.DELETE_PRODUCT + currentProduct.id;
        // const data = {
        //     id : currentBuyer.id
        // };
        axios.delete(url)
              .then(function (response) {
                handleClose();
                // console.log(response);
                displayToast({type : "success", msg : "Product deleted successfully!"});
                fetchProducts();
              })
              .catch(function (error) {
                console.log(error);
                displayToast({type : "error", msg : "Oops! Something went wrong"});
              });
    }

    return (
        <Container className="container-main">  
            <Row className="container-main">
                <Col></Col>
                <Col><h3>Merchandise</h3></Col>
            </Row>      
         <Row>
         <Table className='table table-dark table-striped'>
                <thead class="table-light">
                    <tr>
                        <th>Sr No</th>
                        <th>Merchandise Name</th>
                        <th>Price($)</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>

                <tbody>
                {products.map((product, index) => {
                    const {id, productName, quantity, price = 0} = product;

                    return (<tr key={id}>
                        <td>{index+1}</td>
                        <td>{productName}</td>
                        <td>{price}</td>
                        <td>{quantity}</td>
                        <td>
                            <Link to={`/edit-product/?id=${id}`}><FontAwesomeIcon icon={faPen}/>&nbsp;&nbsp;&nbsp;</Link>
                            <FontAwesomeIcon onClick={()=>deleteProductConfirmation(product)} icon={faTrash} style={{color: "#ff0000",}} />
                        </td>
                    </tr>);
                })}
                    
                </tbody>
            </Table>
          </Row>
          <Row className="container-main">
                <Col>
                    <Link to="/add-product">
                        <Button variant="info">Add Merchandise</Button>
                    </Link>
                </Col>
            </Row>

          <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
            <Modal.Title>Delete Confirmation</Modal.Title>
            </Modal.Header>
            <Modal.Body>Are you sure you want to delete {currentProduct ? currentProduct.productName : ""}</Modal.Body>
            <Modal.Footer>
            <Button variant="secondary" onClick={handleClose}>
                Close
            </Button>
            <Button variant="danger" onClick={deleteBuyer}>
                Delete
            </Button>
            </Modal.Footer>
        </Modal>
        </Container>
    )
}

export default ManageProduct;
