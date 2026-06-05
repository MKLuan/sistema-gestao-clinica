package porjeotooo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class ClinicaService {

    private List<Paciente> pacientes = new ArrayList<>();
    private List<Profissional> profissionais = new ArrayList<>();
    private List<Agendamento> agendamentos = new ArrayList<>();
    private Queue<Paciente> filaEspera = new LinkedList<>();

    private final int MAX_ATIVOS = 10;
    private final double TAXA_CANCELAMENTO = 50.0;

    public int countAgendamentosAtivos() {
        int cont = 0;
        for (Agendamento a : agendamentos) {
            if (a.getStatus().equals("ATIVO")) cont++;
        }
        return cont;
    }
    public String agendar(Paciente pac, Profissional prof, String data, String hora, String sala, Cobravel servico) {
        if (countAgendamentosAtivos() >= MAX_ATIVOS) {
            if (pac.isPrioritario()) {
                filaEspera.add(pac);
                return "Limite de 10 agendamentos ativos atingido. Paciente prioritário movido para a FILA DE ESPERA.";
            }
            return "Erro: Limite de 10 agendamentos ativos simultâneos atingido. Não foi possível agendar.";
        }

        
        for (Agendamento a : agendamentos) {
            if (a.getStatus().equals("ATIVO") && a.getData().equals(data) && a.getHora().equals(hora)) {
                if (a.getProfissional().getDocumento().equals(prof.getDocumento())) {
                    return "Erro: O profissional já possui um agendamento neste horário!";
                }
                if (a.getSala().equalsIgnoreCase(sala)) {
                    return "Erro: A sala informada já está ocupada neste horário!";
                }
            }
        }

        agendamentos.add(new Agendamento(pac, prof, data, hora, sala, servico));
        return "Agendamento realizado com sucesso!";
    }

    public void cancelarAgendamento(int index, boolean foraDoPrazo) {
        if (index >= 0 && index < agendamentos.size()) {
            Agendamento a = agendamentos.get(index);
            a.setStatus("CANCELADO");
            System.out.println("Agendamento Cancelado.");
            if (foraDoPrazo) {
                System.out.printf("Multa por cancelamento fora do prazo aplicada: R$ %.2f\n", TAXA_CANCELAMENTO);
            }

            
            if (!filaEspera.isEmpty()) {
                Paciente proximo = filaEspera.poll();
                System.out.println("Vaga liberada! Chamando paciente prioritário da fila: " + proximo.getNome());
            }
        }
    }

    public void finalizarAtendimento(int index) {
        if (index >= 0 && index < agendamentos.size()) {
            Agendamento a = agendamentos.get(index);
            a.setStatus("FINALIZADO");
            
            double total = a.getServico().calcularValor(a.getPaciente(), a.getProfissional());
            
            
            System.out.println("\n=== EMISSÃO DE RECIBO DE ATENDIMENTO ===");
            System.out.println("Paciente: " + a.getPaciente().getNome() + " (Convênio: " + a.getPaciente().getConvenio() + ")");
            System.out.println("Profissional: " + a.getProfissional().getNome() + " (" + a.getProfissional().getEspecialidade() + ")");
            System.out.println("Data/Hora: " + a.getData() + " às " + a.getHora() + " - Sala: " + a.getSala());
            System.out.println("Serviço: " + a.getServico().getDescricaoServico());
            System.out.printf("TOTAL COBRADO: R$ %.2f\n", total);
            System.out.println("=========================================\n");
        }
    }

    public void gerarRelatorioMensal() {
        System.out.println("\n--- RELATÓRIO ANALÍTICO MENSAL ---");
        int finalizados = 0, cancelados = 0, ativos = 0;
        double receitaTotal = 0;

        for (Agendamento a : agendamentos) {
            switch (a.getStatus()) {
                case "FINALIZADO" -> {
                    finalizados++;
                    receitaTotal += a.getServico().calcularValor(a.getPaciente(), a.getProfissional());
                }
                case "CANCELADO" -> cancelados++;
                case "ATIVO" -> ativos++;
            }
        }

        System.out.println("Pacientes atendidos (Finalizados): " + finalizados);
        System.out.println("Agendamentos Ativos Atuais: " + ativos);
        System.out.println("Cancelamentos registrados: " + cancelados);
        System.out.printf("Receita Total Gerada: R$ %.2f\n", receitaTotal);
        
        
        double taxaOcupacao = ((double) ativos / MAX_ATIVOS) * 100;
        System.out.printf("Taxa de ocupação atual da agenda: %.1f%%\n", taxaOcupacao);
        System.out.println("----------------------------------");
    }


    public void cadastrarPaciente(Paciente p) { 
        pacientes.add(p); 
    }
    public void cadastrarProfissional(Profissional p) { 
        profissionais.add(p); 
    }

    public List<Paciente> getPacientes() { 
        return pacientes; 
    }
    public List<Profissional> getProfissionais() { 
        return profissionais; 
    }
    public List<Agendamento> getAgendamentos() { 
        return agendamentos; 
    }
    

}
