package porjeotooo;

import java.util.ArrayList;
import java.util.List;

public class Profissional extends Pessoa {

    private String especialidade;
    private double valorConsultabase;
    private List<String> horariodisponivel;
    public Profissional(String nome, String documento, String especialidade, double valorConsultabase) {
        super(nome, documento);
        this.especialidade = especialidade;
        this.valorConsultabase = valorConsultabase;
        this.horariodisponivel = new ArrayList<>();
    }
    public String getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    public double getValorConsultabase() {
        return valorConsultabase;
    }
    public void setValorConsultabase(double valorConsultabase) {
        this.valorConsultabase = valorConsultabase;
    }
    public List<String> getHorariodisponivel() {
        return horariodisponivel;
    }
    public void setHorariodisponivel(List<String> horariodisponivel) {
        this.horariodisponivel = horariodisponivel;
    }

    
}
