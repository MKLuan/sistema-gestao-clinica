package porjeotooo;

public class Paciente extends Pessoa{

    private int idade;
    private String contato;
    private String convenio;
    private boolean prioritario;
    private String historico;
    public Paciente(String nome, String documento, int idade, String contato, String convenio, boolean prioritario, String historico) {
        super(nome, documento);
        this.idade = idade;
        this.contato = contato;
        this.convenio = convenio;
        this.prioritario = prioritario;
        this.historico = historico;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public String getContato() {
        return contato;
    }
    public void setContato(String contato) {
        this.contato = contato;
    }
    public String getConvenio() {
        return convenio;
    }
    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }
    public boolean isPrioritario() {
        return prioritario;
    }
    public void setPrioritario(boolean prioritario) {
        this.prioritario = prioritario;
    }
    public String getHistorico() {
        return historico;
    }
    public void setHistorico(String historico) {
        this.historico = historico;
    }



    
}
