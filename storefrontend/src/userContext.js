import { Children, useState } from 'react';
import React, { createContext, useContext, useState } from 'react'

const userContext = createContext(null);
//created at login
const createUserContext = (username, jwt) => {
    userContext = React.createContext(null)
    setUsername(username)
    setjwt(jwt);
} 



export const UserProvider =  ({ children }) => {
    const [username, setUsername] = useState('');
    const [jwt, setJwt] = useState('');


    const setUserContext = (username, jwt) => {
    setUsername(username);
    setJwt(jwt);
}

    return (
        <userContext.provider userContext={{username, jwt, setUserContext}}>
            {children}
        </userContext.provider>
    )
}

export const useUserContext = () => {
    return useContext(UserContext);
};