import axios from "axios";


const auth_api = axios.create({
    baseAuthURL:"http://localhost:8080/auth", // our AUTH API base URL
});


auth_api.interceptors.request.use(
    (config) =>{
        const token = localStorage.getItem('token'); // you store the token in localStorage
        if(token){
            config.headers.Authorization = `Bearer ${token}`
        }
        return config;
    },
    (error) =>{
        return Promise.reject(error);
    }
)



// endpoints
export const register = () =>{
    return auth_api.post('/register');
}

export const login = () =>{
    return auth_api.post('/login');
}


export default auth_api;