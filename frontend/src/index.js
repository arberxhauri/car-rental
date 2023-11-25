// index.js or another entry file
import React from 'react';
import ReactDOM from 'react-dom';
import { AuthProvider } from './AuthContext';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {BrowserRouter as Router, Routes, Route, useNavigate} from 'react-router-dom';
import Login from "./pages/login";

ReactDOM.render(
    <React.StrictMode>
        <AuthProvider>
            <Router>
                <App />
            </Router>
        </AuthProvider>
    </React.StrictMode>,
    document.getElementById('root')
);

reportWebVitals();
