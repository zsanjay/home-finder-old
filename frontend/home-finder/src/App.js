import { BrowserRouter , Routes, Route} from "react-router-dom";
import Home from "./components/home/home";
import './App.css';
import Login from "./components/login/login";
import SignUp from "./components/signup/SignUp";

function App() {
  return (
    <div>
      <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/signup" element={<SignUp/>}/>
        <Route path="/login" element={<Login/>}/>
      </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
