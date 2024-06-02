import React from 'react'
import { Navigate, Outlet } from 'react-router'
import { useAuthContext } from '../../contexts/AuthContext'

const GuestLayout = () => {

  const {token} = useAuthContext();

  if(token){
    return <Navigate to='home' />
  }

  return (
    <div>
      <Outlet/>
    </div>
  )
}

export default GuestLayout
