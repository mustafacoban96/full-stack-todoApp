import { Navigate, createBrowserRouter } from "react-router-dom";
import Login from "./views/login/Login";
import Register from "./views/register/Register";
import NotFound from "./views/notFound/NotFound";
import Home from "./views/home/Home";
import DefaultLayout from "./components/DefaultLayout/DefaultLayout";
import GuestLayout from "./components/GuestLayout/GuestLayout";
import Dashboard from "./views/dashboard/Dashboard";




const router = createBrowserRouter([
   {
    path:'/',
    element: <DefaultLayout/>,
    children: [

            {
                path:'/',
                element:<Navigate to='/home'/>
            },

            {
                path:'/dashboard',
                element:<Dashboard/>
            },
            {
                path:'home',
                element:<Home/>
            }
        ]
   },

   {
    path:'/',
    element: <GuestLayout/>,
    children: [
        {
            path: 'login',
            element: <Login/>
        },
        {
            path: 'register',
            element: <Register/>
        }
    ]
   },
   {
    path: '*',
    element:<NotFound/>
   }

])

export default router