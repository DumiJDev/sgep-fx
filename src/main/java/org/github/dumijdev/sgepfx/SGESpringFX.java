package org.github.dumijdev.sgepfx;

import org.github.dumijdev.sgepfx.repository.AlunoRepository;
import org.github.dumijdev.sgepfx.repository.MatriculaRepository;
import org.github.dumijdev.sgepfx.repository.PagamentoRepository;
import org.github.dumijdev.sgepfx.repository.PropinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Dumilde Paulo
 */
@SpringBootApplication
public class SGESpringFX {
    private static AlunoRepository ar;
    private static MatriculaRepository mr;
    private static PagamentoRepository pr;
    private static PropinaRepository pr1;

    @Autowired
    public SGESpringFX(AlunoRepository ar, MatriculaRepository mr, PagamentoRepository pr, PropinaRepository pr1) {
        SGESpringFX.ar = ar;
        SGESpringFX.mr = mr;
        SGESpringFX.pr = pr;
        SGESpringFX.pr1 = pr1;
    }

    public static void main(String[] args) {
        SpringApplication.run(SGEPFXAPP.class, args);
    }

    public static AlunoRepository getAR() {
        return ar;
    }

    public static MatriculaRepository getMR() {
        return mr;
    }

    public static PagamentoRepository getPr() {
        return pr;
    }

    public static PropinaRepository getPr1() {
        return pr1;
    }
}
