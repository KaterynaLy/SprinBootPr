package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import java.util.Locale;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class AuthServiceIntegrationTests {

    @Autowired
    private AuthService authService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    private static final String TEST_UUID = UUID.randomUUID().toString().toLowerCase(Locale.ROOT);
    private static final String TEST_USERNAME = AuthServiceIntegrationTests.class.getSimpleName().toLowerCase() + "." + TEST_UUID;
    private static final String TEST_EMAIL = TEST_USERNAME + "@example.com";
    private static final String TEST_PASSWORD = UUID.randomUUID().toString();

    private static final String TEST_ROL = TEST_UUID;
    @BeforeEach
    public void setUp() {

        Rol rol = new Rol(TEST_ROL);
        rolRepository.save(rol);

        usuarioRepository.deleteByEmail(TEST_EMAIL);

        Usuario usuario = new Usuario();
        usuario.setEmail(TEST_EMAIL);
        usuario.setPassword(TEST_PASSWORD);
        usuario.setNombreUsuario(TEST_USERNAME);
        usuario.setRol(rol);
        usuarioRepository.save(usuario);
    }

    @AfterEach
    public void cleanUp() {
        usuarioRepository.deleteByEmail(TEST_EMAIL);
        rolRepository.deleteByNombreRol(TEST_ROL);
    }

    @Test
    public void testAutenticarUsuarioSuccess() throws AuthenticationException {
        Usuario result = authService.autenticarUsuario(TEST_EMAIL, TEST_PASSWORD);

        assertNotNull(result);
        assertEquals(TEST_EMAIL, result.getEmail());
        assertEquals(TEST_USERNAME, result.getNombreUsuario());
    }

    @Test
    public void testAutenticarUsuarioInvalidCredentials() {
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authService.autenticarUsuario("wrong@example.com", "wrongpassword");
        });

        assertEquals("Credenciales inválidas", exception.getMessage());
    }
}
