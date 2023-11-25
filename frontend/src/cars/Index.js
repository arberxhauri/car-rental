import React, { useEffect, useState } from "react";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {faCarOn, faCarSide, faPeopleGroup} from "@fortawesome/free-solid-svg-icons";
import {
    faEye,
    faEdit,
    faTrash,
    faPlus,
    faStar,
} from "@fortawesome/free-solid-svg-icons";
import { Link, useParams } from "react-router-dom";

export default function Cars() {
    const [cars, setCars] = useState([]);
    const { carrId } = useParams();
    const storedToken = 'Basic ' + btoa(localStorage.getItem('authToken'));

    useEffect(() => {
        loadCars();
    }, []);

    const loadCars = async () => {
        try {
            const result = await axios.get("http://localhost:8080/api/cars/list", {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': storedToken,
                },
            });
            setCars(result.data);
            console.log(result.data);
        } catch (error) {
            console.error("Error loading cars:", error);
        }
    };

    const deleteCar = async (id) => {
        const result = window.confirm("Are you sure you want to delete this car?");
        if (result) {
            try {
                await axios.delete(`http://localhost:8080/api/cars/delete/${id}`,{
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': storedToken,
                    },
                });
                loadCars();
            } catch (error) {
                console.error("Error deleting car:", error);
            }
        }
    }


    return (
        <div className="container">
            <div className="d-flex align-items-center">
                <Link className="btn btn-outline me-3 custom-button"
                      style={{ fontWeight: "bold", color: "#9400D3", borderColor: "#9400D3", marginTop: "1rem" }}
                      to="/cars/save">
                    <FontAwesomeIcon icon={faCarOn} className="me-2" />
                    Add Car
                </Link>
            </div>
            <div className="py-4">
                <div className="table-responsive">
                    <table className="table border shadow">
                        <thead className="table-header">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Brand</th>
                            <th scope="col">Model</th>
                            <th scope="col">License Plate</th>
                            <th scope="col">Year</th>
                            <th scope="col">Color</th>
                            <th scope="col">Mileage</th>
                            <th scope="col">Status</th>
                            <th scope="col">Amount</th>
                            <th scope="col">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {cars.map((car, index) => (
                            <tr key={index} className={index % 2 === 0 ? "even-row" : ""}>
                                <th scope="row">{index + 1}</th>
                                <td>{car.brand}</td>
                                <td>{car.model}</td>
                                <td>{car.licensePlate}</td>
                                <td>{car.year}</td>
                                <td>{car.color}</td>
                                <td>{car.mileage}</td>
                                <td>{car.status}</td>
                                <td>{car.amount}</td>
                                <td>
                                    <Link className="btn btn-primary mx-2" to={`/cars/view/${car.id}`} style={{ backgroundColor: "#9400D3", borderColor: "#9400D3" }}>
                                        <FontAwesomeIcon icon={faEye} /> View
                                    </Link>
                                    <Link className="btn btn-outline-primary mx-2" style={{ color: "#9400D3", borderColor: "#9400D3" }} to={`/cars/${car.id}`}>
                                        <FontAwesomeIcon icon={faEdit} /> Edit
                                    </Link>
                                    <button
                                        className="btn btn-primary mx-2"
                                        style={{ backgroundColor: "#9400D3", borderColor: "#9400D3" }}
                                        onClick={() => deleteCar(car.id)}
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
