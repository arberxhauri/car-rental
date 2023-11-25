import React, { useState } from "react";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faUser,
  faCheck,
  faTimes,
} from "@fortawesome/free-solid-svg-icons";
import { useNavigate, useParams,Link } from "react-router-dom";

function AddReview() {
  const navigate = useNavigate();
  const { studentId } = useParams();

  const [reviewDto, setReviewDto] = useState({
    name: "",
    description: ""
  });

  const onInputChange = (event) => {
    const { name, value } = event.target;
    setReviewDto({ ...reviewDto, [name]: value });
  };

  const { name, description } = reviewDto;

  const onSubmit = async (event) => {
    event.preventDefault();
    try {
      await axios.post(`http://localhost:8080/api/reviews/save/${studentId}`, reviewDto);
      navigate("/");
    } catch (error) {
      
      console.error("Error occurred while adding review:", error);
    }
  };

  return (
    <div>
      <h2>Add Review</h2>
      <form onSubmit={(event) => onSubmit(event)}>
        <div className="mb-3 d-flex align-items-center">
          <label htmlFor="Name" className="form-label with-shadow">
            <FontAwesomeIcon
              icon={faUser}
              className="mr-2"
              style={{ color: "#9400D3" }}
            />
          </label>
          <input
            type="text"
            className="form-control with-shadow custom-input"
            placeholder="Name"
            name="name"
            value={name}
            onChange={(event) => onInputChange(event)}
          />
        </div>
        <div className="mb-3 d-flex align-items-center">
          <label htmlFor="Description" className="form-label with-shadow">
            <FontAwesomeIcon
              icon={faUser}
              className="mr-2"
              style={{ color: "#9400D3" }}
            />
          </label>
          <input
            type="text"
            className="form-control with-shadow custom-input"
            placeholder="Description"
            name="description"
            value={description}
            onChange={(event) => onInputChange(event)}
          />
        </div>
        <button className="btn btn-primary mx-2" style={{ backgroundColor: "#9400D3", borderColor: "#9400D3" }}>
          <FontAwesomeIcon icon={faCheck} />
        </button>

        <Link className="btn btn-primary mx-2" style={{ backgroundColor: "#9400D3", borderColor: "#9400D3" }} to="/">
          <FontAwesomeIcon icon={faTimes} />
        </Link>
      </form>
    </div>
  );
}

export default AddReview;
