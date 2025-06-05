import './App.css'
import FormularioRegistro from "./components/FormularioRegistro.jsx";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import ConfirmacionRegistro from "./components/ConfirmacionRegistro";

function App() {

  return (
    <>
        <Router>
            <Routes>
                <Route path="/" element={<FormularioRegistro />} />
                <Route path="/confirmacion" element={<ConfirmacionRegistro />} />
            </Routes>
        </Router>
    </>
  )
}

export default App
