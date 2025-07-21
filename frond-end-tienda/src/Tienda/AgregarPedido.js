
import React, { useState, useEffect } from 'react';
import { useSearchParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

export default function AgregarPedido() {
 let navegacion = useNavigate();

    const [searchParams] = useSearchParams();
    const [hawa, setHawa] = useState('');
    const [producto, setProducto] = useState(null);
    const [cantidad, setCantidad] = useState(1);
    const [pedido, setPedido] = useState({
        fechaPedido: '',
        idCliente: '',
        idVendedor: '',
        idTienda: '',
        descuentoGlobal: 0,
        estatus: 'pendiente'
    });
   
    useEffect(() => {
        const hawaParam = searchParams.get('hawa');
        if (hawaParam) {
            setHawa(hawaParam);
            buscarProducto(hawaParam);
        }
    }, [searchParams]);

    const buscarProducto = async (hawaParam) => {
        try {
            const response = await axios.get(`http://localhost:8080/ApiRestTienda/buscar/${hawaParam}`);
            setProducto(response.data);
        } catch (err) {
            alert('Error al buscar producto');
        }
    }

   const guardarPedidoYDetalle = async () => {
    try {
        // Armamos el objeto correctamente
        const pedidoRequest = {
            fechaPedido: pedido.fechaPedido,
            cliente: { idCliente: parseInt(pedido.idCliente) },
            vendedor: { idVendedor: parseInt(pedido.idVendedor) },
            tienda: { idTienda: parseInt(pedido.idTienda) },
            descuentoGlobal: parseFloat(pedido.descuentoGlobal),
            estatus: pedido.estatus
        };

        const pedidoResponse = await axios.post('http://localhost:8080/ApiRestTienda/guardar', pedidoRequest);
        const pedidoGuardado = pedidoResponse.data;

        const detallePedido = {
            camioneta: producto.camioneta,
            cantidad: parseInt(cantidad),
            precioUnitario: producto.camioneta.precioLista,
            descuentoProducto: producto.camioneta.descuento,
            pedido: pedidoGuardado
        };

        await axios.post('http://localhost:8080/ApiRestTienda/guardarD', detallePedido);

        alert('Pedido y detalle guardados correctamente');

            
    } catch (err) {
        console.error(err);
        alert('Error al guardar');
    }
    finally{
        navegacion('/');
    }
}
    return (
        <div className="container mt-4">
            <h3>Agregar Producto al Pedido</h3>

            <div className="mb-3">
                <label>HAWA</label>
                <input 
                    type="text" 
                    className="form-control" 
                    value={hawa}
                    readOnly
                />
            </div>

            {producto && (
                <div className="mb-4">
                    <p><strong>Modelo:</strong> {producto.camioneta.nombreModelo}</p>
                    <p><strong>Precio:</strong> ${producto.camioneta.precioLista.toLocaleString()}</p>
                  

                    <label>Cantidad a Comprar</label>
                    <input 
                        type="number" 
                        className="form-control mb-3" 
                        value={cantidad}
                        min={1}
                        max={producto.existencias}
                        onChange={(e) => setCantidad(e.target.value)}
                    />
                </div>
            )}

            <h5>Datos del Pedido</h5>

            <div className="mb-3">
                <label>Fecha Pedido</label>
                <input 
                    type="datetime-local" 
                    className="form-control" 
                    value={pedido.fechaPedido}
                    onChange={(e) => setPedido({ ...pedido, fechaPedido: e.target.value })}
                />
            </div>

            <div className="mb-3">
                <label>ID Cliente</label>
                <input 
                    type="number" 
                    className="form-control" 
                    value={pedido.idCliente}
                    onChange={(e) => setPedido({ ...pedido, idCliente: e.target.value })}
                />
            </div>

            <div className="mb-3">
                <label>ID Vendedor</label>
                <input 
                    type="number" 
                    className="form-control" 
                    value={pedido.idVendedor}
                    onChange={(e) => setPedido({ ...pedido, idVendedor: e.target.value })}
                />
            </div>

            <div className="mb-3">
                <label>ID Tienda</label>
                <input 
                    type="number" 
                    className="form-control" 
                    value={pedido.idTienda}
                    onChange={(e) => setPedido({ ...pedido, idTienda: e.target.value })}
                />
            </div>

            <div className="mb-3">
                <label>Descuento Global</label>
                <input 
                    type="number" 
                    className="form-control" 
                    value={pedido.descuentoGlobal}
                    onChange={(e) => setPedido({ ...pedido, descuentoGlobal: e.target.value })}
                />
            </div>

            <button 
                className="btn btn-success"
                onClick={guardarPedidoYDetalle}
                disabled={!producto || cantidad > producto.existencias}
            >
                Confirmar Pedido
            </button>
        </div>
    );
}
