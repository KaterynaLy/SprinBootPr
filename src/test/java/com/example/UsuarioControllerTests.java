package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.naming.AuthenticationException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTests {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioControllerTests.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @MockBean
    private UsuarioService usuarioService;

    @BeforeEach
    public void setup() {
        // Additional setup if needed
    }

    @Test
    public void testListarUsuarios() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setNombreUsuario("testUser");
        usuario.setEmail("test@example.com");
        usuario.setPassword("password");

        List<Usuario> usuarios = Collections.singletonList(usuario);
        when(usuarioService.listarUsuarios()).thenReturn(usuarios);

        MvcResult result = mockMvc.perform(get("/api/usuarios/listar"))
                .andDo(MockMvcResultHandlers.print())  // Logs request and response details
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].idUsuario").value(1L))
                .andExpect(jsonPath("$[0].nombreUsuario").value("testUser"))
                .andExpect(jsonPath("$[0].email").value("test@example.com"))
                .andReturn();

        logResult(result);
    }

    @Test
    public void testLoginSuccess() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setNombreUsuario("testUser");
        usuario.setEmail("test@example.com");
        usuario.setPassword("password");

        when(authService.autenticarUsuario("test@example.com", "password")).thenReturn(usuario);

        MvcResult result = mockMvc.perform(post("/api/usuarios/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"password\":\"password\"}"))
                .andDo(MockMvcResultHandlers.print())  // Logs request and response details
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idUsuario").value(1L))
                .andExpect(jsonPath("$.nombreUsuario").value("testUser"))
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andReturn();

        logResult(result);
    }

    @Test
    public void testLoginUnauthorized() throws Exception {
        when(authService.autenticarUsuario("wrong@example.com", "wrongpassword"))
                .thenThrow(new AuthenticationException("Credenciales inválidas"));

        MvcResult result = mockMvc.perform(post("/api/usuarios/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"wrong@example.com\",\"password\":\"wrongpassword\"}"))
                .andDo(MockMvcResultHandlers.print())  // Logs request and response details
                .andExpect(status().isUnauthorized())
                .andReturn();

        logResult(result);
    }

    private void logResult(MvcResult result) {
        logger.info("Request URI: {}", result.getRequest().getRequestURI());
        logger.info("Request Method: {}", result.getRequest().getMethod());
        logger.info("Response Status: {}", result.getResponse().getStatus());
        try {
            logger.info("Response Content: {}", result.getResponse().getContentAsString());
        } catch (UnsupportedEncodingException e) {
            logger.error("failed to log response: {}", result.getResponse());
        }
    }
}
