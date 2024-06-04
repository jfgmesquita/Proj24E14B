/**
 *
 */
package orcamento;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.apache.commons.validator.routines.EmailValidator;

/**
 *
 */
public class Gerir {
    private ArrayList<Utilizador> listaUtilizadores;
    private ArrayList<Painel> listaPaineis;
    
    /**
     *
     */
    public Gerir() {
        listaUtilizadores = new ArrayList<>();
        listaPaineis = new ArrayList<>();
    }

    public void inserirUtilizador(Utilizador newUtilizador)
    {
        listaUtilizadores.add(newUtilizador);
    }
    
    public int pesquisarEmail(String email)
    {
    	boolean encontrou = false;
    	for(Utilizador user : listaUtilizadores) {
    		if(user.getEmail().equals(email)) {
    			encontrou = true;
    			return listaUtilizadores.indexOf(user);
    		}
    	}
    	if(encontrou) {
    		System.out.println("Já existe um utilizador com este email!");
    	}
    	return -1;
    }
    
    public boolean validarEmail(String email)
    {
        EmailValidator emailValidator = EmailValidator.getInstance();
        boolean isValid = emailValidator.isValid(email);

        if(!isValid) {
            System.out.println("E-mail inválido!");
        }

        return isValid;
    }

    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%&]).{8,}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    
    public boolean validarSenha(String password) {
    	
        Matcher matcher = pattern.matcher(password);
        boolean result = matcher.matches()
;       if(!result) {
            System.out.println("A sua palavra-passe precisa de conter:\n"
                    + "-Pelo menos 8 caracteres.\n"
                    + "-Pelo menos 1 letra maiúscula.\n"
                    + "-Pelo menos 1 letra minúscula.\n"
                    + "-Pelo menos 1 número\n"
                    + "-Pelo menos 1 carácter especial (@#$%&)\n");
        }

		

        return result;
    }

    public String fazerLogin(String email, String password) {
        boolean encontrou = false;
        for(Utilizador utilizador:listaUtilizadores) {
            if(utilizador.getEmail().equals(email)) {
                encontrou = true;
                if(utilizador.getPassword().equals(password)) {
                    System.out.println(utilizador.getClass().getSimpleName());
                    return utilizador.getClass().getSimpleName();
                }
            }
        }
        if(encontrou){
            System.out.println("E-mail ou senha incorretos!\n");
            return "";
        }

        System.out.println("Utilizador não encontrado.\n");
        return "";
    }
    
    //Métodos do menu administrador
    
    public void registarPainel(String marca, String modelo, double precoUnitario, double tempoInstalacao, double producaoKwh, double medida)
    {
    	Painel newPainel = new Painel (marca, modelo, precoUnitario, tempoInstalacao, producaoKwh, medida);
    	listaPaineis.add(newPainel);
    }
    
    public void alterarPrecoUnitario(String modelo, double precoUnitario)
    {
    	for(Painel painel:listaPaineis) {
    		if(painel.getModelo().equals(modelo)) {
    			painel.setPrecoUnitario(precoUnitario);
    		}
    	}
    }
    
    public void alterarTempoInstalacao(String modelo, double tempoInstalacao)
    {
    	for(Painel painel:listaPaineis) {
    		if(painel.getModelo().equals(modelo)) {
    			painel.setTempoInstalacao(tempoInstalacao);
    		}
    	}
    }
    
    //Métodos do menu cliente
    
    public void mostrarPaineis()
    {
    	System.out.println("\nPaineis:");
    	for(Painel painel : listaPaineis) {
    		System.out.println((listaPaineis.indexOf(painel) + 1) + ") " + painel);
    	}
    }
    
    public void gerarOrcamento(String userId, String descricao, int panelOption)
    {
    	for(Utilizador user : listaUtilizadores) {
    		if(user.getUserId().equals(userId) && user.getClass().getSimpleName().equals("Cliente")) {
    			double userConsumo = (double) user.getConsumoUltimoMes();
    			String modelo = "";
    			double producao = 0;
    			double preco = 0;
    			double tempo = 0;
    			
    			for(Painel painel : listaPaineis) {
    				if(listaPaineis.indexOf(painel) == (panelOption - 1)) {
    					modelo = painel.getModelo();
    					producao = (double) painel.getProducao();
    					preco = painel.getPrecoUnitario();
    					tempo = painel.getTempoInstalacao();
    				}
    			}
    			
                // cálculo do número de painéis necessários, valor total e tempo total de instalação
    			double prodMensal = producao * 30;
    			double numeroPaineis = userConsumo / prodMensal;
    			double valorTotal = numeroPaineis * preco;
    			double tempoTotalInstalacao = numeroPaineis * tempo;

                // cálculo do tempo até ao retorno do investimento
                double energiaSolar = numeroPaineis * prodMensal * (1 - 0.2); // 20% de perdas
                double economiaMensal = userConsumo - energiaSolar;
                double retorno = valorTotal / economiaMensal;

    			Orcamento orcamento = new Orcamento(descricao, modelo, valorTotal, tempoTotalInstalacao, numeroPaineis, retorno);
    			user.getListaOrcamentos().add(orcamento);
    			
    			System.out.println("\n" + user.getListaOrcamentos().getLast());
    		}
    	}
    }
    
    public void mostrarOrcamentos(String userId)
    {
    	for(Utilizador user : listaUtilizadores) {
    		if(user.getUserId().equals(userId)) {
    			if(user.getListaOrcamentos().size() == 0) {
    				System.out.println("Não há orçamentos registados!");
    			}
                else {
    				System.out.println("Orçamentos:");
    				for(Orcamento orca : user.getListaOrcamentos()) {
    					System.out.println(orca.toString());
    				}    				
    			}
    			
    		}
    	}
    }

    public void compararOrcamentos(String userId)
    {
        for(Utilizador user : listaUtilizadores) {
            if(user.getUserId().equals(userId)) {
                if(user.getListaOrcamentos().size() == 0) {
                    System.out.println("Não há orçamentos registados!");
                }
                else {
                    double menorValorTotal = 999999999;
                    double menorTempoTotalInstalacao = 999999999;
                    double retornoMaisRapido = 999999999;
                    String menorValorOrca = "";
                    String menorTempoOrca = "";
                    String maisRapidoOrca = "";
                    for(Orcamento orca : user.getListaOrcamentos()) {
                        if(orca.getValorTotal() < menorValorTotal) {
                            menorValorTotal = orca.getValorTotal();
                            menorValorOrca = orca.getDescricao(); 
                        }
                        if(orca.getTempoTotalInstalacao() < menorTempoTotalInstalacao) {
                            menorTempoTotalInstalacao = orca.getTempoTotalInstalacao();
                            menorTempoOrca = orca.getDescricao();
                        }
                        if(orca.getRetorno() < retornoMaisRapido) {
                            retornoMaisRapido = orca.getRetorno();
                            maisRapidoOrca = orca.getDescricao();
                        }
                    }
                    System.out.println("Orçamento com menor valor total: " + menorValorOrca);
                    System.out.println("Orçamento com menor tempo total de instalação: " + menorTempoOrca);
                    System.out.println("Orçamento com retorno mais rápido: " + maisRapidoOrca);
                }
            }
        }
    }

    /**
     * @return the listaUtilizadores
     */
    public ArrayList<Utilizador> getListaUtilizadores() {
        return listaUtilizadores;
    }
    
    /**
     * @return the listaPaineis
     */
    public ArrayList<Painel> getListaPaineis() {
        return listaPaineis;
    }
}
