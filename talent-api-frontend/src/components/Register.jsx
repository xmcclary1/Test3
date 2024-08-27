import React, { useState } from 'react';
import { registerAPICall } from '../services/AuthService';
import { useNavigate } from 'react-router-dom';
import { storeToken, saveLoggedInUser } from '../services/AuthService';

const Register = () => {
  const [formData, setFormData] = useState({
    firstname: '',
    lastname: '',
    username: '',
    email: '',
    password: '',
    phone: '',
    role: 'candidate',
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleRegistrationForm = (e) => {
    e.preventDefault();

    const register = {
      firstname: formData.firstname,
      lastname: formData.lastname,
      username: formData.username,
      email: formData.email,
      password: formData.password,
      phone: parseInt(formData.phone), // Convert phone to int
      role: formData.role,
    };

    console.log(register);

    registerAPICall(register)
      .then((response) => {
        console.log(response.data);

        // Store the JWT token
        const token = 'Bearer ' + response.data.accessToken;
        storeToken(token);

        saveLoggedInUser(formData.username, formData.role);
        navigate('/');
      })
      .catch((error) => {
        console.error(error);
      });
  };

  return (
    <div className='container'>
      <br /> <br />
      <div className='row'>
        <div className='col-md-6 offset-md-3'>
          <div className='card'>
            <div className='card-header'>
              <h2 className='text-center'> User Registration Form </h2>
            </div>

            <div className='card-body'>
              <form onSubmit={handleRegistrationForm}>
                <div className='row mb-3'>
                  <label className='col-md-3 control-label'> First Name </label>
                  <div className='col-md-9'>
                    <input
                      type='text'
                      name='firstname'
                      className='form-control'
                      placeholder='Enter first name'
                      value={formData.firstname}
                      onChange={handleChange}
                      required
                    />
                  </div>
                </div>

                <div className='row mb-3'>
                  <label className='col-md-3 control-label'> Last Name </label>
                  <div className='col-md-9'>
                    <input
                      type='text'
                      name='lastname'
                      className='form-control'
                      placeholder='Enter last name'
                      value={formData.lastname}
                      onChange={handleChange}
                      required
                    />
                  </div>
                </div>

                <div className='row mb-3'>
                  <label className='col-md-3 control-label'> Username </label>
                  <div className='col-md-9'>
                    <input
                      type='text'
                      name='username'
                      className='form-control'
                      placeholder='Enter username'
                      value={formData.username}
                      onChange={handleChange}
                      required
                    />
                  </div>
                </div>

                <div className='row mb-3'>
                  <label className='col-md-3 control-label'> Email </label>
                  <div className='col-md-9'>
                    <input
                      type='email'
                      name='email'
                      className='form-control'
                      placeholder='Enter email address'
                      value={formData.email}
                      onChange={handleChange}
                      required
                    />
                  </div>
                </div>

                <div className='row mb-3'>
                  <label className='col-md-3 control-label'> Password </label>
                  <div className='col-md-9'>
                    <input
                      type='password'
                      name='password'
                      className='form-control'
                      placeholder='Enter password'
                      value={formData.password}
                      onChange={handleChange}
                      required
                    />
                  </div>
                </div>

                <div className='row mb-3'>
                  <label className='col-md-3 control-label'> Phone </label>
                  <div className='col-md-9'>
                    <input
                      type='text'
                      name='phone'
                      className='form-control'
                      placeholder='Enter phone number'
                      value={formData.phone}
                      onChange={handleChange}
                      required
                    />
                  </div>
                </div>

                <div className='row mb-3'>
                  <label className='col-md-3 control-label'> Role </label>
                  <div className='col-md-9'>
                    <select
                      name='role'
                      className='form-control'
                      value={formData.role}
                      onChange={handleChange}
                      required
                    >
                      <option value='manager'>Manager</option>
                      <option value='candidate'>Candidate</option>
                    </select>
                  </div>
                </div>

                <div className='form-group mb-3'>
                  <button type='submit' className='btn btn-primary'>
                    Submit
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Register;
