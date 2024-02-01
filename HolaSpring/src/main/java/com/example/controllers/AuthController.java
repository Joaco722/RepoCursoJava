package com.example.controllers;

import com.example.dao.UsuarioDao;
import com.example.models.Usuario;
import com.example.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> login(@RequestBody Usuario usuario) throws UnsupportedEncodingException {
        Map<String, String> response = new HashMap<>();
        Usuario usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
        if (usuarioLogueado!=null){
            response.put("token", jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("error", "FAIL");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}

