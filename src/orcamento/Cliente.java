/**
 *
 */
package orcamento;

import java.util.ArrayList;

/**
 *
 */
public class Cliente extends Utilizador {
    private int consumoUltimoMes;
    private double pagamentoUltimoMes;
    private ArrayList<Orcamento> listaOrcamentos;
    /**
     * @param nome
     * @param email
     * @param password
     * @param consumoUltimoMes
     * @param pagamentoUltimoMes
     */
    public Cliente(String nome, String email, String password, int consumoUltimoMes, double pagamentoUltimoMes) {
        super(nome, email, password);
        this.consumoUltimoMes = consumoUltimoMes;
        this.pagamentoUltimoMes = pagamentoUltimoMes;
        listaOrcamentos = new ArrayList<>();
    }
    
    /**
     * @return the consumoUltimoMes
     */
    public int getConsumoUltimoMes() {
        return consumoUltimoMes;
    }
    /**
     * @return the pagamentoUltimoMes
     */
    public double getPagamentoUltimoMes() {
        return pagamentoUltimoMes;
    }
    /**
     * @return the listaOrcamentos
     */
    public ArrayList<Orcamento> getListaOrcamentos() {
        return listaOrcamentos;
    }
}
