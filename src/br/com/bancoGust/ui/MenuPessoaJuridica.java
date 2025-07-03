package br.com.bancoGust.ui;
import br.com.bancoGust.core.contaPessoaJuridica;

import java.util.Scanner;


import java.util.Scanner;

public class MenuPessoaJuridica {
    public static void exibirPessoaJuridica() {
        contaPessoaJuridica pj = contaPessoaJuridica.carregarDadosJuridico();
        Scanner teclado = new Scanner(System.in);
        int opcao;
        int valor;
        boolean sair = true;
        do {
            System.out.println("BancoGust 2.0 " +
                        "\n [1]Ver dados" +
                        "\n [2]Declarar Renda Anual" +
                        "\n [3]Depositar" +
                        "\n [4]Sacar" +
                        "\n [5]Pagar Imposto Anual" +
                        "\n [0]Sair" +
                        "\n Selecione a Opção desejada: ");

                opcao = teclado.nextInt();
                teclado.nextLine();
                switch (opcao) {
                    case 1:
                        System.out.println(pj);
                        break;
                    case 2:
                        System.out.println("Digite o valor do lucro deste ano: ");
                        valor = teclado.nextInt();
                        teclado.nextLine();
                        pj.faturaAnual(valor);
                        break;
                    case 3:
                        System.out.println("Digite o valor do depósito: ");
                        pj.Depositar(valor = teclado.nextInt());
                        teclado.nextLine();
                        break;
                    case 4:
                        System.out.println("Digite o valor do saque: ");
                        pj.sacar(valor = teclado.nextInt());
                        teclado.nextLine();
                        break;
                    case 5:
                        do {
                            System.out.println("Digite o valor da parcela (max 12 vezes) \nDigite o valor da parcela: ");
                            valor = teclado.nextInt();
                            teclado.nextLine();
                            if (valor > 12) {
                                System.out.println("ERRO! - opção inválida, você pode parcelar em no máximo 12 vezes! ");
                            } else {
                                System.out.println("Parcela realizada com sucesso! \n Valor mensal a pagar: " + pj.calcularParcelaImposto(valor) + "R$");
                                sair = true;
                            }
                        } while (sair == false);
                    case 0:
                        System.out.println("Saindo do sistema.... \nObrigado por usar nossos serviços");
                        break;
                    default:
                        System.out.println("Opção inválida, tente novamente");

                }
            } while (opcao != 0);
        }
}
