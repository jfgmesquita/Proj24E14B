package orcamento;

public class Orcamento {
    private String descricao;
    private String modelo;
    private double valorTotal;
    private double tempoTotalInstalacao;
    private int numPaineis;
    private double retorno;

    /**
	 * @param descricao
	 * @param modelo
	 * @param valorTotal
	 * @param tempoTotalInstalacao
	 */
	public Orcamento(String descricao, String modelo, double valorTotal, double tempoTotalInstalacao, double numPaineis, double retorno) {
		this.descricao = descricao;
		this.modelo = modelo;
		this.valorTotal = valorTotal;
		this.tempoTotalInstalacao = (int) Math.ceil(tempoTotalInstalacao);
		this.numPaineis = (int) Math.ceil(numPaineis);
        this.retorno = (int) Math.ceil(retorno);
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

    @Override
    public String toString() {
    	String StValorTotal = String.format("%.2f", valorTotal);
    	
        return descricao + "\nSerão instalados " + numPaineis + " painéis " + modelo + "\n"
        		+ "Valor Total: " + StValorTotal + " euros\n"
				+ "Tempo de instalação total: " + tempoTotalInstalacao + " horas\n"
                + "Tempo até haver retorno do investimento: " + retorno + " meses\n";
    }
}