package porjeotooo;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
     
        ClinicaService service = new ClinicaService();
        Scanner scanner = new Scanner(System.in);

        // Massa de dados de teste inicial
        Paciente p1 = new Paciente("João Silva", "123", 34, "9999-8888", "Particular", false, "Nenhum");
        Paciente p2 = new Paciente("Maria Souza (Idosa)", "456", 72, "9999-7777", "Unimed", true, "Hipertensa");
        service.cadastrarPaciente(p1);
        service.cadastrarPaciente(p2);

        Profissional m1 = new Profissional("raimundinho", "007", "cardiologista", 300);
        service.cadastrarProfissional(m1);

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== SISTEMA DE GESTÃO DE CLÍNICA ===");
            System.out.println("1. Cadastrar paciente");
            System.out.println("2. Novo agendamento");
            System.out.println("3. Finalizar atendimento (Emitir Recibo)");
            System.out.println("4. Cancelar agendamento");
            System.out.println("5. Relatório mensal e mcupação");
            System.out.println("6. Salvar dados (Integração JSON)");
            System.out.println("0. Ir embora");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            if (opcao == 1) {
                System.out.print("Nome do Paciente: ");
                String nome = scanner.nextLine();
                System.out.print("CPF/Código: ");
                String doc = scanner.nextLine();
                System.out.print("Idade: ");
                int idade = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Convênio (ou digite 'Particular'): ");
                String conv = scanner.nextLine();
                System.out.print("É prioritário? (true/false): ");
                boolean prio = scanner.nextBoolean();
                scanner.nextLine();
                
                service.cadastrarPaciente(new Paciente(nome, doc, idade, "Contato", conv, prio, "Sem histórico"));
                System.out.println("Paciente cadastrado!");

            } else if (opcao == 2) {
                if (service.getPacientes().isEmpty() || service.getProfissionais().isEmpty()) {
                    System.out.println("Cadastre pacientes e profissionais antes de agendar.");
                } else {
                    System.out.println("Selecione o Paciente (Index):");
                    for (int i = 0; i < service.getPacientes().size(); i++) {
                        System.out.println(i + " - " + service.getPacientes().get(i).getNome());
                    }
                    int idxPac = scanner.nextInt();

                    System.out.println("Selecione o Profissional (Index):");
                    for (int i = 0; i < service.getProfissionais().size(); i++) {
                        System.out.println(i + " - " + service.getProfissionais().get(i).getNome() + " (" + service.getProfissionais().get(i).getEspecialidade() + ")");
                    }
                    int idxProf = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Data (DD/MM): ");
                    String data = scanner.nextLine();
                    System.out.print("Horário (Ex: 09:00): ");
                    String hora = scanner.nextLine();
                    System.out.print("Sala (Ex: Sala 102): ");
                    String sala = scanner.nextLine();

                    System.out.println("Tipo: 1- Consulta Comum, 2- Retorno, 3- Procedimento Com Custo");
                    int tipoSvc = scanner.nextInt();
                    
                    Cobravel servicoAtendimento;
                    if (tipoSvc == 2) {
                        servicoAtendimento = new Consulta("Retorno");
                    } else if (tipoSvc == 3) {
                        servicoAtendimento = new Procedimento("Remoção de Pontos", 150.0);
                    } else {
                        servicoAtendimento = new Consulta("Normal");
                    }

                    String res = service.agendar(service.getPacientes().get(idxPac), service.getProfissionais().get(idxProf), data, hora, sala, servicoAtendimento);
                    System.out.println(res);
                }

            } else if (opcao == 3) {
                System.out.println("Selecione o agendamento ativo para FINALIZAR:");
                for (int i = 0; i < service.getAgendamentos().size(); i++) {
                    Agendamento a = service.getAgendamentos().get(i);
                    System.out.println(i + " - " + a.getPaciente().getNome() + " com " + a.getProfissional().getNome() + " [" + a.getStatus() + "]");
                }
                int idx = scanner.nextInt();
                service.finalizarAtendimento(idx);

            } else if (opcao == 4) {
                System.out.println("Selecione o agendamento para CANCELAR:");
                for (int i = 0; i < service.getAgendamentos().size(); i++) {
                    Agendamento a = service.getAgendamentos().get(i);
                    System.out.println(i + " - " + a.getPaciente().getNome() + " [" + a.getStatus() + "]");
                }
                int idx = scanner.nextInt();
                System.out.print("Cancelado fora do prazo? (true/false): ");
                boolean fora = scanner.nextBoolean();
                service.cancelarAgendamento(idx, fora);

            } else if (opcao == 5) {
                service.gerarRelatorioMensal();

            } else if (opcao == 6) {
                DataManger.salvarDados(service);

            } else if (opcao == 0) {
                System.out.println("Saindo do sistema...");

            } else {
                System.out.println("Opção inválida!");
            }
        }
   
    }
}
