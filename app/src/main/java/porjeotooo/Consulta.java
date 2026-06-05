package porjeotooo;


public class Consulta implements Cobravel{

    private String tipo;

    public Consulta(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public double calcularValor(Paciente paciente, Profissional profissional) {
        if (tipo.equalsIgnoreCase("Retorno")) {
            return 0.0;
        }
        double valor = profissional.getValorConsultabase();
        
        if (!paciente.getConvenio().equalsIgnoreCase("Particular")) {
            valor = valor * 0.5;
        }
        return valor;
    }

    @Override
    public String getDescricaoServico() {
        return "Consulta Médica (" + tipo + ")";
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
