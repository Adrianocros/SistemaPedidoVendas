package db;

import models.Cliente;
import models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuariosDB {

    private List<Usuario> usuarioList = new ArrayList<>();

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    //Incremente automaticamente o ID do usuario
    public  void addNovoUsuario(Usuario usuario){
        int id = usuarioList.size() + 1;
        usuario.setId(id);
        usuarioList.add(usuario);
    }

    //Com api de streens do java
    public Usuario getUsuarioPorId(int id) {
      return usuarioList.stream()
                .filter((usuario -> usuario.getId() == id))
                .findFirst()
                .get();

    }
}
