
document.querySelector("form").addEventListener("submit", async (event) => {
    event.preventDefault();

    const email = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const response = await fetch('/api/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ email, password })
    });

    if (response.ok) {
        const usuario = await response.json();
        alert(`Bienvenido ${usuario.nombreUsuario}!`);

        // Redirigir según el rol
        if (usuario.rol.nombreRol === 'admin') {
            window.location.href = '/admin/dashboard.html';
        } else if (usuario.rol.nombreRol === 'paciente') {
            window.location.href = '/paciente/perfil.html';
        }
    } else {
        alert('Credenciales incorrectas');
    }
});
