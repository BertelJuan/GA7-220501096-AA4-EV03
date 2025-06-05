import { useState } from "react";
import axios from "axios";
import "./FormularioRegistro.css"

function FormularioRegistro() {
    const [usuario, setUsuario] = useState({
        nombre: "",
        correo: "",
        contraseña: "",
        rol: ""
    });

    const [mensaje, setMensaje] = useState("");

    const handleChange = (e) => {
        setUsuario({ ...usuario, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        if (!usuario.correo.includes("@") || !usuario.correo.includes(".")) {
            setMensaje("❌ El correo ingresado no es valido.");
            return;
        }

        if (usuario.contraseña.length < 6) {
            setMensaje("❌ La contraseña debe tener al menos 6 caracteres.");
            return;
        }
        e.preventDefault();

        try {
            const respuesta = await axios.post("http://localhost:8080/api/usuarios/registro", usuario);
            if (respuesta.status === 200 || respuesta.status === 201) {
                window.location.href = "/confirmacion";
                setUsuario({ nombre: "", correo: "", contraseña: "", rol: "" });
            }
        } catch (error) {
            setMensaje("❌ Error al registrar usuario.");
            console.error(error);
        }
    };

    return (
        <div className="form-container">
            <h2>Registro de Usuario</h2>
            {mensaje && <p className="mensaje">{mensaje}</p>}

            <form onSubmit={handleSubmit}>
                <label>Nombre:</label>
                <input
                    type="text"
                    name="nombre"
                    value={usuario.nombre}
                    onChange={handleChange}
                    required
                />

                <label>Correo:</label>
                <input
                    type="email"
                    name="correo"
                    value={usuario.correo}
                    onChange={handleChange}
                    required
                />

                <label>Contraseña:</label>
                <input
                    type="password"
                    name="contraseña"
                    value={usuario.contraseña}
                    onChange={handleChange}
                    required
                />

                <label>Rol:</label>
                <select
                    name="rol"
                    value={usuario.rol}
                    onChange={handleChange}
                    required
                >
                    <option value="">Seleccione un rol</option>
                    <option value="ADMIN">Administrador</option>
                    <option value="DOCENTE">Docente</option>
                    <option value="ESTUDIANTE">Estudiante</option>
                </select>

                <br />
                <button type="submit" style={{ marginTop: "1rem" }}>
                    Registrar
                </button>
            </form>
        </div>
    );
}

export default FormularioRegistro;
