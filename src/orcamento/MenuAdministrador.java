package orcamento;

import java.util.Scanner;

public class MenuAdministrador {
    public static void mainAdm(Gerir gestor, String email)
    {
    	Scanner input = new Scanner(System.in);
    	int option;
    	
        for(Utilizador adm:gestor.getListaUtilizadores()) {
        	if(adm.getEmail().equals(email)) {
        		System.out.println("Olá " + adm.getNome());
        		do {
		        	System.out.println("Que operação deseja realizar?\n"
		        					+ "1) Registar painel\n"
		        					+ "2) Alterar preço unitário de um painel\n"
		        					+ "3) Alterar tempo de instalação de um painel\n"
		        					+ "0) Log out");
		        	option = input.nextInt();
		        	input.nextLine();
        		} while(option < 0 || option > 3);
	        	
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
		        			
		        			gestor.registarPainel(marca, modelo, precoUnitario, tempoInstalacao, producaoKwh);
		        			
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
	        		}
	        		
	        		
	        		do {
			        	System.out.println("Que operação deseja realizar?\n"
			        					+ "1) Registar painel\n"
			        					+ "2) Alterar preço unitário de um painel\n"
			        					+ "3) Alterar tempo de instalação de um painel\n"
			        					+ "0) Log out");
			        	option = input.nextInt();
			        	input.nextLine();
	        		} while(option < 0 || option > 3);
	        	}
        	}
        }
//      input.close();
    }
}
