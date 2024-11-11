    async function login() {
        console.log("Función de inicio de sesión ejecutada"); // Comprobación
        const email = document.getElementById('loginEmail').value;
        const password = document.getElementById('loginPassword').value;

        const response = await fetch('/api/usuarios/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email, password })
        });

        if (response.ok) {
            const data = await response.json();
            console.log("Datos recibidos:", data); // Comprobación de datos
            if (data.rol === 'paciente') {
                window.location.href = `/perfil?id=${data.id}`;  
            } else if (data.rol === 'admin') {
                window.location.href = '/perfil?admin=true';
            }
        } else {
            alert('Credenciales inválidas');
        }
    }
