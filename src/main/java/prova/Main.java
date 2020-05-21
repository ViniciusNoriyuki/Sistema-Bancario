package prova;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cliente cliente = new Cliente();
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        int op = 1;
        do{
            System.out.println("########## Sistema Bancario ###########");
            System.out.println("[1] - Inserir cliente;");
            System.out.println("[2] - Excluir cliente;");
            System.out.println("[3] - Abrir conta corrente ou poupanca;");
            System.out.println("[4] - Realizar saque ou deposito em conta;");
            System.out.println("[5] - Verificar lista de clientes;");
            System.out.println("[0] - Sair.");
            System.out.println("#################### MENU ####################");
            System.out.println("Digite a opcao: ");
            try{
                op = scanner.nextInt();
                switch (op) {
                    case 1:
                        //Inserir cliente
                        cliente.inserirCliente(listaClientes);
                        break;
                    case 2:
                        //Excluir cliente
                        cliente.imprimirTodosClientes(listaClientes);
                        cliente.excluirCliente(listaClientes);
                        break;
                    case 3:
                        //Abrir conta corrente ou poupanca
                        cliente.imprimirTodosClientes(listaClientes);
                        cliente.abrirTipoConta(listaClientes);
                        break;
                    case 4:
                        //Saque ou deposito
                        cliente.imprimirTodosClientes(listaClientes);
                        cliente.saqueOuDeposito(listaClientes);
                        break;
                    case 5:
                        if (listaClientes.size() != 0)
                            cliente.imprimirTodosClientes(listaClientes);
                        else
                            System.out.println("Nao existem clientes cadastrados!");
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opcao invalida. Digite novamente.");
                        break;
                }
            }catch (InputMismatchException e){
                System.out.println("Opcao invalida. Erro " + e + ". Digite a opcao novamente.");
                scanner.nextLine();
            }
        }while (op != 0);
    }
}
