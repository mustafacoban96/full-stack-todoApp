import React from 'react'
import { Navigate, Outlet } from 'react-router'
import { useAuthContext } from '../../contexts/AuthContext'
import MyNavbar from '../Navbar/MyNavbar';

const DefaultLayout = () => {

  const {user,token} = useAuthContext();
  
  

  if(!token){
    return <Navigate to='register'/>
  }

  const onLogout = (e) =>{
    e.preventDefault();
  }

  return (
    
    <div>
      <MyNavbar/>
      <Outlet/>
    </div>
  )
}

export default DefaultLayout
