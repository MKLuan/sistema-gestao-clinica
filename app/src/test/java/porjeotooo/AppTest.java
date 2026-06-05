package porjeotooo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void testApenasAgendamentoComSucesso() {
        ClinicaService service = new ClinicaService();
        
        
        Paciente pac = new Paciente("Paciente Teste", "111", 25, "9999", "Particular", false, "Nenhum");
        Profissional prof = new Profissional("Dr. Teste", "CRM999", "Geral", 200.0);
        Consulta consulta = new Consulta("Normal");
        
        
        String resultado = service.agendar(pac, prof, "10/10", "14:00", "Sala 1", consulta);
        
        
        assertEquals("Agendamento realizado com sucesso!", resultado);
        assertEquals(1, service.countAgendamentosAtivos());
    }
}