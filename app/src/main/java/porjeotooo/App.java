package porjeotooo;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
     
        ClinicaService service = new ClinicaService();
        Scanner scanner = new Scanner(System.in);

        Paciente p1 = new Paciente("João Silva", "123", 34, "9999-8888", "Particular", false, "Nenhum");
        Paciente p2 = new Paciente("Maria Souza (Idosa)", "456", 72, "9999-7777", "Unimed", true, "Hipertensa");
        service.cadastrarPaciente(p1);
        service.cadastrarPaciente(p2);

        Profissional m1 = new Profissional("Rauzinh", "124124", "cardiovascular", 300.0);
        service.cadastrarProfissional(m1);

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== SISTEMA DE GESTÃO DE CLÍNICA ===");
            System.out.println("1. Cadastrar paciente");
            System.out.println("2. Cadastrar profissional");
            System.out.println("3. Novo agendamento");
            System.out.println("4. Finalizar atendimento (Emitir Recibo)");
            System.out.println("5. Cancelar agendamento");
            System.out.println("6. Relatório mensal e ocupação");
            System.out.println("7. Salvar dados (Integração JSON)");
            System.out.println("0. Ir embora");
            System.out.print("Escolha uma opção: ");
            
            opcao = lerNumeroInteiro(scanner);

            if (opcao == 1) {
                System.out.print("Nome do Paciente: ");
                String nome = scanner.nextLine();
                System.out.print("CPF/Código: ");
                String doc = scanner.nextLine();
                
                System.out.print("Idade: ");
                int idade = lerNumeroInteiro(scanner);
                
                System.out.print("Convênio (ou digite 'Particular'): ");
                String conv = scanner.nextLine();
                
                System.out.print("É prioritário? (true/false): ");
                boolean prio = lerBooleano(scanner);
                
                service.cadastrarPaciente(new Paciente(nome, doc, idade, "Contato", conv, prio, "Sem histórico"));
                System.out.println("Paciente cadastrado!");

            } else if (opcao == 2) {
                
                System.out.print("Nome do Profissional: ");
                String nome = scanner.nextLine();
                System.out.print("Documento/CRM: ");
                String doc = scanner.nextLine();
                System.out.print("Especialidade: ");
                String esp = scanner.nextLine();
                System.out.print("Valor da consulta padrão: ");
                double valor = lerNumeroDecimal(scanner);
                
                service.cadastrarProfissional(new Profissional(nome, doc, esp, valor));
                System.out.println("Profissional cadastrado!");

            } else if (opcao == 3) {
                if (service.getPacientes().isEmpty() || service.getProfissionais().isEmpty()) {
                    System.out.println("Cadastre pacientes e profissionais antes de agendar.");
                } else {
                    System.out.println("Selecione o Paciente (Index):");
                    for (int i = 0; i < service.getPacientes().size(); i++) {
                        System.out.println(i + " - " + service.getPacientes().get(i).getNome());
                    }
                    int idxPac = lerNumeroInteiro(scanner);

                    System.out.println("Selecione o Profissional (Index):");
                    for (int i = 0; i < service.getProfissionais().size(); i++) {
                        System.out.println(i + " - " + service.getProfissionais().get(i).getNome() + " (" + service.getProfissionais().get(i).getEspecialidade() + ")");
                    }
                    int idxProf = lerNumeroInteiro(scanner);

                    System.out.print("Data (DD/MM): ");
                    String data = scanner.nextLine();
                    System.out.print("Horário (Ex: 09:00): ");
                    String hora = scanner.nextLine();
                    System.out.print("Sala (Ex: Sala 102): ");
                    String sala = scanner.nextLine();

                    System.out.println("Tipo: 1- Consulta Comum, 2- Retorno, 3- Procedimento Com Custo");
                    int tipoSvc = lerNumeroInteiro(scanner);
                    
                    Cobravel servicoAtendimento;
                    if (tipoSvc == 2) {
                        servicoAtendimento = new Consulta("Retorno");
                    } else if (tipoSvc == 3) {
                        System.out.print("Valor do procedimento: ");
                        double valorProc = lerNumeroDecimal(scanner);
                        servicoAtendimento = new Procedimento("Procedimento Clínico", valorProc);
                    } else {
                        servicoAtendimento = new Consulta("Normal");
                    }

                    
                    if (idxPac >= 0 && idxPac < service.getPacientes().size() && idxProf >= 0 && idxProf < service.getProfissionais().size()) {
                        String res = service.agendar(service.getPacientes().get(idxPac), service.getProfissionais().get(idxProf), data, hora, sala, servicoAtendimento);
                        System.out.println(res);
                    } else {
                        System.out.println("Índice de paciente ou profissional inválido!");
                    }
                }

            } else if (opcao == 4) {
                System.out.println("Selecione o agendamento ativo para FINALIZAR:");
                for (int i = 0; i < service.getAgendamentos().size(); i++) {
                    Agendamento a = service.getAgendamentos().get(i);
                    System.out.println(i + " - " + a.getPaciente().getNome() + " com " + a.getProfissional().getNome() + " [" + a.getStatus() + "]");
                }
                int idx = lerNumeroInteiro(scanner);
                if (idx >= 0 && idx < service.getAgendamentos().size()) {
                    service.finalizarAtendimento(idx);
                } else {
                    System.out.println("Índice inválido!");
                }

            } else if (opcao == 5) {
                System.out.println("Selecione o agendamento para CANCELAR:");
                for (int i = 0; i < service.getAgendamentos().size(); i++) {
                    Agendamento a = service.getAgendamentos().get(i);
                    System.out.println(i + " - " + a.getPaciente().getNome() + " [" + a.getStatus() + "]");
                }
                int idx = lerNumeroInteiro(scanner);
                System.out.print("Cancelado fora do prazo? (true/false): ");
                boolean fora = lerBooleano(scanner);
                
                if (idx >= 0 && idx < service.getAgendamentos().size()) {
                    service.cancelarAgendamento(idx, fora);
                } else {
                    System.out.println("Índice inválido!");
                }

            } else if (opcao == 6) {
                service.gerarRelatorioMensal();

            } else if (opcao == 7) {
                DataManger.salvarDados(service);

            } else if (opcao == 0) {
                System.out.println("Saindo do sistema...");

            } else {
                System.out.println("Opção inválida!");
            }
        }
    }


    private static int lerNumeroInteiro(Scanner scanner) {
        while (true) {
            try {
                
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida! Digite apenas números inteiros: ");
            }
        }
    }

    private static double lerNumeroDecimal(Scanner scanner) {
        while (true) {
            try {
                
                String entrada = scanner.nextLine().replace(",", ".").trim();
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida! Digite um valor numérico (ex: 150.00): ");
            }
        }
    }

    private static boolean lerBooleano(Scanner scanner) {
        while (true) {
            String entrada = scanner.nextLine().trim().toLowerCase();
            if (entrada.equals("true") || entrada.equals("t") || entrada.equals("sim") || entrada.equals("s")) {
                return true;
            } else if (entrada.equals("false") || entrada.equals("f") || entrada.equals("nao") || entrada.equals("n") || entrada.equals("não")) {
                return false;
            }
            System.out.print("Entrada inválida! Digite 'true' (sim) ou 'false' (não): ");
        }
    }
}