async function fetchPatientProfile() {
    const mainContent = document.getElementById('main-content');
    const patientId = document.getElementById('patient-id').value; // Obtiene el ID del paciente del campo de entrada

    if (!patientId) {
        mainContent.innerHTML = `<p>Por favor, introduce un ID de paciente válido.</p>`;
        return;
    }

    try {
        // Consultar datos del paciente
        const pacienteResponse = await fetch(`/api/paciente/${patientId}/perfil`);
        if (!pacienteResponse.ok) throw new Error("Error al cargar los datos del paciente.");
        const paciente = await pacienteResponse.json();

        // Consultar tratamientos
        const tratamientosResponse = await fetch(`/api/paciente/${patientId}/tratamientos`);
        if (!tratamientosResponse.ok) throw new Error("Error al cargar los tratamientos del paciente.");
        const tratamientos = await tratamientosResponse.json();

        // Consultar documentos
        const documentosResponse = await fetch(`/api/paciente/${patientId}/documentos`);
        if (!documentosResponse.ok) throw new Error("Error al cargar los documentos del paciente.");
        const documentos = await documentosResponse.json();

        // Consultar presupuesto
        const presupuestosResponse = await fetch(`/api/paciente/${patientId}/presupuestos`);
        if (!presupuestosResponse.ok) throw new Error("Error al cargar los presupuestos del paciente.");
        const presupuestos = await presupuestosResponse.json();

        // Renderizar el contenido
        mainContent.innerHTML = `
            <div class="profile">
                <h2>Perfil Paciente</h2>
                <p>Bienvenido, <strong>${paciente.nombre} ${paciente.apellido}</strong>. Estás en tu perfil de la clínica dental.</p>
                
                <h3>Datos de Usuario</h3>
                <table class="table">
                    <tr><th>Email</th><td>${paciente.email}</td></tr>
                    <tr><th>Nombre</th><td>${paciente.nombre}</td></tr>
                    <tr><th>Teléfono</th><td>${paciente.telefono}</td></tr>
                </table>
                
                <h3>Tratamientos</h3>
                <table class="table">
                    <tr><th>Nombre del Tratamiento</th><th>Estado</th></tr>
                    ${tratamientos.map(tratamiento => `
                        <tr>
                            <td>${tratamiento.nombre}</td>
                            <td>${tratamiento.aprobado ? 'Aprobado' : 'Pendiente'}</td>
                        </tr>`).join('')}
                </table>

                <h3>Presupuesto</h3>
                <ul>
                    ${presupuestos.map(presupuesto => `
                        <li>${presupuesto.descripcion} - $${presupuesto.monto} (${presupuesto.estado})</li>
                    `).join('')}
                </ul>

                <h3>Documentos</h3>
                <ul>
                    ${documentos.map(documento => `
                        <li>${documento.nombreDocumento} - ${documento.firmado ? 'Firmado' : 'No firmado'}</li>
                    `).join('')}
                </ul>
            </div>
        `;
    } catch (error) {
        console.error('Error al cargar el perfil del paciente:', error);
        mainContent.innerHTML = `<p>${error.message}</p>`;
    }
}


function showAdminProfile() {
    const mainContent = document.getElementById('main-content');
    mainContent.innerHTML = `
        <div class="profile">
            <h2>Perfil Administrador</h2>
            <p>Bienvenido, <strong>[Nombre del Administrador]</strong>. Estás en tu perfil de la clínica dental.</p>
            <h3>Caja</h3>
            <p>[Detalles de Caja]</p>
            <h3>Lista de Usuarios</h3>
            <table class="table">
                <tr><th>Nombre</th><th>Email</th><th>Acciones</th></tr>
                <tr>
                    <td>[Nombre del Usuario]</td>
                    <td>[email@ejemplo.com]</td>
                    <td><button>Dar Baja</button> <button>Dar Alta</button></td>
                </tr>
            </table>
            <h3>Documentos</h3>
            <ul>
                <li><a href="#">Documento 1</a></li>
                <li><a href="#">Documento 2</a></li>
            </ul>
            <h3>Financiación</h3>
            <p>[Detalles de Financiación]</p>
            <h3>Presupuesto</h3>
            <p>[Detalles del Presupuesto]</p>
            <h3>Tratamientos</h3>
            <table class="table">
                <tr><th>Nombre del Tratamiento</th><th>Detalles</th></tr>
                <tr><td>Tratamiento 1</td><td>[Detalles]</td></tr>
            </table>
        </div>
    `;
}