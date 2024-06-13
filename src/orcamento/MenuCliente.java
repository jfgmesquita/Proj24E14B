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
                System.out.println("Logado como: " + email + "ID: " + user.getUserId());

                do {
                    System.out.println("Como podemos ajudá-lo?\n"
                    				+ "1) Consultar dados dos painéis\n"
                    				+ "2) Fazer um orçamento para a instalação de painéis\n"
                    				+ "3) Consultar os meus orçamentos\n"
                                    + "4) Comparar orçamentos\n"
                    				+ "0) Log out");
                    try {
                        option = input.nextInt();
                    }
                    catch (Exception e) {
                        System.out.println("\nPor favor, introduza um número válido ou '0' para sair da sua conta.");
                        option = -1;
                    }
                    input.nextLine();
                } while (option < 0 || option > 4);

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
                        		try {
                                    panelOption = input.nextInt();
                                }
                                catch (Exception e) {
                                    System.out.println("\nEscolha uma dos painéis apresentados.");
                                    panelOption = 0;
                                }
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

                        case 0:
                            System.out.println("A sair da sua conta...");
                            break;
                    }

                    do {
                        System.out.println("Como podemos ajudá-lo?\n"
                                + "1) Consultar dados dos painéis\n"
                                + "2) Fazer um orçamento para a instalação de painéis\n"
                                + "3) Consultar os meus orçamentos\n"
                                + "4) Comparar orçamentos\n"
                                + "0) Log out");
                        try {
                            option = input.nextInt();
                        }
                        catch (Exception e) {
                            System.out.println("\nPor favor, introduza um número válido ou '0' para sair da sua conta.");
                            option = -1;
                        }
                        input.nextLine();
                    } while (option < 0 || option > 4);
                }
            }
    	}
//        input.close();
    }

}
