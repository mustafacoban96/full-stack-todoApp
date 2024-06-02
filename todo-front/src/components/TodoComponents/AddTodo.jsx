import { Box, Container, IconButton, Stack, TextField, Typography } from '@mui/material'
import AddIcon from '@mui/icons-material/Add';
import React, {useRef, useState } from 'react'
import { useAuthContext } from '../../contexts/AuthContext';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const AddTodo = () => {
    const {user,token} = useAuthContext();
    const [payload, setPayload] = useState({ content: '' });
    //success
    //error
    const [isValid,setIsValid] = useState(true);

    
    const submitHandler = (e) =>{
        e.preventDefault();

        if (!user || !user.userId) {
            console.log('User is not authenticated or userId is missing');
            return;
          }

        axios
        .post(`${process.env.REACT_APP_TODO_URL}/${parseInt(user.userId)}/createTodo`, payload,{
            headers:{
                'Authorization': `Bearer ${token}`
            }
        })
        .then(() => {
            setPayload({content: ''})
            toast.success('New activity is added successfully')
        })
        .catch((err) =>{
            //toast error
        let res = err.response.data.errors.filter(message => message.trim() !== "");
        console.log(err)
        setIsValid(false);
        if(res.length > 0){
            toast.error(res[0])
        }
        
        });

    }
    
    
  return (
    <Box 
    sx={{m:2}}
    component="form" 
    onSubmit={submitHandler}
    >
       <Container>
        <Stack direction="row" spacing={2} sx={{justifyContent:'center'}}>
            
            <TextField 
            sx={{width:'60%'}} 
            label="Add activity" 
            value={payload.content}
            id="fullWidth"
            onChange={(e) => setPayload({...payload, content: e.target.value})}
            
             />
                <IconButton aria-label="add" size='large' type='submit'>
                    <AddIcon fontSize='large' sx={{color:'green'}}/>
                </IconButton>
        </Stack>
       </Container>
       <ToastContainer position="bottom-right" autoClose={3000} />
    </Box>
  )
}

export default AddTodo
