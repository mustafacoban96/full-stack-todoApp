// import axios from "axios";

// //https://dev.to/mihaiandrei97/jwt-authentication-using-axios-interceptors-55be
// //https://www.toptal.com/java/rest-security-with-jwt-spring-security-and-java
// export function createAxiosClient({
//     options,
//     getCurrentAccessToken,
//     getCurrentRefreshToken,
//     refreshTokenUrl,
//     logout,
//     setRefreshedTokens,
// }){
//     const client = axios.create(options);

//     client.interceptors.request.use(
//         (config) => {
//             if(config.authorization !== false){
//                 const token = getCurrentAccessToken();
//                 if(token){
//                     config.headers.Authorization = "Bearer " + token;
//                 }
//             }
//             return config;
//         },
//         (error) => {
//             return Promise.reject(error);
//         }
//     );
//     return client;
// }