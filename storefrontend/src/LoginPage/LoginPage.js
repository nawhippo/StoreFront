import React, { useState } from 'react';
import style from "./loginPageStyle.css"
const Login = () => {
const [message, setMessage] = useState('');
const [formData, setFormData] = useState(new formData());
const [responseData, setResponseDate] = useState('');
const handleLogin = (event) => {
    event.preventDefault();
    fetch("/login", {
        method:'POST',
        body: formData
})
    .then(response => {
        setResponseDate(responseData)
        setUserState(username, password)
        setMessage("Login Successful")
    })
    .catch(error => {
        setMessage("Something went wrong while trying to log in");
        console.error("Error:", error);
    });
}

return (
<div>
<form onSubmit={handleLogin}>
    <input type="text" placeholder="Login" />
    <input type="password" placeholder="Password" />
    <button type="submit">Login</button>
</form>
message ? <div> ${message} </div> : <div>
</div>
</div>
);

}
export default Login;