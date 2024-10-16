/**
 *
 */
package orcamento;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 */
public class Administrador extends Utilizador
{
	private boolean isManager;
	private LocalDateTime ultimaAlteracao;
	/**
	 * @param nome
	 * @param email
	 * @param password
	 */
	public Administrador(String nome, String email, String password, boolean isManager) {
		super(nome, email, password);
		this.isManager = isManager;
		ultimaAlteracao = LocalDateTime.now();
	}

	/**
	 * @param nome
	 * @param email
	 * @param password
	 * @param userId
	 */
	public Administrador(String nome, String email, String password, String userId, boolean isManager) {
		super(nome, email, password, userId);
		this.isManager = isManager;
		ultimaAlteracao = LocalDateTime.now();
	}

	public double getPagamentoUltimoMes()
	{
		return 0;
	}
	
	public int getConsumoUltimoMes()
	{
		return 0;
	}
	
	public ArrayList<Orcamento> getListaOrcamentos(){
		return new ArrayList<>();
	}
	
	/**
	 * @return the ultimaAlteracao
	 */
	public LocalDateTime getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	/**
	 * @param ultimaAlteracao the ultimaAlteracao to set
	 */
	public void setUltimaAlteracao(LocalDateTime ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	/**
	 * @return the isManager
	 */
	public boolean getIsManager() {
		return isManager;
	}
}
