import React, { useState, useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import axios from "axios";
import { faHome,faInfoCircle } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';


export default function ViewCustomer() {
  const [student, setStudent] = useState({
    firstName: "",
    lastName: "",
    age: 0,
    username: "",
    email: "",
    address: "",
    phoneNumber: 0,
  });
  const { id } = useParams();
  const storedToken = 'Basic ' + btoa(localStorage.getItem('authToken'));

  useEffect(() => {
    loadStudent();
  }, []);

  const loadStudent = async () => {
    const result = await axios.get(`http://localhost:8080/api/costumer/${id}`,{
      headers: {
        'Authorization': storedToken,
        'Content-Type': 'application/json',
      }
    });
    setStudent(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2
            className="text-center mb-4"
            style={{
              color: "#9400D3",
              fontSize: "2.5rem",
              fontFamily: "Arial, sans-serif",
            }}
          >
            <FontAwesomeIcon icon={faInfoCircle} className="me-2" />
             Info
          </h2>

          <div className="card">
            <div className="card-header">
              Details of Student with id: {id}
              <ul className="list-group list-group-flush">
                <li className="list-group-item">
                  <b>First Name:</b> {student.firstName}
                </li>
                <li className="list-group-item">
                  <b>Last Name:</b> {student.lastName}
                </li>
                <li className="list-group-item">
                  <b>Age:</b> {student.age}
                </li>
                <li className="list-group-item">
                  <b>Username:</b> {student.username}
                </li>
                <li className="list-group-item">
                  <b>Email:</b> {student.email}
                </li>
                <li className="list-group-item">
                  <b>Address:</b> {student.address}
                </li>
                <li className="list-group-item">
                  <b>Phone Number:</b> {student.phoneNumber}
                </li>
                {/*<li className="list-group-item">*/}
                {/*  <b>Reviews:</b>*/}
                {/*  {student.reviewDtoList && student.reviewDtoList.length > 0 ? (*/}
                {/*    student.reviewDtoList.map((review, reviewIndex) => (*/}
                {/*      <div key={reviewIndex}>*/}
                {/*        <strong>{review.name}:</strong>{" "}*/}
                {/*        <span>{review.description}</span>*/}
                {/*      </div>*/}
                {/*    ))*/}
                {/*  ) : (*/}
                {/*    <div>No reviews available</div>*/}
                {/*  )}*/}
                {/*</li>*/}
              </ul>
            </div>
          </div>
          <Link
            className="btn btn-primary mx-2"
            style={{
              backgroundColor: "#9400D3",
              borderColor: "#9400D3",
            }}
            to={"/customer"}
          >
            <FontAwesomeIcon icon={faHome} className="me-2" />
             Back to Home
          </Link>
        </div>
      </div>
    </div>
  );
}
