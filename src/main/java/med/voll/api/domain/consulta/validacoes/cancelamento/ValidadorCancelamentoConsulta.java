package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoConsulta {
    default void validar(DadosCancelamentoConsulta dados){

    }
}
