import { Box, Button, Stack, TextField, Typography } from '@mui/material'
import React, {useRef, useState } from 'react'
import LockOpenIcon from '@mui/icons-material/LockOpen';
import axiosClient from '../../services/authentication/axios-client';
import { useAuthContext } from '../../contexts/AuthContext';

const Login = () => {

   //success
   const [isSuccess,setIsSuccess] = useState(false);
   const [successMessage, setSuccessMessage] = useState('');
   //error
   const [isValid,setIsValid] = useState(true);
   const [errorMessage, setErrorMessage] = useState('');

   //inputs
   const userNameRef = useRef();
   const passwordRef = useRef();

   //user info
   const {setUser, setToken} = useAuthContext()

 
 
   const submitHandler = (e) =>{
     e.preventDefault();
 
     const payload = {
       username: userNameRef.current.value,
       password: passwordRef.current.value,  
     };
     console.log(payload)
     setErrorMessage('')
     axiosClient.post(`${process.env.REACT_APP_AUTH_BASE_URL}/login` ,payload)
     .then(response =>{
        const data = response.data
        setUser(data.userInfo)
        setToken(data.accsess_token)
        

     })
     .catch(err =>{
      console.log(err);
     })
 
     
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
      <Typography variant="h5" gutterBottom sx={{fontWeight:'bold'}}>Login</Typography>
      {isValid ? '' : <Typography component='p' gutterBottom sx={{fontWeight:'bold',fontSize:'1em',color:'red'}}>{errorMessage}</Typography>}
      {!isSuccess ? '' : <Typography component='p' gutterBottom sx={{fontWeight:'bold',fontSize:'1em',color:'green'}}>{successMessage}</Typography>}
      <Stack spacing={3} p={2}>
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
         
      </Stack>
      <Button type="submit" variant="contained" color='success' sx={{width:'60%',marginTop:'10%'}}>Sing In</Button>
    </Box>
  )
}

export default Login
