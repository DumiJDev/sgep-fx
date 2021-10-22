package org.github.dumijdev.sgepfx.util;

import org.github.dumijdev.sgepfx.SGESpringFX;
import org.github.dumijdev.sgepfx.model.Aluno;
import org.github.dumijdev.sgepfx.model.Matricula;
import org.github.dumijdev.sgepfx.model.Pagamento;
import org.github.dumijdev.sgepfx.model.builder.AlunoBuilder;
import org.github.dumijdev.sgepfx.model.property.AlunoProperty;
import org.github.dumijdev.sgepfx.model.property.MatriculaProperty;
import org.github.dumijdev.sgepfx.model.property.PagamentoProperty;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.format.DateTimeFormatter.ofPattern;

public class Converte {
    public static AlunoProperty converte(Aluno a) {
        return new AlunoProperty(
                a.getId(),
                String.format("%s %s", a.getNomeProprio(), a.getNomeDeFamilia()),
                idade(a.getDataDeNascimento()),
                "",
                a.getDataDaInscricao().toString());
    }

    public static Aluno converte(AlunoProperty a) {
        return SGESpringFX.getAR().findById(a.getId()).get();
    }

    public static MatriculaProperty converte(Matricula m) {
        return new MatriculaProperty(
                m.getId(),
                String.format("%s %s", m.getAluno().getNomeProprio(), m.getAluno().getNomeDeFamilia()),
                m.getClasse().toString(),
                m.getPagamento().getValorPago().toString(),
                m.getPagamento().getDataDePagamento().toString());
    }

    public static Matricula converte(MatriculaProperty m) {
        return SGESpringFX.getMR().findById(m.getId()).get();
    }

    public static PagamentoProperty converte(Pagamento p) {
        return new PagamentoProperty(
                p.getId(),
                p.getAluno().nomeCompleto(),
                p.getTipoDePagamento().toString(),
                p.getValorPago().toString(),
                p.getDataDePagamento().toString());
    }

    public static List<AlunoProperty> converteTodos(Aluno...a) {
        return Arrays.stream(a).map(Converte::converte).collect(Collectors.toList());
    }

    public static List<MatriculaProperty> converteTodos(Matricula...m) {
        return Arrays.stream(m).map(Converte::converte).collect(Collectors.toList());
    }

    public static List<PagamentoProperty> converteTodos(Pagamento...pagamentos) {
        return Arrays.stream(pagamentos).map(Converte::converte).collect(Collectors.toList());
    }

    private static int idade(LocalDate nasc) {
        var hoje = LocalDate.now();
        return ((hoje.getYear() - nasc.getYear()) -
                (hoje.getMonthValue() > nasc.getMonthValue() ? 1 : 0) -
                (hoje.getMonthValue() == nasc.getMonthValue() ?
                        (hoje.getDayOfMonth() > nasc.getDayOfMonth() ? 1 : 0) : 0));
    }
}
