import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faGraduationCap } from "@fortawesome/free-solid-svg-icons";

export default function Footer() {
  return (
    <footer className="navbar navbar-expand-lg navbar-dark" style={{ backgroundColor: "#9400D3" }}>
      <div className="container-fluid justify-content-center">
      <div className="container">
        <p className="mb-0">
          <FontAwesomeIcon icon={faGraduationCap} className="me-2" />
           Final Project 
        </p>
        <p className="mb-0">Copyright Saimir Bardhi &copy; 2023</p>
        </div>
      </div>
    </footer>
  );
};
