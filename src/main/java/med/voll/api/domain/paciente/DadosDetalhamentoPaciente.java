package med.voll.api.domain.paciente;

import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.Especialidade;

public record DadosDetalhamentoPaciente(
        long id,
        String nome,
        String email,
        String telefone,
        Endereco endereco) {

    public DadosDetalhamentoPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(),
                paciente.getEmail(), paciente.getTelefone(),
                paciente.getEndereco());
    }
}
