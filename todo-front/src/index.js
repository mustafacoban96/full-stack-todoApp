import React from 'react';
import ReactDOM from 'react-dom/client';
//import App from './App';
import { RouterProvider } from 'react-router-dom';
import router from "./router";
import { AuthProvider } from './contexts/AuthContext';
import './App.css'
import { CssBaseline, createTheme } from '@mui/material';
import { ThemeProvider } from '@emotion/react';
import { DarkModeProvider } from './contexts/DarkModeContext';

const darkTheme = createTheme({
  palette:{
    mode:'dark',
  }
})



const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  //<React.StrictMode>
    //<App />
  //</React.StrictMode>

  <React.StrictMode>
    <DarkModeProvider>
      <AuthProvider>
        <RouterProvider router={router}/>
      </AuthProvider>
    </DarkModeProvider>
  </React.StrictMode>
);

