import React, { useState } from "react";
import "./LandingPage.css";
import axios from "axios";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const LandingPage = () => {
  const [isLogin, setIsLogin] = useState(true);
  const [isSignUp, setSignUp] = useState(true);
  const [isReset, setIsReset] = useState(false);
  const [formData, setFormData] = useState({
    fullName: '',
    email: '',
    password: '',
    confirmPassword: ''
  });

  const toggleLogin = () => {
    setIsLogin(true);
    setSignUp(false);
    setIsReset(false);
  };

  const toggleSignUp = () => {
    setIsLogin(false);
    setSignUp(true);
    setIsReset(false);
  };

  const toggleReset = () => {
    setIsLogin(false);
    setSignUp(true);
    setIsReset(true);
  };

  const toggleBack = () => {
    setIsLogin(true);
    setSignUp(false);
    setIsReset(false);
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();  // Prevent default form submission behavior

    console.log("On submit");

    // Password and confirmPassword validation
    if (isSignUp && formData.password !== formData.confirmPassword) {
      console.log("Password is %s", formData.password);
      console.log("Confirm Password is %s", formData.confirmPassword);
      toast.error("Passwords do not match!");
      return; // Stop the form from submitting
    }

    if (isReset) {
      console.log("Inside reset");
      // Call Reset Password API
      try {
        const response = await axios.post('http://localhost:8081/api/user', {
          email: formData.email,
        });
        console.log('Reset Password response:', response.data);
      } catch (error) {
        console.error('Error resetting password:', error);
      }
    } else if (isLogin) {
      // Call Login API
      console.log("Inside login");
      try {
        const response = await axios.post('http://localhost:8081/api/user', {
          email: formData.email,
          password: formData.password,
        });
        console.log('Login response:', response.data);
      } catch (error) {
        console.error('Error logging in:', error);
      }
    } else {
      // Call Sign Up API
      console.log("Inside signup");
      toast.success("Success!");
      try {
        const response = await axios.post('http://localhost:8081/api/user', {
          fullName: formData.fullName, 
          email: formData.email,
          password: formData.password,
        });
        console.log('Sign Up response:', response.data);
      } catch (error) {
        console.error('Error signing up:', error);
      }
    }
  };


  return (
    <div className="main-container">
      <div className="container">
        {!isReset ? (
          <div id="top-btns" className="btn-grp">
            <button
              className={`top-btn ${isLogin ? "selected" : ""}`}
              id="login-btn"
              onClick={toggleLogin}
            >
              Login
            </button>
            <button
              className={`top-btn ${!isLogin ? "selected" : ""}`}
              id="sign-up"
              onClick={toggleSignUp}
            >
              Sign Up
            </button>
          </div>
        ) : (
          <div id="reset-btns" className="btn-grp">
            <button className="top-btn selected" id="reset-btn">
              Reset Password
            </button>
            <button className="top-btn" id="back-btn" onClick={toggleBack}>
              Back
            </button>
          </div>
        )}
        <form onSubmit={handleSubmit}>
          {!isReset && !isLogin && (
            <div className="inputContainer" id="name">
              <input type="text" name="fullName" required autoComplete="off" onChange={handleInputChange} />
              <label>fullname</label>
            </div>
          )}
          <div className="inputContainer">
            <input type="email" name="email" required autoComplete="off" onChange={handleInputChange} />
            <label>email</label>
          </div>
          {!isReset && (
            <div className="inputContainer" id="passwd">
              <input type="password" name="password" required autoComplete="off" onChange={handleInputChange} />
              <label>password</label>
            </div>
          )}
          {!isReset && !isLogin && (
            <div className="inputContainer" id="re-pass">
              <input type="password" name="confirmPassword" required autoComplete="off" onChange={handleInputChange} />
              <label>confirm password</label>
            </div>
          )}
          <ToastContainer />
          <div className="btn">
            <input type="submit" name="submit" />
          </div>
          {isLogin && !isReset && (
            <div className="forget-pass">
              <p id="forget-pass" onClick={toggleReset}>
                FORGET YOUR PASSWORD?
              </p>
            </div>
          )}
        </form>
      </div>
    </div>
  );
};

export default LandingPage;
