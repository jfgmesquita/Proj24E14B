package orcamento;

import java.text.DecimalFormat;

public class Orcamento {
    private String descricao;
    private String modelo;
    private double valorTotal;
    private int tempoTotalInstalacao;
    private int numPaineis;
    private int retorno;
    private double ocupacao;

    /**
	 * @param descricao
	 * @param modelo
	 * @param valorTotal
	 * @param tempoTotalInstalacao
	 */
	public Orcamento(String descricao, String modelo, double valorTotal, int tempoTotalInstalacao, int numPaineis, int retorno, double ocupacao) {
		this.descricao = descricao;
		this.modelo = modelo;
		this.valorTotal = valorTotal;
		this.tempoTotalInstalacao = tempoTotalInstalacao;
		this.numPaineis = numPaineis;
        this.retorno = retorno;
        this.ocupacao = ocupacao;
    }
	
    /*
     * @return a descrição do orçamento
     */
	public String getDescricao() {
        return descricao;
    }

    /**
     * @return o modelo do painel usado no orçamento
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @return o valor total do orçamento
     */
    public double getValorTotal() {
        return valorTotal;
    }

    /**
     * @return o tempo total de instalação dos painéis
     */
    public double getTempoTotalInstalacao() {
        return tempoTotalInstalacao;
    }
    
    /**
     * @return o nuúmero de painéis a serem instalados
     */
    public int getNumPaineis() {
    	return numPaineis;
    }

    /**
     * @return o tempo até haver retorno do investimento
     */
    public double getRetorno() {
        return retorno;
    }

    /**
     * @return a ocupação dos painéis
     */
    public double getOcupacao() {
        return ocupacao;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
    	
        return descricao + "\nSerão instalados " + numPaineis + " painéis " + modelo + "\n"
        		+ "Valor Total: " + df.format(valorTotal) + " euros\n"
				+ "Tempo de instalação total: " + tempoTotalInstalacao + " horas\n"
                + "Tempo até haver retorno do investimento: " + retorno + " meses\n"
                + "Ocupação dos painéis: " + df.format(ocupacao) + " m²\n";
    }
}