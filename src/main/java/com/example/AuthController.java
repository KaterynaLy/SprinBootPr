/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example;

/*@RestController
@RequestMapping("/auth")
public class AuthController {

        @Autowired
        private AuthService authService;

        @PostMapping("/login")
        public ResponseEntity<UsuarioDTO> login(@RequestBody Usuario usuarioRequest) {
            // Llamar al servicio para autenticar al usuario
            try {
                UsuarioDTO usuarioDTO = authService.autenticarUsuario(usuarioRequest.getEmail(), usuarioRequest.getPassword());

                if (usuarioDTO != null) {
                    return ResponseEntity.ok(usuarioDTO); // Devuelve el usuario con sus datos y rol
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 401 si las credenciales son incorrectas
                }
            } catch (AuthenticationException e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        }
    }*/
