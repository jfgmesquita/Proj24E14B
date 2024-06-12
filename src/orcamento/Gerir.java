/**
 *
 */
package orcamento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

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

    /**
     * @param newUtilizador
     */
    public void inserirUtilizador(Utilizador newUtilizador)
    {
        listaUtilizadores.add(newUtilizador);
        escreverUtilizadores(newUtilizador);
    }

    public void escreverUtilizadores(Utilizador newUtilizador)
    {
        try {
            FileWriter fileWriter;
            String saida = "";
            if (newUtilizador.getClass().getSimpleName().equals("Cliente")) {

                fileWriter = new FileWriter("clientes.txt", true);
                Cliente cli = (Cliente) newUtilizador;
                saida = cli.getUserId() + "," + cli.getNome() + "," + cli.getEmail() + "," + cli.getPassword() + "," + cli.getConsumoUltimoMes() + "," + (int)cli.getPagamentoUltimoMes();
            
            } else if(newUtilizador.getClass().getSimpleName().equals("Administrador")) {

                fileWriter = new FileWriter("adms.txt", true);
                Administrador adm = (Administrador) newUtilizador;
                saida = adm.getUserId() + "," + adm.getNome() + "," + adm.getEmail() + "," + adm.getPassword() + "," + adm.getIsManager();
            } else {

                fileWriter = new FileWriter("utilizadores.txt", true);
            
            }
        
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(saida);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo");
            e.printStackTrace();
        }
    }

    public void escreverOrcamentos(String userId, Orcamento orca)
    {
        try {
            FileWriter fileWriter = new FileWriter("orcamentos.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String saida = userId + "," + orca.getDescricao() + "," + orca.getModelo() + "," + orca.getValorTotal() + "," + (int) orca.getTempoTotalInstalacao() + "," + orca.getNumPaineis() + "," + (int) orca.getRetorno() + "," + orca.getOcupacao();
            bufferedWriter.write(saida);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo");
            e.printStackTrace();
        }
    }

    public void escreverPaineis(Painel newPainel)
    {
        try {
            FileWriter fileWriter = new FileWriter("paineis.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String saida = newPainel.getMarca() + "," + newPainel.getModelo() + "," + newPainel.getPrecoUnitario() + "," + newPainel.getTempoInstalacao() + "," + newPainel.getProducao() + "," + newPainel.getMedida();
            bufferedWriter.write(saida);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo");
            e.printStackTrace();
        }
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

        escreverPaineis(newPainel);
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
    			double userValorPago = user.getPagamentoUltimoMes();
    			double userConsumo = (double) user.getConsumoUltimoMes();
    			String modelo = "";
    			double producao = 0;
    			double preco = 0;
    			double tempo = 0;
                double medida = 0;
    			
    			for(Painel painel : listaPaineis) {
    				if(listaPaineis.indexOf(painel) == (panelOption - 1)) {
    					modelo = painel.getModelo();
    					producao = (double) painel.getProducao();
    					preco = painel.getPrecoUnitario();
    					tempo = painel.getTempoInstalacao();
                        medida = painel.getMedida();
    				}
    			}
    			
                // cálculo do número de painéis necessários, valor total, tempo total de instalação e tempo até ao retorno do investimento
    			double prodMensal = producao * 30;
    			int numeroPaineis = (int) Math.ceil(userConsumo / prodMensal);
    			double valorTotal = numeroPaineis * preco;
    			int tempoTotalInstalacao = (int) Math.ceil(numeroPaineis * tempo);
                int retorno = (int) Math.ceil(valorTotal / userValorPago);
                

                // cálculo da ocupação do telhado
                double ocupacao = numeroPaineis * medida;

    			Orcamento orcamento = new Orcamento(descricao, modelo, valorTotal, tempoTotalInstalacao, numeroPaineis, retorno, ocupacao);
    			user.getListaOrcamentos().add(orcamento);
                escreverOrcamentos(userId, orcamento);
    			
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
    					System.out.println("\n" + orca.toString());
                        informacaoAmbiente(orca);
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
                    double menorValorOcupacao = 999999999;
                    String menorValorOrca = "";
                    String menorTempoOrca = "";
                    String maisRapidoOrca = "";
                    String menosEspacoOrca = "";
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
                        if(orca.getOcupacao() < menorValorOcupacao) {
                            menorValorOcupacao = orca.getOcupacao();
                            menosEspacoOrca = orca.getDescricao();
                        }
                    }
                    DecimalFormat df = new DecimalFormat("#.##");
                    System.out.println("Orçamento com menor valor total: " + menorValorOrca + " (" + df.format(menorValorTotal) + " euros)");
                    System.out.println("Orçamento com menor tempo total de instalação: " + menorTempoOrca + " (" + menorTempoTotalInstalacao + " horas)");
                    System.out.println("Orçamento com retorno mais rápido: " + maisRapidoOrca + " (" + retornoMaisRapido + " meses)");
                    System.out.println("Orçamento com menos espaço ocupado: " + menosEspacoOrca + " (" + df.format(menorValorOcupacao) + " m²)");
                }
            }
        }
    }

    public void informacaoAmbiente(Orcamento orca) {
    	double producaoKwh = 0;
    	double estimativaCo2;
    	double estimativaAnual;
    	int estimativaArvores;

    	for(Painel painel : listaPaineis) {
    		if(painel.getModelo().equals(orca.getModelo())) {
    			producaoKwh = painel.getProducao();
            }
        }

    //A média de Carbono emitido por Kwh de energia em portugal é 130 gramas.
    	estimativaCo2 = (producaoKwh * orca.getNumPaineis()) * 130;
    	estimativaAnual = (estimativaCo2 * 24 * 365)/1000; 	
    	estimativaArvores = (int) Math.ceil(estimativaAnual/22);
    	System.out.println("Ao fim de um ano, estará a ajudar o meio ambiente com menos " 
    	+ Math.round(estimativaAnual) + "Kg de CO2 emitidos, o equivalente a " + estimativaArvores + " árvores plantadas!");
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

    public void cancelarOperacao() {  
        MenuPrincipal.main(null);
        System.exit(0);
    }

    public void lerFicheiros(String path, String pathAdm, String pathClientes, String pathOrcamentos)
    {

        String email;
        String password;
        //Leitura de admins
        //----------------------------------------------------------------------------------------------------
        
        try (BufferedReader br = new BufferedReader(new FileReader(pathAdm))) {
            
            String line = br.readLine();
            line = br.readLine();
            while(line != null) {
                String[] vetor = line.split(",");
                String userId = vetor[0];
                String nome = vetor[1];
                email = vetor[2];
                password = vetor[3];
                boolean isManager = Boolean.parseBoolean(vetor[4]);
                
                Administrador newAdmin = new Administrador(nome,email,password,userId,isManager);
                getListaUtilizadores().add(newAdmin);
                
                line = br.readLine();
            }
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        //Leitura de clientes
        //----------------------------------------------------------------------------------------------------

        try (BufferedReader br = new BufferedReader(new FileReader(pathClientes))) {
            String line = br.readLine();
            line = br.readLine();
            while(line != null) {
                String[] vetor = line.split(",");
                String userId = vetor[0];
                String nome = vetor[1];
                email = vetor[2];
                password = vetor[3];
                int consumoUltimoMes = Integer.parseInt(vetor[4]);
                int pagamentoUltimoMes = Integer.parseInt(vetor[5]);

                Cliente newCliente = new Cliente(nome, email, password, userId, consumoUltimoMes, pagamentoUltimoMes);
                getListaUtilizadores().add(newCliente);

                line = br.readLine();
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        //Leitura de paineis
        //----------------------------------------------------------------------------------------------------
        
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            
            String line = br.readLine();
            line = br.readLine();
            while(line != null) {
                String[]vetor = line.split(",");
                String marca = vetor[0];
                String modelo = vetor[1];
                double precoUnitario = Double.parseDouble(vetor[2]);
                double tempoInstalacao = Double.parseDouble(vetor[3]);
                double producaoKwh = Double.parseDouble(vetor[4]);
                double medida = Double.parseDouble(vetor[5]);
                
                Painel newPainel = new Painel(marca, modelo, precoUnitario, tempoInstalacao, producaoKwh, medida);
                getListaPaineis().add(newPainel);
                
                line = br.readLine();
            }
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Ler Orcamentos
        //----------------------------------------------------------------------------------------------------
        try (BufferedReader br = new BufferedReader(new FileReader(pathOrcamentos))) {
            String line = br.readLine();
            line = br.readLine();
            while(line != null) {
                String[] vetor = line.split(",");
                String userId = vetor[0];
                String descricao = vetor[1];
                String modelo = vetor[2];
                double valorTotal = Double.parseDouble(vetor[3]);
                int tempoTotalInstalacao = Integer.parseInt(vetor[4]);
                int numPaineis = Integer.parseInt(vetor[5]);
                int retorno = Integer.parseInt(vetor[6]);
                double ocupacao = Double.parseDouble(vetor[7]);

                Orcamento orca = new Orcamento(descricao, modelo, valorTotal, tempoTotalInstalacao, numPaineis, retorno, ocupacao);
                for(Utilizador user : getListaUtilizadores()) {
                    if(user.getUserId().equals(userId)) {
                        user.getListaOrcamentos().add(orca);
                    }
                }

                line = br.readLine();
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public String readPassword() {
        Console console = System.console();
        if (console == null) {
            return readPasswordSwing();
        }
        char[] passwordArray = console.readPassword();
        return new String(passwordArray);
    }

    public String readPasswordSwing() {
        JPasswordField pf = new JPasswordField();
        int okCxl = JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (okCxl == JOptionPane.OK_OPTION) {
            return new String(pf.getPassword());
        }
        return null;
    }
}//fim da classe

