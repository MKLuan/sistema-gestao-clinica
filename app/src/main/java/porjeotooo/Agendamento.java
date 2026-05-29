package porjeotooo;


public class Agendamento {

    private Paciente paciente;
    private Profissional profissional;
    private String data;
    private String hora;
    private String sala;
    private String status;
    private Cobravel servico;

    public Agendamento(Paciente paciente, Profissional profissional, String data, String hora, String sala, Cobravel servico) {
        this.paciente = paciente;
        this.profissional = profissional;
        this.data = data;
        this.hora = hora;
        this.sala = sala;
        this.status = "ATIVO";
        this.servico = servico;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public Profissional getProfissional() {
        return profissional;
    }
    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
    public String getSala() {
        return sala;
    }
    public void setSala(String sala) {
        this.sala = sala;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Cobravel getServico() {
        return servico;
    }
    public void setServico(Cobravel servico) {
        this.servico = servico;
    }

    

}
