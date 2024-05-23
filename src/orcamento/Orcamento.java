package orcamento;

public class Orcamento {
    private String descricao;
    private String modelo;
    private double valorTotal;
    private double tempoTotalInstalacao;
    private int numPaineis;

    /**
	 * @param descricao
	 * @param modelo
	 * @param valorTotal
	 * @param tempoTotalInstalacao
	 */
	public Orcamento(String descricao, String modelo, double valorTotal, double tempoTotalInstalacao, double numPaineis) {
		this.descricao = descricao;
		this.modelo = modelo;
		this.valorTotal = valorTotal;
		this.tempoTotalInstalacao = (int) Math.ceil(tempoTotalInstalacao);
		this.numPaineis = (int) Math.ceil(numPaineis);
	}
	public String getDescricao() {
        return descricao;

    }

    public String getModelo() {
        return modelo;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double getTempoTotalInstalacao() {
        return tempoTotalInstalacao;
    }
    
    /**
     * @return the numPaineis
     */
    public int getNumPaineis() {
    	return numPaineis;
    }
    
    @Override
    public String toString() {
    	String StValorTotal = String.format("%.2f", valorTotal);
    	
        return descricao + "\nSerão instaladas " + numPaineis + " painéis " + modelo + "\n"
        		+ "Valor Total: " + StValorTotal + " euros\n"
				+ "Tempo de instalação total: " + tempoTotalInstalacao + " horas\n";
    }
}