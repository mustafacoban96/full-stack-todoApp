import React from 'react'


// fetch type 1
function getToken() {
  let loginUrl = "http://localhost:8080/todo/auth/login";
  let xhr = new XMLHttpRequest();
  let userElement = document.getElementById('username');
  let passwordElement = document.getElementById('password');
  let tokenElement = document.getElementById('token-area');
  let user = userElement.value;
  let password = passwordElement.value;

  xhr.open('POST',loginUrl,true);
  xhr.setRequestHeader('Content-Type','application/json; charset=UTF-8');
  xhr.addEventListener('load', () => {
      if(xhr.readyState === 4){
        var responseObject = JSON.parse(xhr.response);
        console.log(responseObject.accessToken);
        tokenElement.innerHTML   = responseObject.accessToken;
      }
      else{
        tokenElement.innerHTML = "No token received"
      } 
  });
  let sendObject = JSON.stringify({username: user, password: password});
  console.log('going to send', sendObject);
  xhr.send(sendObject);
}

//fetch type 2
const getTokenv2 = async () =>{

  let loginUrl = "http://localhost:8080/auth/login";
  let userElement = document.getElementById('username');
  let passwordElement = document.getElementById('password');
  let tokenElement = document.getElementById('token-area');
  let user = userElement.value;

  let password = passwordElement.value;

  console.log('username: ' +  user,'password: ' + password)

  try{
    const response = await fetch(loginUrl,{
      method:'POST',
      headers:{
        'Content-Type' : 'application/json',
      },
      body : JSON.stringify({
        username: user,
        password:password    
      })
    });
    if(!response.ok){
      throw new Error(`Error! status: ${response.status}`)
    }

    const result = await response.json();
    console.log(result)
    tokenElement.innerHTML = result.accsess_token;
;
  }catch(err){
    console.log(err)
    tokenElement.innerHTML = 'username or password is not correct';
  }
  

}

const Login = () => {
  return (
    <>
    <div className='login'>
      <h1>Login Page</h1>
      <div className='form-group'>
        <label htmlFor="username">Username: </label>
        <input type="text" name='username' id='username'/>
      </div>
      <div className='form-group'>
        <label htmlFor="password">Password:</label>
        <input type="password" name='password' id='password'/>
      </div>
      <input type="submit" value='login' onClick={() => getTokenv2()} style={{ marginTop: '10px', width:'20%' }} />
    </div>

    <div id='token-area'>
      <h3>token area</h3>

    </div>
    </>
  )
}

export default Login
