package br.com.bancoGust.ui;

import java.util.Scanner;
import java.util.InputMismatchException;
import br.com.bancoGust.core.ContaPessoaFisica;
import br.com.bancoGust.core.Estado;
import br.com.bancoGust.core.TipoConta;

public class MenuPessoaFisica {
    public static void exibirPessoaFisica() {
        try {
            int opcao;
            int valor;
            Scanner teclado = new Scanner(System.in);
            ContaPessoaFisica c1 = ContaPessoaFisica.CarregarDadosPessoaFisica();

            do {
                System.out.println("Oque você deseja fazer? :" +
                        "\n [1] Ver Conta" +
                        "\n [2] Depositar" +
                        "\n [3] Sacar  " +
                        "\n [4] Usar crédito" +
                        "\n [5] Fatura" +
                        "\n [6] Mudar dados" +
                        "\n [7] Histórico de Transações" +
                        "\n [0] sair " +
                        "\n Digite a opção desejada: ");
                opcao = teclado.nextInt();

                switch (opcao) {
                    case 1:
                        System.out.println(c1);
                        break;
                    case 2:
                        if (c1.getEstadoConta() != Estado.INATIVO && c1.getEstadoConta() != Estado.BLOQUEADO) {
                            System.out.println("Valor do depósito: ");
                            valor = teclado.nextInt();
                            c1.Depositar(valor);
                            c1.listaDepositos(valor);
                        } else {
                            System.out.println("Impossível depositar em uma conta inativa ou bloqueada, reative ou desbloqueie sua conta para realizar o depósito. ");
                        }
                        break;
                    case 3:
                        if (c1.getEstadoConta() != Estado.INATIVO && c1.getEstadoConta() != Estado.BLOQUEADO){
                            System.out.println("Valor do saque :");
                            valor = teclado.nextInt();
                            c1.sacar(valor);
                            c1.listaSaques(valor);
                            if (c1.getSaldo() < valor) {
                                System.out.println("Erro! - Saldo insuficiente");
                            } else if (c1.getRealizacaoSaque()) {
                                System.out.println("Saque realizado com Sucesso! \n Saldo atual: " + c1.getSaldo());
                            }
                        } else {
                            System.out.println("Impossível sacar de uma conta inativa ou bloqueada, reative ou desbloqueie sua conta para realizar o saque. ");
                        }
                        break;
                    case 4:
                        System.out.println("Crédito disponível: " + c1.getCreditoDisponivel() + "\n Digite o valor que deseja usar: ");
                        valor = teclado.nextInt();
                        if (c1.getCreditoDisponivel() == 0) {
                            System.out.println("Erro! - Você não tem mais créditos disponíveis. Pague sua fatura para usá-los novamente.");
                        } else if (valor > c1.getCreditoDisponivel()) {
                            System.out.println("Erro! - Valor desejado maior do que seu limite.");
                        } else {
                            c1.usarCredito(valor);
                            System.out.println("Realizado com sucesso!");
                        }
                        break;

                    case 5:
                        System.out.println(" [1] ver fatura \n [2] Pagar fatura :");
                        opcao = teclado.nextInt();
                        if (opcao == 1){
                            System.out.println("Fatura atual: " + c1.getFatura());
                        } else if (opcao == 2){
                            System.out.println("Processando...");
                            if (c1.getSaldo() >= c1.getFatura()){
                                c1.pagarFatura();
                                System.out.println("Faatura paga com sucesso! ");
                            } else {
                                System.out.println("Saldo Insuficiente");
                            }
                        } else {
                            System.out.println("Opção inválida, tente novamente! ");
                        }
                        break;
                    case 6:
                        String senha;
                        teclado.nextLine();
                        System.out.println("Digite a senha de acesso para mudar informações de clientes: ");
                        senha = teclado.nextLine();
                        if (senha.equals("Admin")){
                            System.out.println("Acesso liberado, selecione a opção desejada:" +
                                    "\n [1] Mudar Nome" +
                                    "\n [2] Mudar Número da conta " +
                                    "\n [3] Mudar estado da conta:" +
                                    "\n [4] Mudar tipo de cliente " +
                                    "\nDigite a opção desejada: ");
                            opcao = teclado.nextInt();
                            teclado.nextLine();
                            switch (opcao){
                                case 1:
                                    if (c1.getEstadoConta().equals(Estado.ATIVO)) {
                                        String novoNome;
                                        System.out.println("Qual o novo nome do cliente " + c1.getNome() + ": ");
                                        novoNome = teclado.nextLine();
                                        c1.mudarNomeCliente(novoNome);
                                        System.out.println("O nome foi mudado com sucesso! ");
                                    } else if (c1.getEstadoConta().equals(Estado.BLOQUEADO)) {
                                        System.out.println("Impossível mudar os número de uma conta bloqueada");
                                    } else {
                                        System.out.println("Impossível mudar o número de uma Conta inativa");
                                    }
                                    break;
                                case 2:
                                    if (c1.getEstadoConta().equals(Estado.INATIVO)){
                                        System.out.println("Impossível mudar o número de uma Conta inativa");
                                    } else if (c1.getEstadoConta().equals(Estado.BLOQUEADO)){
                                        System.out.println("Impossível mudar os número de uma conta bloqueada");
                                    } else {
                                        System.out.println("Número da conta foi alterado com sucesso. ");
                                        c1.mudarNumeroConta();
                                    }
                                    break;
                                case 3:
                                    System.out.println("Para qual estado deseja mudar a conta: \n [1] Ativo" +
                                            "\n [2] Inativo" +
                                            "\n [3] Bloqueado" +
                                            "\n Digite a opção desejada: ");
                                    opcao = teclado.nextInt();
                                    teclado.nextLine();
                                    if (c1.getEstadoConta().equals(Estado.ATIVO) && opcao == 1){
                                        System.out.println("A conta já está ativa. ");
                                    } else if(c1.getEstadoConta().equals(Estado.ATIVO) || c1.getEstadoConta().equals(Estado.BLOQUEADO) && opcao == 2){
                                        System.out.println("A conta foi inativada com sucesso. ");
                                        c1.contaInativa();
                                    } else if (c1.getEstadoConta().equals(Estado.ATIVO) || c1.getEstadoConta().equals(Estado.INATIVO) && opcao == 3){
                                        System.out.println("A conta foi Bloqueada com sucesso. ");
                                        c1.bloquearConta();
                                    } else if (c1.getEstadoConta().equals(Estado.INATIVO) && opcao == 2){
                                        System.out.println("A conta já está inativa");
                                    } else if (c1.getEstadoConta().equals(Estado.BLOQUEADO) && opcao == 3){
                                        System.out.println("A conta já está bloqueada");
                                    } else if (c1.getEstadoConta().equals(Estado.INATIVO) || c1.getEstadoConta().equals(Estado.BLOQUEADO) && opcao == 1){
                                        System.out.println("Conta foi reativada com sucesso! ");
                                        c1.reativarConta();
                                    }
                                    else {
                                        System.out.println("Opção inválida, tente novamente! ");
                                    }
                                    break;
                                case 4:
                                    System.out.println("Para será o novo tipo de conta do cliente: " +
                                            "\n [1] Básico " +
                                            "\n [2] Preferencial " +
                                            "\n [3] Premium " +
                                            "\n [4] Private " +
                                            "\n Selecione a opção desejada: ");
                                    opcao = teclado.nextInt();
                                    teclado.nextLine();
                                    if (c1.getTipo().equals(TipoConta.BASICO) && opcao == 1){
                                        System.out.println("A conta já é básica, impossível altera-la ");

                                    } else if ((c1.getTipo().equals(TipoConta.PREMIUM)
                                            || c1.getTipo().equals(TipoConta.PRIVATE)
                                            ||  c1.getTipo().equals(TipoConta.PREFERENCIAL))  && opcao == 1){
                                        System.out.println("Conta alterada para Basic com sucesso! ");
                                        c1.setTipoBasic();




                                    } else if (c1.getTipo().equals(TipoConta.PREFERENCIAL) && opcao == 2){
                                        System.out.println("A conta já é preferencial, impossível altera-la ");

                                    } else if ((c1.getTipo().equals(TipoConta.PREMIUM)
                                            || c1.getTipo().equals(TipoConta.PRIVATE)
                                            ||  c1.getTipo().equals(TipoConta.BASICO))  && opcao == 2) {
                                        System.out.println("Conta alterada para preferencial com sucesso! ");
                                        c1.setTipoPreferencial();




                                    } else if (c1.getTipo().equals(TipoConta.PREMIUM) && opcao == 3) {
                                        System.out.println("A conta já é premium, impossível altera-la ");

                                    } else if ((c1.getTipo().equals(TipoConta.PREFERENCIAL)
                                            || c1.getTipo().equals(TipoConta.PRIVATE)
                                            || c1.getTipo().equals(TipoConta.BASICO))  && opcao == 3) {
                                        System.out.println("Conta alterada para premium com sucesso! ");
                                        c1.setTipoPremium();



                                    } else if (c1.getTipo().equals(TipoConta.PRIVATE) && opcao == 4) {
                                        System.out.println("A conta já é private, impossível altera-la ");


                                    }  else if ((c1.getTipo().equals(TipoConta.PREFERENCIAL)
                                            || c1.getTipo().equals(TipoConta.PREMIUM)
                                            ||  c1.getTipo().equals(TipoConta.BASICO))  && opcao == 4) {
                                        System.out.println("Conta alterada para private com sucesso! ");
                                        c1.setTipoPrivate();

                                    } else {
                                        System.out.println("Opção inválida, tente novamente! ");
                                    }
                                default:
                                    System.out.println("Opção inválida no menu de alteração.");
                                    break;

                            }
                            break;
                        } else {
                            System.out.println("Senha incorreta! ");
                        }
                    case 7:
                        if (c1.getEstadoConta() != Estado.INATIVO && c1.getEstadoConta() != Estado.BLOQUEADO){
                            System.out.println("Selecione a opção desejada:  " +
                                    "\n [1] Depósitos " +
                                    "\n [2] Saques " +
                                    "\n Digite a opção desejada: ");
                            opcao = teclado.nextInt();
                            teclado.nextLine();
                            switch (opcao){
                                case 1:
                                    System.out.println(c1.mostrarHistoricoDepositos());
                                    break;
                                case 2:
                                    System.out.println(c1.mostrarHistoricoSaques());
                                    break;
                                default:
                                    System.out.println("Opção inválida, tente novamente! ");
                            }
                        }
                    case 0:
                        System.out.println("Fechando o Sistema....");
                        break;
                    default:
                        System.out.println("Opção inválida, tente novamente! ");
                        break;
                }
            } while (opcao != 0);

            teclado.close();
        } catch (InputMismatchException e) {
            System.out.println("Erro! - Você deve digitar um número inteiro (sem casas decimais) e não é aceito entrada de caracteres alfabéticos. ");
        }
    }
}