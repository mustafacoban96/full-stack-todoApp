import React, { useState } from 'react'


const darkLightMode = (params) =>{
    const myBody = document.body;

    if(params){
        myBody.style.backgroundColor = 'lightpink'
    }
    else{
        myBody.style.backgroundColor = 'white'
    }

}

const LambButton = () => {
    const [mode, setMode] = useState(false);
    darkLightMode(mode);
  return (
    <button onClick={() => setMode((prevMode) => !prevMode)}>Lamb button</button>
  )
}

export default LambButton
