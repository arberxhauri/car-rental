import React, { useEffect, useState } from "react";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPeopleGroup} from "@fortawesome/free-solid-svg-icons";
import {
  faEye,
  faEdit,
  faTrash,
  faPlus,
  faStar,
} from "@fortawesome/free-solid-svg-icons";
import { Link, useParams } from "react-router-dom";

export default function Home() {
  const [customers, setCustomers] = useState([]);
  const { customerId } = useParams();
  const storedToken = 'Basic ' + btoa(localStorage.getItem('authToken'));

  useEffect(() => {
    loadCustomers();
  }, []);

  const loadCustomers = async () => {
    try {
      const result = await axios.get("http://localhost:8080/api/costumer/list",{
        headers: {
          'Authorization': storedToken,
          'Content-Type': 'application/json',
        }
      });
      setCustomers(result.data);
      console.log(result.data);
    } catch (error) {
      console.error("Error loading customer:", error);
    }
  };

  const deleteCustomer = async (id) => {
    const result = window.confirm("Are you sure you want to delete this customer?");
    if (result) {
      try {
        await axios.delete(`http://localhost:8080/api/costumer/${id}`,{
          headers: {
            'Authorization': storedToken,
            'Content-Type': 'application/json',
          }
        });
        loadCustomers();
      } catch (error) {
        console.error("Error deleting customer:", error);
      }
    }
  }


  return (
    <div className="container">
      <div className="d-flex align-items-center">
        <Link className="btn btn-outline me-3 custom-button"
              style={{ fontWeight: "bold", color: "#9400D3", borderColor: "#9400D3", marginTop: "1rem" }}
              to="/customer/save">
          <FontAwesomeIcon icon={faPeopleGroup} className="me-2" />
          Add Customer
        </Link>
      </div>
      <div className="py-4">
        <div className="table-responsive">
          <table className="table border shadow">
            <thead className="table-header">
              <tr>
                <th scope="col">#</th>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
                <th scope="col">Username</th>
                <th scope="col">Email</th>
                <th scope="col">Address</th>
                <th scope="col">Age</th>
                <th scope="col">Phone Number</th>
                <th scope="col">Actions</th>
              </tr>
            </thead>
            <tbody>
              {customers.map((customer, index) => (
                <tr key={index} className={index % 2 === 0 ? "even-row" : ""}>
                  <th scope="row">{index + 1}</th>
                  <td>{customer.firstName}</td>
                  <td>{customer.lastName}</td>
                  <td>{customer.username}</td>
                  <td>{customer.email}</td>
                  <td>{customer.address}</td>
                  <td>{customer.age}</td>
                  <td>{customer.phoneNumber}</td>
                  <td>
                    <Link className="btn btn-primary mx-2" to={`/customer/view/${customer.id}`} style={{ backgroundColor: "#9400D3", borderColor: "#9400D3" }}>
                      <FontAwesomeIcon icon={faEye} /> View
                    </Link>
                    <Link className="btn btn-outline-primary mx-2" style={{ color: "#9400D3", borderColor: "#9400D3" }} to={`/customer/${customer.id}`}>
                      <FontAwesomeIcon icon={faEdit} /> Edit
                    </Link>
                    <button
                        className="btn btn-primary mx-2"
                        style={{ backgroundColor: "#9400D3", borderColor: "#9400D3" }}
                        onClick={() => deleteCustomer(customer.id)}
                    >
                      <FontAwesomeIcon icon={faTrash} />
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}
