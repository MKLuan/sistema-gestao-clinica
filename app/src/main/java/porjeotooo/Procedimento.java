package porjeotooo;



public class Procedimento implements Cobravel {

    private String nomeProcedimento;
    private double custoAdicional;
    public Procedimento(String nomeProcedimento, double custoAdicional) {
        this.nomeProcedimento = nomeProcedimento;
        this.custoAdicional = custoAdicional;
    }

    @Override
    public double calcularValor(Paciente paciente, Profissional profissional) {
        double valorBase = profissional.getValorConsultabase() + custoAdicional;
        if (!paciente.getConvenio().equalsIgnoreCase("Particular")) {
            return valorBase * 0.7;
        }
        return valorBase;
    }
    @Override
    public String getDescricaoServico() {
        return "Procedimento: " + nomeProcedimento;
    }
    

}
