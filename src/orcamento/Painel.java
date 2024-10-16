package orcamento;

import java.io.Serializable;

public class Painel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String marca;
    private String modelo;
    private double precoUnitario; // por unidade
    private double tempoInstalacao; // por unidade
    private double producaoKwh; // kWh
    private double medida; // m2
    
    public Painel() {
    }

    public Painel(String marca, String modelo, double precoUnitario, double tempoInstalacao, double producaoKwh, double medida) {
        this.marca = marca;
        this.modelo = modelo;
        this.precoUnitario = precoUnitario;
        this.tempoInstalacao = tempoInstalacao;
        this.producaoKwh = producaoKwh;
        this.medida = medida;
    }

    public double getMedida() {
        return medida;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public double getTempoInstalacao() {
        return tempoInstalacao;
    }

    public double getProducao() {
        return producaoKwh;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    /**
	 * @param tempoInstalacao the tempoInstalacao to set
	 */
	public void setTempoInstalacao(double tempoInstalacao) {
		this.tempoInstalacao = tempoInstalacao;
	}

	@Override
    public String toString() {
        return modelo + "(" + marca + ") / " + precoUnitario + " Euros / "
                + tempoInstalacao + " horas para instalação / Produz " + producaoKwh + " kWh " + " / Ocupa " + medida + " m²";
    }

}