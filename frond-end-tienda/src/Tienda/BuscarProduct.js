import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function BuscarProductoInventario() {

    const [hawa, setHawa] = useState('');
    const [producto, setProducto] = useState(null);
    const [error, setError] = useState('');

    const navigate = useNavigate();

    const buscarProducto = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/ApiRestTienda/buscar/${hawa}`);
            setProducto(response.data);
            setError('');
        } catch (err) {
            setProducto(null);
            setError('Producto no encontrado o error en la búsqueda');
        }
    }

    const redirigirAgregarProducto = () => {
        navigate(`/agregar?hawa=${producto.camioneta.hawa}`);
    }

    return (
        <div className="container mt-4">
            <h3>Buscar Producto en Inventario</h3>

            <div className="mb-3">
                <input 
                    type="text" 
                    className="form-control" 
                    placeholder="Ingrese HAWA o Código del producto"
                    value={hawa}
                    onChange={(e) => setHawa(e.target.value)}
                />
            </div>

            <button onClick={buscarProducto} className="btn btn-primary mb-3">Buscar</button>

            {error && <div className="alert alert-danger">{error}</div>}

            {producto && (
                <div>
                    <table className="table table-bordered">
                        <thead>
                            <tr>
                                <th>HAWA</th>
                                <th>Existencias</th>
                                <th>Modelo</th>
                                <th>Precio</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>{producto.camioneta?.hawa}</td>
                                <td>{producto.existencias}</td>
                                <td>{producto.camioneta?.nombreModelo}</td>
                                <td>${producto.camioneta?.precioLista.toLocaleString()}</td>
                            </tr>
                        </tbody>
                    </table>

                    <button 
                        className="btn btn-success"
                        onClick={redirigirAgregarProducto}
                        disabled={producto.existencias === 0}
                    >
                        {producto.existencias === 0 ? 'Sin Stock Disponible' : 'Agregar Producto'}
                    </button>

                    {producto.existencias === 0 && 
                        <div className="alert alert-warning mt-2">
                            No hay existencias disponibles, no se puede agregar.
                        </div>
                    }
                </div>
            )}
        </div>
    );
}