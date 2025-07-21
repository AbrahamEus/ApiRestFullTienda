import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

export default function Login() {
     let navegacion = useNavigate();

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [mensaje, setMensaje] = useState('');

    const iniciarSesion = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/login', null, {
                params: {
                    username: username,
                    password: password
                }
            });

            if (response.data.token) {
                localStorage.setItem('token', response.data.token);
                setMensaje('Inicio de sesión exitoso');
                navegacion('/listar')

            } else if (response.data.error) {
                setMensaje(response.data.error);
            }
        } catch (error) {
            console.error(error);
            setMensaje('Error al conectarse al servidor');
        }
    };

    return (
        <div className="login-container">
            <h2>Iniciar Sesión</h2>
            <form onSubmit={iniciarSesion}>
                <input
                    type="text"
                    placeholder="Usuario"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
                <input
                    type="password"
                    placeholder="Contraseña"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <button type="submit">Ingresar</button>
            </form>
            <p>{mensaje}</p>
        </div>
    );
}