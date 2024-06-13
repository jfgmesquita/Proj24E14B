package orcamento;

import java.util.Scanner;


/**
 *
 */
public class MenuPrincipal {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Gerir gestor = new Gerir();
        
        Scanner input = new Scanner(System.in);
        
        int signOption;
        String email = "";
        String password = "";
        
		// Localização dos ficheiros txt

        String path = "C:\\Users\\jolit\\OneDrive\\Ambiente de Trabalho\\Faculdade\\1o Ano\\2o Semestre\\Programacao Orientada a Objetos (6 ECTS)\\eclipse-workspace\\Proj24E14B\\paineis.txt";
        String pathAdm = "C:\\Users\\jolit\\OneDrive\\Ambiente de Trabalho\\Faculdade\\1o Ano\\2o Semestre\\Programacao Orientada a Objetos (6 ECTS)\\eclipse-workspace\\Proj24E14B\\adms.txt";
		String pathClientes = "C:\\Users\\jolit\\OneDrive\\Ambiente de Trabalho\\Faculdade\\1o Ano\\2o Semestre\\Programacao Orientada a Objetos (6 ECTS)\\eclipse-workspace\\Proj24E14B\\clientes.txt";
        String pathOrcamentos = "C:\\Users\\jolit\\OneDrive\\Ambiente de Trabalho\\Faculdade\\1o Ano\\2o Semestre\\Programacao Orientada a Objetos (6 ECTS)\\eclipse-workspace\\Proj24E14B\\orcamentos.txt";

		// String path = "C:\\Programação\\Repositórios clonados\\Proj24E14B\\paineis.txt";
        // String pathAdm = "C:\\Programação\\Repositórios clonados\\Proj24E14B\\adms.txt";
        // String pathClientes = "C:\\Programação\\Repositórios clonados\\Proj24E14B\\clientes.txt";
        // String pathOrcamentos = "C:\\Programação\\Repositórios clonados\\Proj24E14B\\orcamentos.txt";

        gestor.lerFicheiros(path, pathAdm, pathClientes, pathOrcamentos);

		// Inicio do programa
        //----------------------------------------------------------------------------------------------------

        do {
            System.out.println("Seja bem-vindo! É um prazer tê-lo connosco.");
            System.out.println("1) Fazer login");
            System.out.println("2) Criar um perfil");
            System.out.println("0) Sair do programa");
            try {
				signOption = input.nextInt();
			}
			catch (Exception e) {
				System.out.println("\nPor favor, introduza um número válido ou '0' para sair do programa.");
				signOption = -1;
			}
			input.nextLine();
        } while(signOption < 0 || signOption > 2);

        while(signOption != 0) {
        	if(signOption == 1) {
        		boolean loginRealizado = false;
        		String tipoUser = "";
        		while(!loginRealizado) {
        			do {
        				System.out.print("Email ('Cancelar' para cancelar o login): ");
        				email = input.nextLine();

						if(email.equalsIgnoreCase("Cancelar")) {
							gestor.cancelarOperacao();
						}
        			}while(!gestor.validarEmail(email));

        			
					do {

						System.out.print("Senha('Cancelar' para cancelar o login): ");
						password = gestor.readPassword();


						if(password.equalsIgnoreCase("Cancelar")) {
							gestor.cancelarOperacao();
						}
					} while(password.isEmpty());

        			
        			tipoUser = gestor.fazerLogin(email, password);
        			
        			if(!tipoUser.isEmpty()){
        				loginRealizado = true;
        			}
        		}
        		System.out.println("Login realizado!\n");
        		
        		if(tipoUser.equals("Administrador")){
        			MenuAdministrador.mainAdm(gestor, email);
        		} else{
        			MenuCliente.mainClient(gestor, email);
        		}
        		
        	} else {
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
        		
				int consumoUltimoMes = -1;
        		do {
					System.out.println("Quantos kWh foram consumidos na sua residência no último mês? ('Cancelar' para cancelar o registo): ");
					String entrada = input.nextLine();
				
					if (entrada.equalsIgnoreCase("Cancelar")) {
						gestor.cancelarOperacao();
					}
				
					try {
						consumoUltimoMes = Integer.parseInt(entrada);
					} catch (NumberFormatException e) {
						System.out.println("Por favor, insira um número válido ou 'Cancelar' para cancelar o registo.");
						consumoUltimoMes = -1;
					}
				} while(consumoUltimoMes < 0);
        		
				int pagamentoUltimoMes = -1;
        		do {
					System.out.println("Quanto pagou na sua última fatura? ('Cancelar' para cancelar o registo): ");
					String entrada = input.nextLine();

					if (entrada.equalsIgnoreCase("Cancelar")) {
						gestor.cancelarOperacao();
					}

					try {
						pagamentoUltimoMes = Integer.parseInt(entrada);
					} catch (NumberFormatException e) {
						System.out.println("Por favor, insira um número válido ou 'Cancelar' para cancelar o registo.");
						pagamentoUltimoMes = -1;
					}
				} while(pagamentoUltimoMes < 0);
        		
        		Cliente newCliente = new Cliente(nome, email, password, consumoUltimoMes, pagamentoUltimoMes);
        		gestor.inserirUtilizador(newCliente);
        		
        		MenuCliente.mainClient(gestor, gestor.getListaUtilizadores().getLast().getEmail());
        	}
        	
        	do {
                System.out.println("Seja bem-vindo! é um prazer tê-lo conosco.");
                System.out.println("1) Fazer login");
                System.out.println("2) Criar um perfil");
                System.out.println("0) Sair do programa");
				try {
					signOption = input.nextInt();
				}
				catch (Exception e) {
					System.out.println("\nPor favor, introduza um número válido ou '0' para sair do programa.");
					signOption = -1;
				}
                input.nextLine();
            } while(signOption < 0 || signOption > 2);
        }
        input.close();
    }

}