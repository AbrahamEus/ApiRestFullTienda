import {BrowserRouter, Route, Routes} from "react-router-dom";
import ListProduct from "./Tienda/ListProduct";
import Nav from "./Plantilla/Nav";
import BuscarProduct from "./Tienda/BuscarProduct";
import AgregarPedido from "./Tienda/AgregarPedido";
function App() {
  return (
    <div className="container">
        <BrowserRouter>
   <Nav/>
    <Routes>
      <Route exact path = "/" element = {<ListProduct/>}/>
      <Route exact path = "/buscar" element = {<BuscarProduct/>}/>
      <Route exact path = "/agregar" element = {<AgregarPedido/>}/>
    </Routes>
  
   </BrowserRouter>

    </div>
 
  );
}

export default App;
