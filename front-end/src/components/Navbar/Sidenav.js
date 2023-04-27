import React, { useContext } from 'react';
import { useHistory, useLocation } from 'react-router-dom';
import { AuthContext } from '../../context/Auth';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faUserTie, faFileInvoiceDollar, faUserTag, faReceipt, faSpinner, faDollarSign } from '@fortawesome/free-solid-svg-icons';
import displayToast from '../../utils/displayToast';
import { Navbar, Container, Nav, Button } from 'react-bootstrap';


function Sidenav() {
  const history = useHistory();
  const location = useLocation();
  const { isLoggedIn, setUserData, userData } = useContext(AuthContext);

  const logoutUser = () => {
    displayToast('You have successfully logged out!', 'success');

    setTimeout(() => {
      setUserData(null);
      history.push('/');
    }, 1000);
  };

  if (!isLoggedIn) {
    return null;
  }

  return (
    <Navbar bg="light" variant="light">
      <Container>
        <Navbar.Brand href="/">Inventory Management</Navbar.Brand>

          <Nav>
            <Nav.Item>
              <Nav.Link
                eventKey="manage-products"
                onSelect={() => {
                  history.push('/manage-products');
                }}
                active={location.pathname === '/manage-products'}
              >

                Merchandise
              </Nav.Link>
            </Nav.Item>
            {userData.designation.toUpperCase() === 'MANAGER' && (
              <Nav.Item>
                <Nav.Link
                  eventKey="manage-employees"
                  onSelect={() => {
                    history.push('/manage-employees');
                  }}
                  active={location.pathname === '/manage-employees'}
                >

                  Employees
                </Nav.Link>
              </Nav.Item>
            )}
            <Nav.Item>
              <Nav.Link
                eventKey="manage-buyers"
                onSelect={() => {
                  history.push('/manage-buyers');
                }}
                active={location.pathname === '/manage-buyers'}
              >

                Members
              </Nav.Link>
            </Nav.Item>
            <Nav.Item>
              <Nav.Link
                eventKey="manage-purchase-order"
                onSelect={() => {
                  history.push('/manage-purchase-order');
                }}
                active={location.pathname === '/manage-purchase-order'}
              >

                Orders
              </Nav.Link>
            </Nav.Item>
            <Nav.Item>
              <Nav.Link
                eventKey="manage-invoice"
                onSelect={() => {
                  history.push('/manage-invoice');
                }}
                active={location.pathname === '/manage-invoice'}
              >

                Bills
              </Nav.Link>
            </Nav.Item>
          </Nav>

        <Nav className="mr-auto">
                  <Nav.Link>
                    <FontAwesomeIcon icon={faUser} className="side-nav-icons" /> {userData.fullName} -{' '}
                    <b>{userData.designation}</b>
                  </Nav.Link>
                </Nav>

                <Button variant="outline-dark" className="" onClick={logoutUser}>
                  Logout
                </Button>

      </Container>
    </Navbar>
  );
}

export default Sidenav;
