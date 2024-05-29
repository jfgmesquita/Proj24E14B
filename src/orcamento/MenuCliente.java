/**
 *
 */
package orcamento;

import java.util.Scanner;

/**
 *
 */
public class MenuCliente {


    /**
     * @param args
     */
    public static void mainClient(Gerir gestor, String email) {
        Scanner input = new Scanner(System.in);

        int option;

        for(Utilizador user:gestor.getListaUtilizadores()) {
            if(user.getEmail().equals(email)) {
                System.out.println("Logado como: " + email);

                do {
                    System.out.println("Como podemos ajudá-lo?\n"
                    				+ "1) Consultar dados dos painéis\n"
                    				+ "2) Fazer um orçamento para a instalação de painéis\n"
                    				+ "3) Consultar os meus orçamentos\n"
                                    + "4) Comparar orçamentos\n"
                    				+ "0) Log out");
                    option = input.nextInt();
                    input.nextLine();
                } while (option < 0 || option > 3);

                while(option != 0) {
                    switch(option) {
                        case 1:
                        	gestor.mostrarPaineis();

                            System.out.println("\n********************\n");
                            break;

                        case 2:
                        	String descricao;
                        	int panelOption;
                        	
                        	System.out.println("Vamos fazer o seu orçamento!");
                        	do {
                        		System.out.print("Digite uma descrição para este orçamento: ");
                        		descricao = input.nextLine();
                        	} while(descricao.equals(""));
                        	
                        	do {
                        		gestor.mostrarPaineis();
                        		System.out.print("Qual é o modelo de painel que deseja para o seu orçamento?: ");
                        		panelOption = input.nextInt();
                        		input.nextLine();
                        	} while(panelOption < 1 || panelOption > gestor.getListaPaineis().size());
                        	
                        	gestor.gerarOrcamento(user.getUserId(), descricao, panelOption);
                        	
                        	System.out.println("Orçamento adicionado!\n"
                        	
    								+ "********************\n");
                        	break;
                        case 3:
                        	gestor.mostrarOrcamentos(user.getUserId());
                        	
                        	System.out.println("\n********************\n");
                            break;

                        case 4:
                            gestor.compararOrcamentos(user.getUserId());

                            System.out.println("\n********************\n");
                            break;
                    }

                    do {
                        System.out.println("Como podemos ajudá-lo?\n"
                                + "1) Consultar dados dos painéis\n"
                                + "2) Fazer um orçamento para a instalação de painéis\n"
                                + "3) Consultar os meus orçamentos\n"
                                + "4) Comparar orçamentos\n"
                                + "0) Log out");
                        option = input.nextInt();
                        input.nextLine();
                    } while (option < 0 || option > 42);
                }
            }
    	}
//        input.close();
    }

}
