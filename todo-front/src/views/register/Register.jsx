import { Box, Button, Stack, TextField, Typography } from '@mui/material'
import React, {useRef, useState } from 'react'
import LockOpenIcon from '@mui/icons-material/LockOpen';
import axios from 'axios';
import { useNavigate } from 'react-router';


const Register = () => {
  //success
  const [isSuccess,setIsSuccess] = useState(false);
  const [successMessage, setSuccessMessage] = useState('');
  //error
  const [isValid,setIsValid] = useState(true);
  const [errorMessage, setErrorMessage] = useState('');

  //navigate login after register
  const navigate = useNavigate()

  const nameRef = useRef();
  const userNameRef = useRef();
  const passwordRef = useRef();
  const confirmPasswordRef = useRef();


  const submitHandler = (e) =>{
    e.preventDefault();

    const payload = {
      name: nameRef.current.value,
      username: userNameRef.current.value,
      password: passwordRef.current.value,
      confirm_password: confirmPasswordRef.current.value,
      authorities:["ROLE_USER"]
    }

    axios
    .post(`${process.env.REACT_APP_AUTH_BASE_URL}/register`, payload)
    .then((response) => {
      setSuccessMessage(response.data);
      setIsSuccess(true);

      setTimeout(() =>{
        navigate('/login')
      },3000)
    })
    .catch((err) =>{
      
      let res = err.response
      console.log(err)
      setErrorMessage(res.data);
      setIsValid(false);
    });

  }

  

  return (
  <Box sx={{
    width:'38%',
    marginX:'31%',
    marginTop:'7%',
    borderRadius:'3px',
    padding:'12px',
    border:'2px solid green',
    textAlign:'center',
    }}
    component='form'
    onSubmit={submitHandler}
    >
    <LockOpenIcon color='success' fontSize='large' />
    <Typography variant="h5" gutterBottom sx={{fontWeight:'bold'}}>Register</Typography>
    {isValid ? '' : <Typography component='p' gutterBottom sx={{fontWeight:'bold',fontSize:'1em',color:'red'}}>{errorMessage}</Typography>}
    {!isSuccess ? '' : <Typography component='p' gutterBottom sx={{fontWeight:'bold',fontSize:'1em',color:'green'}}>{successMessage}</Typography>}
    <Stack spacing={3} p={2}>
    <TextField
            inputRef={nameRef}
            id="outlined-multiline-flexible"
            label="Name"
            multiline
            maxRows={4}
            
          />
      <TextField
            inputRef={userNameRef}
            id="outlined-multiline-flexible"
            label="Username"
            multiline
            maxRows={4}
          />
         <TextField
          inputRef={passwordRef}
          id="outlined-multiline-flexible"
          label="Password"
          multiline
          maxRows={4}
        />
        <TextField
          inputRef={confirmPasswordRef}
          id="outlined-multiline-flexible"
          label="Confirm Password"
          multiline
          maxRows={4}
        />
    </Stack>
    <Button type="submit" variant="contained" color='success' sx={{width:'60%',marginTop:'10%'}}>Sing Up</Button>
  </Box>
  )
}

export default Register
