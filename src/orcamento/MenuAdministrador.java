package orcamento;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuAdministrador {
    public static void mainAdm(Gerir gestor, String email)
    {
    	Scanner input = new Scanner(System.in);
    	int option;
		int limite;
    	
		ArrayList<Utilizador> listaUtilizadores = new ArrayList<>(gestor.getListaUtilizadores());
        for(Utilizador uti:listaUtilizadores) {
        	if(uti.getEmail().equals(email)) {
				Administrador adm = (Administrador) uti;
        		System.out.println("Olá " + adm.getNome());
        		do {
					if(adm.getIsManager()){
						System.out.println("Que operação deseja realizar?\n"
								+ "1) Registar painel\n"
								+ "2) Alterar preço unitário de um painel\n"
								+ "3) Alterar tempo de instalação de um painel\n"
								+ "4) Registar novo administrador\n"
								+ "0) Log out");
						limite = 4;
					} else {
						System.out.println("Que operação deseja realizar?\n"
								+ "1) Registar painel\n"
								+ "2) Alterar preço unitário de um painel\n"
								+ "3) Alterar tempo de instalação de um painel\n"
								+ "0) Log out");
						limite = 3;
					}
		        	option = input.nextInt();
		        	input.nextLine();
        		} while(option < 0 || option > limite);
	        	
	        	while(option != 0) {
	        		String marca;
	        		String modelo;
	        		double precoUnitario;
	        		double tempoInstalacao;
	        		double producaoKwh;
					int modeloOption;
	        		
	        		switch(option) {
		        		case 1:
		        			do {
		        				System.out.print("Marca: ");
		        				marca = input.nextLine();
		        			} while(marca.equals(""));
		        			
		        			do {
		        				System.out.print("Modelo: ");
		        				modelo = input.nextLine();
		        			} while(modelo.equals(""));
		        			
		        			do {
		        				System.out.print("Preço Unitário: ");
		        				precoUnitario = input.nextDouble();
		        				input.nextLine();
		        			} while(precoUnitario < 0);
		        			
		        			do {
		        				System.out.print("Tempo de Instalação: ");
		        				tempoInstalacao = input.nextDouble();
		        				input.nextLine();
		        			} while(tempoInstalacao < 0);
		        			
		        			do {
		        				System.out.print("Produção(Em Kwh): ");
		        				producaoKwh = input.nextDouble();
		        				input.nextLine();
		        			} while(producaoKwh < 0);

							double medida;
							do{
								System.out.print("Medida(Em m2): ");
								medida = input.nextDouble();
								input.nextLine();
							} while(medida < 0);
		        			
		        			
		        			for(Painel painel : gestor.getListaPaineis()) {
		        				if(painel.getModelo().equals(modelo)) {
		        					System.out.println("Já existe um painel com esse modelo!\n"
		        										+ "********************\n");
		        					break;
		        				}
							}
		        			
		        			gestor.registarPainel(marca, modelo, precoUnitario, tempoInstalacao, producaoKwh, medida);
		        			
		        			System.out.println("Painel adicionado!\n"
		        								+ "********************\n");
		        			break;
							
		        		case 2:
						gestor.mostrarPaineis();

		        			do {
		        				System.out.print("\nDeseja alterar o preço unitário de qual painel?:");
		        				modeloOption = input.nextInt();
								input.nextLine();
		        			} while(modeloOption <= 0 || modeloOption > gestor.getListaPaineis().size());

							modelo = gestor.getListaPaineis().get(modeloOption-1).getModelo();
		        			
		        			do {
		        				System.out.print("\nNovo preço unitário do painel " + modelo + ":");
		        				precoUnitario = input.nextDouble();
		        				input.nextLine();
		        			} while(precoUnitario < 0);
		        			
		        			gestor.alterarPrecoUnitario(modelo, precoUnitario);
		        			
		        			System.out.println("Painel alterado!\n"
    								+ "********************\n");
		        			break;
		        			
		        		case 3:
						gestor.mostrarPaineis();
		        			do {
		        				System.out.print("\nDeseja alterar o tempo de instalação de qual painel?:");
		        				modeloOption = input.nextInt();
								input.nextLine();
		        			} while(modeloOption <= 0 || modeloOption > gestor.getListaPaineis().size());

		        			modelo = gestor.getListaPaineis().get(modeloOption-1).getModelo();

		        			do {
		        				System.out.print("\nNovo tempo de instalação do painel " + modelo + ":");
		        				tempoInstalacao = input.nextDouble();
		        				input.nextLine();
		        			} while(tempoInstalacao < 0);
		        			
		        			gestor.alterarTempoInstalacao(modelo, tempoInstalacao);
		        			
		        			System.out.println("Painel alterado!\n"
    								+ "********************\n");
		        			break;
						case 4:
							
							String nome;
							do {
								System.out.println("Nome ('Cancelar' para cancelar o registo): ");
								nome = input.nextLine();
			
								if(nome.equalsIgnoreCase("Cancelar")) {
									gestor.cancelarOperacao();
								}
							} while(nome.equals(""));
							
							do {
								System.out.println("Email ('Cancelar' para cancelar o registo): ");
								email = input.nextLine();
			
								if(email.equalsIgnoreCase("Cancelar")) {
									gestor.cancelarOperacao();
								}
							} while(!gestor.validarEmail(email) || gestor.pesquisarEmail(email) != -1);

							String password;
							do {
								System.out.println("Palavra-passe ('Cancelar' para cancelar o registo):\n"
										+ "-Pelo menos 8 caracteres.\n"
										+ "-Pelo menos 1 letra maiúscula.\n"
										+ "-Pelo menos 1 letra minúscula.\n"
										+ "-Pelo menos 1 número\n"
										+ "-Pelo menos 1 carácter especial (@#$%&)\n");
								password = gestor.readPassword();
			
								if(password.equalsIgnoreCase("Cancelar")) {
									gestor.cancelarOperacao();
								}
							} while(!gestor.validarSenha(password));

							String isManagerString;
							do{
								System.out.print("É gerente? (S)im ou (N)ão: ");
								isManagerString = input.nextLine();
							} while(!isManagerString.equalsIgnoreCase("S") && !isManagerString.equalsIgnoreCase("N"));

							if (isManagerString.equalsIgnoreCase("S")) {
								Administrador newAdm = new Administrador(nome, email, password, true);
								gestor.inserirUtilizador(newAdm);
							} else {
								Administrador newAdm = new Administrador(nome, email, password, false);
								gestor.inserirUtilizador(newAdm);
							}
	        		}
	        		
	        		
	        		do {
			        	if(adm.getIsManager()){
							System.out.println("Que operação deseja realizar?\n"
									+ "1) Registar painel\n"
									+ "2) Alterar preço unitário de um painel\n"
									+ "3) Alterar tempo de instalação de um painel\n"
									+ "4) Registar novo administrador\n"
									+ "0) Log out");
							limite = 4;
						} else {
							System.out.println("Que operação deseja realizar?\n"
									+ "1) Registar painel\n"
									+ "2) Alterar preço unitário de um painel\n"
									+ "3) Alterar tempo de instalação de um painel\n"
									+ "0) Log out");
							limite = 3;
						}
			        	option = input.nextInt();
			        	input.nextLine();
	        		} while(option < 0 || option > 3);
	        	}
        	}
        }
//      input.close();
    }
}
