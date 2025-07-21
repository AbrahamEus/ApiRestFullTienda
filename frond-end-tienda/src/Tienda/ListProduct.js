import axios from 'axios';
import React, { useEffect, useState } from 'react';

export default function ListProduct() {

    const urlPedido = "http://localhost:8080/ApiRestTienda/mostrarD";
    const urlCambiarEstatus = "http://localhost:8080/ApiRestTienda/editarEstatus";

    const [detallePedido, setDetallePedido] = useState(null);

    useEffect(() => {
        cargardetallePedidos();
    }, []);

    const cargardetallePedidos = async () => {
        try {
            const result = await axios.get(urlPedido);
            setDetallePedido(result.data);
        } catch (error) {
            console.error("Error al cargar el pedido", error);
        }
    }

    const cambiarEstatus = async (idPedido, nuevoEstatus) => {
        try {
            await axios.put(`${urlCambiarEstatus}/${idPedido}?nuevoEstatus=${nuevoEstatus}`);
            alert("Estatus actualizado correctamente");
            cargardetallePedidos(); // refrescar la tabla
        } catch (error) {
            console.error(error);
            alert(error.response?.data || "Error al cambiar el estatus");
        }
    }

    if (!detallePedido) {
        return <p>Cargando pedido...</p>;
    }

    return (
        <div className='container'>
            <div className='container text-center' style={{ margin: "30px" }}>
                <h3>Listado de Pedidos</h3>
            </div>
            <table className="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>ID Detalle</th>
                        <th>Fecha Pedido</th>
                        <th>Cliente</th>
                        <th>Teléfono</th>
                        <th>Vendedor</th>
                        <th>Tienda</th>
                        <th>Modelo</th>
                        <th>HAWA</th>
                        <th>Cantidad</th>
                        <th>Precio Unitario</th>
                        <th>Descuento</th>
                        <th>Estatus</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    {detallePedido.map((detalle, index) => {
                        const estatus = detalle.pedido.estatus;
                        return (
                            <tr key={index}>
                                <td>{detalle.idDetalle}</td>
                                <td>{detalle.pedido.fechaPedido}</td>
                                <td>{detalle.pedido.cliente.nombre}</td>
                                <td>{detalle.pedido.cliente.telefono}</td>
                                <td>{detalle.pedido.vendedor.nombre}</td>
                                <td>{detalle.pedido.tienda.nombre}</td>
                                <td>{detalle.camioneta.nombreModelo}</td>
                                <td>{detalle.camioneta.hawa}</td>
                                <td>{detalle.cantidad}</td>
                                <td>{detalle.precioUnitario}</td>
                                <td>{detalle.descuentoProducto}</td>
                                <td>{estatus}</td>
                                <td>
                                    {estatus === "pendiente" && (
                                        <>
                                            <button className="btn btn-success btn-sm m-1" onClick={() => cambiarEstatus(detalle.pedido.idPedido, "entregado")}>
                                                Entregar
                                            </button>
                                            <button className="btn btn-danger btn-sm m-1" onClick={() => cambiarEstatus(detalle.pedido.idPedido, "cancelado")}>
                                                Cancelar
                                            </button>
                                        </>
                                    )}
                                </td>
                            </tr>
                        )
                    })}
                </tbody>
            </table>
        </div>
    );
}
