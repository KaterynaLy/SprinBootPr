
async function login() {
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    const response = await fetch('/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password }),
    });

    if (response.ok) {
        const usuario = await response.json();
        localStorage.setItem('usuario', JSON.stringify(usuario)); // Guarda los datos del usuario

        // Verifica el rol y redirige al perfil adecuado
        if (usuario.idRol === 1) { // Si es Admin
            showAdminProfile(); // Muestra el perfil del administrador
        } else {
            showPatientProfile(); // Muestra el perfil del paciente
        }
    } else {
        alert('Error al iniciar sesión. Verifica tus credenciales.');
    }
}

