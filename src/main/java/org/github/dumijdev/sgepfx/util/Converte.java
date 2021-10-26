package org.github.dumijdev.sgepfx.util;

import org.github.dumijdev.sgepfx.SGESpringFX;
import org.github.dumijdev.sgepfx.model.Aluno;
import org.github.dumijdev.sgepfx.model.Matricula;
import org.github.dumijdev.sgepfx.model.Pagamento;
import org.github.dumijdev.sgepfx.model.TipoDePagamento;
import org.github.dumijdev.sgepfx.model.property.AlunoProperty;
import org.github.dumijdev.sgepfx.model.property.MatriculaProperty;
import org.github.dumijdev.sgepfx.model.property.PagamentoProperty;
import org.github.dumijdev.sgepfx.model.property.TipoDePagamentoProperty;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Converte {
    public static AlunoProperty converte(Aluno a) {
        return new AlunoProperty(a.getId(), a.getNome(), idade(a.getDataDeNascimento()), "", a.getDataDaInscricao().toString());
    }

    public static Aluno converte(AlunoProperty a) {
        return SGESpringFX.getAR().busca(a.getId());
    }

    public static MatriculaProperty converte(Matricula m) {
        return new MatriculaProperty(
                m.getId(),
                String.format("%s", m.getAluno().getNome()),
                m.getClasse().toString(),
                m.getPagamento().getValorPago().toString(),
                m.getPagamento().getDataDePagamento().toString());
    }

    public static Matricula converte(MatriculaProperty m) {
        return SGESpringFX.getMR().findById(m.getId()).orElse(null);
    }

    public static PagamentoProperty converte(Pagamento p) {
        return new PagamentoProperty(
                p.getId(),
                p.getAluno().getNome(),
                p.getTipoDePagamento().toString(),
                p.getValorPago().toString(),
                p.getDataDePagamento().toString());
    }

    public static TipoDePagamentoProperty converte(TipoDePagamento t) {
        return new TipoDePagamentoProperty(t);
    }

    public static List<AlunoProperty> converteTodos(Aluno... as) {
        return Arrays.stream(as).map(Converte::converte).collect(Collectors.toList());
    }

    public static List<MatriculaProperty> converteTodos(Matricula... ms) {
        return Arrays.stream(ms).map(Converte::converte).collect(Collectors.toList());
    }

    public static List<PagamentoProperty> converteTodos(Pagamento... pagamentos) {
        return Arrays.stream(pagamentos).map(Converte::converte).collect(Collectors.toList());
    }

    private static int idade(LocalDate nasc) {
        var hoje = LocalDate.now();
        return ((hoje.getYear() - nasc.getYear()) -
                (hoje.getMonthValue() > nasc.getMonthValue() ? 1 : 0) -
                (hoje.getMonthValue() == nasc.getMonthValue() ?
                        (hoje.getDayOfMonth() > nasc.getDayOfMonth() ? 1 : 0) : 0));
    }

    public static List<TipoDePagamentoProperty> converteTodos(TipoDePagamento[] ex, TipoDePagamento... tipos) {
        if (ex == null)
            return Arrays.stream(tipos).map(Converte::converte).collect(Collectors.toList());
        return Arrays.stream(tipos)
                .filter(tipoDePagamento -> !Arrays.asList(ex).contains(tipoDePagamento))
                .map(Converte::converte).collect(Collectors.toList());
    }
}
