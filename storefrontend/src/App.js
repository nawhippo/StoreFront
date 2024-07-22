import './App.css';
import { useState } from 'react';
import ProductPage from "../ProductPage/ProductPage.js";
import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom';
import 'bootstrap/dist/css/boostrap.min.css';

function App = () => {
  const [userState, setUserState] = useState(null)
  const userContext = useUserContext();
  url = "localhost:8080/"
  return (
    <Router>
    <div className="App">
      <nav className="navbar" >
     <li className="nav-item">
        <a className="nav-link" href={`${url}/Products`}>Products</a>
      </li> 
      userContext ? (
      <li className="nav-item">
        <a className="nav-link" href={`${url}/Account`}>Account</a>
      </li>
      ) : (
        null
      )

      <li class="nav-item">
        <a class="nav-link" href="#">Orders</a>
      </li>
      </nav>
      <Switch>
        <Route exact path="/" component={Home} />
        <Route path="/Products" component={ProductPage} />
        <Route path="/contact" component={Contact} />
      </Switch>
    </div>
</Router>
);
}

export default App;
