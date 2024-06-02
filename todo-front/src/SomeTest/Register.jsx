import React from 'react'

const Register = () => {
  return (
    <div className='login'>
    <h1>Register Page</h1>
    <div className='form-group'>
      <label htmlFor="username">Username: </label>
      <input type="text" name='username' id='username'/>
    </div>
    <div className='form-group'>
      <label htmlFor="password">Password:</label>
      <input type="password" name='password' id='password'/>
    </div>
    <input type="submit" value='login' style={{ marginTop: '10px', width:'20%' }} />
  </div>
  )
}

export default Register
