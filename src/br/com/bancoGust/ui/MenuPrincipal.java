package br.com.bancoGust.ui;
import br.com.bancoGust.core.contaPessoaJuridica;
import br.com.bancoGust.core.ContaPessoaFisica;

import java.util.Scanner;

public class MenuPrincipal {
    public String exibirMenuPrincipal() {
        int opcao;
        String nome;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("Bem vindo ao bancoGust 2.0 \nO que deseja fazer: \n[1]Criar conta \n[2]Entrar na conta \n[3]Sair \nSelecione a opção desejada: ");
            opcao = teclado.nextInt();
            teclado.nextLine();
            switch (opcao) {
                case 1:
                    System.out.println("Que tipo de conta deseja criar? \n[1]Pessoa Física  \n[2]Pessoa Jurídica \nSelecione a opção desejada: ");
                    int tipoConta = teclado.nextInt();
                    teclado.nextLine();

                    if (tipoConta == 1) {
                        ContaPessoaFisica clienteFisico = new ContaPessoaFisica(null, null);
                        System.out.println(clienteFisico.criarPessoaFisica());
                    } else if (tipoConta == 2) {
                        contaPessoaJuridica clienteJuridico = new contaPessoaJuridica(null, null, null);
                        System.out.println(clienteJuridico.criarContaJuridica());
                    } else {
                        System.out.println("Opção inválida para tipo de conta.");
                    }
                    break;

                case 2:
                    System.out.println("Digite o seu (cpf ou cnpj): ");
                    nome = teclado.nextLine();
                    if (nome.length() == 18){
                        if (contaPessoaJuridica.cnpjExiste(nome)){
                            MenuPessoaJuridica.exibirPessoaJuridica();

                        }
                    } else if (nome.length() == 14) {
                        if(ContaPessoaFisica.pequenaGambiarraPF(nome)){
                            MenuPessoaFisica.exibirPessoaFisica();
                        }

                    } else {
                        System.out.println("ERRO! Você não digitou um CPF  ou CNPJ válido");
                    } break;

                case 3:
                    System.out.println("Obrigado por usar nossos serviços :). \nDesconectando....");
                    break;

                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        } while (opcao != 3);

        teclado.close();
        return "";
    }
}
