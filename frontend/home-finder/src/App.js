import { BrowserRouter , Routes, Route} from "react-router-dom";
import Home from "./components/home/home";
import './App.css';
import Register from "./components/register/register";
import Login from "./components/login/login";

function App() {
  return (
    <div>
      <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/register" element={<Register/>}/>
        <Route path="/login" element={<Login/>}/>
      </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
