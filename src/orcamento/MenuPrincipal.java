package orcamento;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        String password;
        
        String path = "C:\\Users\\jolit\\OneDrive\\Ambiente de Trabalho\\Faculdade\\1.º Ano (UPT)\\2.º Semestre\\Programacao Orientada a Objetos (6 ECTS)\\eclipse-workspace\\Proj24E14B\\paineis.txt";
        String pathAdm = "C:\\Users\\jolit\\OneDrive\\Ambiente de Trabalho\\Faculdade\\1.º Ano (UPT)\\2.º Semestre\\Programacao Orientada a Objetos (6 ECTS)\\eclipse-workspace\\Proj24E14B\\adms.txt";
        // String path = "C:\\Faculdade\\Projeto SI + POO\\Proj24E14B\\Proj24E14B - Incremento 1\\paineis.txt";
        // String pathAdm = "C:\\Faculdade\\Projeto SI + POO\\Proj24E14B\\Proj24E14B - Incremento 1\\adms.txt";

        //Leitura de admins
        //----------------------------------------------------------------------------------------------------
        
        try (BufferedReader br = new BufferedReader(new FileReader(pathAdm))) {
        	
        	String line = br.readLine();
        	line = br.readLine();
        	while(line != null) {
        		String[] vetor = line.split(",");
        		String nome = vetor[0];
        		email = vetor[1];
        		password = vetor[2];
        		boolean isManager = Boolean.parseBoolean(vetor[3]);
        		
        		Administrador newAdmin = new Administrador(nome,email,password,isManager);
        		gestor.getListaUtilizadores().add(newAdmin);
        		
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
        		String[] vetor = line.split(",");
        		String marca = vetor[0];
        		String modelo = vetor[1];
        		double precoUnitario = Double.parseDouble(vetor[2]);
        		double tempoInstalacao = Double.parseDouble(vetor[3]);
        		double producaoKwh = Double.parseDouble(vetor[4]);
        		
        		Painel newPainel = new Painel(marca, modelo, precoUnitario, tempoInstalacao, producaoKwh);
        		gestor.getListaPaineis().add(newPainel);
        		
        		line = br.readLine();
        	}
        	
        } catch (IOException e) {
        	System.out.println("Error: " + e.getMessage());
        }
        
        //----------------------------------------------------------------------------------------------------

        do {
            System.out.println("Seja bem-vindo! É um prazer tê-lo connosco.");
            System.out.println("1) Fazer login");
            System.out.println("2) Criar um perfil");
            System.out.println("0) Sair do programa");
            signOption = input.nextInt();
            input.nextLine();
        } while(signOption < 0 || signOption > 2);

        while(signOption != 0) {
        	if(signOption == 1) {
        		boolean loginRealizado = false;
        		String tipoUser = "";
        		while(!loginRealizado) {
        			do {
        				System.out.print("Email: ");
        				email = input.nextLine();
        			}while(!gestor.validarEmail(email));
        			
        			do {
        				System.out.print("Senha: ");
        				password = input.nextLine();
        			} while(password.isEmpty()); //gestor.validarSenha(password)
        			
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
        		int consumoUltimoMes;
        		int pagamentoUltimoMes;
        		
        		
        		do {
        			System.out.println("Nome: ");
        			nome = input.nextLine();
        		} while(nome.equals(""));
        		
        		do {
        			System.out.println("Email: ");
        			email = input.nextLine();
        		} while(!gestor.validarEmail(email) || gestor.pesquisarEmail(email) != -1);
        		
        		do {
        			System.out.println("Palavra-passe:\n"
        					+ "-Pelo menos 8 caracteres.\n"
        					+ "-Pelo menos 1 letra maiúscula.\n"
        					+ "-Pelo menos 1 letra minúscula.\n"
        					+ "-Pelo menos 1 número\n"
        					+ "-Pelo menos 1 carácter especial (@#$%&)\n");
        			password = input.nextLine();
        		} while(!gestor.validarSenha(password));
        		
        		do {
        			System.out.println("Quantos kWh foram consumidos na sua residência no último mês?: ");
        			consumoUltimoMes = input.nextInt();
        			input.nextLine();
        		} while(consumoUltimoMes < 0);
        		
        		do {
        			System.out.println("Qual foi o valor da fatura paga no último mês?: ");
        			pagamentoUltimoMes = input.nextInt();
        			input.nextLine();
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
                signOption = input.nextInt();
                input.nextLine();
            } while(signOption < 0 || signOption > 2);
        }
        input.close();
    }

}