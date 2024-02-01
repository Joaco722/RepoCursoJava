package com.example.dao;

import com.example.models.Usuario;

import java.util.ArrayList;

public interface UsuarioDao {


    ArrayList<Usuario>getUsuarios();

    void eliminar(Long id);

    void registrar(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
