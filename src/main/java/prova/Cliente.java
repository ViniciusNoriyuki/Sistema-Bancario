package prova;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.rmi.UnexpectedException;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private String nome;
    private Integer numeroConta;
    private String tipoConta = "";
    private Double saldo = 0.0;

    public void inserirCliente(ArrayList<Cliente> lista){
        Scanner scanner = new Scanner(System.in);
        Cliente cliente = new Cliente();
        boolean flag;
        do{
            try{
                System.out.println("Digite o nome do cliente: ");
                cliente.setNome(scanner.next());

                System.out.println("Digite o numero da conta do cliente: ");
                cliente.setNumeroConta(scanner.nextInt());

                lista.add(cliente);
                flag = true;
            }catch (InputMismatchException e){
                System.out.println("Entrada invalida. Erro " + e + ". Digite os dados novamente.");
                scanner.nextLine();
                flag = false;
            }
        }while (!flag);
    }

    public void imprimirTodosClientes(ArrayList<Cliente> lista){
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("Cliente ID [" + i + "] ####################");
            System.out.println("Nome: " + lista.get(i).getNome());
            System.out.println("Numero da conta: " + lista.get(i).getNumeroConta());
            System.out.println("Tipo de conta: " + lista.get(i).getTipoConta());
            System.out.println("Saldo em conta: R$" + lista.get(i).getSaldo());
        }
    }

    public void excluirCliente(ArrayList<Cliente> lista){
        Scanner scanner = new Scanner(System.in);
        if (verificaClientes(lista)){
            System.out.println("Digite o ID do cliente que deseja excluir: ");
            int id = scanner.nextInt();
            if (verificaExisteConta(lista, id)){
                lista.remove(id);
                System.out.println("Cliente excluido com sucesso!");
            }
        }
    }

    public void abrirTipoConta(ArrayList<Cliente> lista){
        Scanner scanner = new Scanner((System.in));
        boolean flag;
        if (verificaClientes(lista)){
            System.out.println("Digite o ID do cliente que deseja criar a conta: ");
            int id = scanner.nextInt();
            if (verificaExisteConta(lista, id)) {
                do{
                    System.out.println("Digite - [0] para: Criar Conta Corrente");
                    System.out.println("Digite - [1] para: Criar Conta Poupanca");
                    int conta = scanner.nextInt();
                    if (conta == 0){
                        lista.get(id).setTipoConta("Conta Corrente");
                        flag = true;
                    }
                    else if (conta == 1){
                        lista.get(id).setTipoConta("Poupanca");
                        flag = true;
                    }
                    else{
                        System.out.println("Opcao invalida, digite novamente!");
                        flag = false;
                    }
                }while (!flag);
            }
        }
    }

    public void saqueOuDeposito(ArrayList<Cliente> lista){
        Scanner scanner = new Scanner((System.in));
        if (verificaClientes(lista)){
            System.out.println("Digite o ID do cliente que deseja realizar a movimentacao: ");
            int id = scanner.nextInt();
            if (lista.get(id).getTipoConta() != ""){
                if (verificaExisteConta(lista, id)){
                    System.out.println("Digite - [0] para: Saque");
                    System.out.println("Digite - [1] para: Deposito");
                    int movimentacao = scanner.nextInt();
                    if (movimentacao == 0){
                        System.out.println("Digite o valor para saque: R$");
                        double valorSaque = scanner.nextDouble();
                        if (lista.get(id).getSaldo() >= valorSaque)
                            lista.get(id).setSaldo(lista.get(id).getSaldo() - valorSaque);
                        else
                            System.out.println("Saldo em conta insuficiente.");
                    }
                    else if (movimentacao == 1){
                        System.out.println("Digite o valor para deposito: R$");
                        double valorDeposito = scanner.nextDouble();
                        lista.get(id).setSaldo(lista.get(id).getSaldo() + valorDeposito) ;
                    }
                }
            }
            else
                System.out.println("Cliente nao possui conta criada!");
        }
    }

    public boolean verificaExisteConta(ArrayList<Cliente> lista, int id){
        if (lista.size() > id)
            return true;
        else{
            System.out.println("Conta nao existente!");
            return false;
        }
    }

    public boolean verificaClientes(ArrayList<Cliente> lista){
        if (lista.size() == 0){
            System.out.println("Nao existem clientes cadastrados!");
            return false;
        }
        return true;
    }
}
