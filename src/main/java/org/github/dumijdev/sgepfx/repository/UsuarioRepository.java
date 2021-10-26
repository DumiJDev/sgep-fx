package org.github.dumijdev.sgepfx.repository;

import org.github.dumijdev.sgepfx.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;

import java.util.List;

import static org.github.dumijdev.sgepfx.util.cripto.DPCripto.codifica;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {
    @Query("select u from Usuario u order by u.login")
    List<Usuario> buscaTodos();

    @Query("select u from Usuario u where u.login = ?1 and u.senha = ?2")
    Usuario buscaPeloLoginESenha(@NonNull String login, String senha);

    default Usuario login(@NonNull String login, @NonNull String senha) {
        return  buscaTodos().stream()
                .filter(u -> u.getLogin().equals(login) && u.getSenha().equals(codifica(login, senha)))
                .findFirst().orElse(null);
    }

    default Usuario salva(Usuario u) {
        u.setSenha(codifica(u.getLogin(), u.getSenha()));
        return save(u);
    }
}