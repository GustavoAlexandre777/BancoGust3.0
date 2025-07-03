
package br.com.bancoGust.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ContaPessoaFisica extends Cliente {
    private String cpf;
    private int limiteCredito;
    private int creditoDisponivel;
    private int fatura;
    private TipoConta tipo;
    private List<String> historicoDeposito = new ArrayList<>();
    private List<String> historicoSaque = new ArrayList<>();


    public ContaPessoaFisica(String nome, String cpf) {
        super(nome);
        this.cpf = cpf;
        this.fatura = 0;
        this.limiteCredito = 0;
        this.creditoDisponivel = 0;
        super.gerarNovoNumero();
    }

    @Override
    public void Depositar(int valor) {
        if (valor >= 1) {
            this.saldo += valor;

            if (!classificado) {
                classificarCliente(valor);
                classificado = true;
            }
        }
    }

    private void classificarCliente(double valor) {
        if (valor <= 1000) {
            this.tipo = TipoConta.BASICO;
            this.limiteCredito = 500;
        } else if (valor <= 5000) {
            this.tipo = TipoConta.PREFERENCIAL;
            this.limiteCredito = 2000;
        } else if (valor <= 10000) {
            this.tipo = TipoConta.PREMIUM;
            this.limiteCredito = 5000;
        } else {
            this.tipo = TipoConta.PRIVATE;
            this.limiteCredito = 15000;
        }
        this.creditoDisponivel = this.limiteCredito;
    }

    public void usarCredito(int valor) {
        if (valor <= creditoDisponivel) {
            fatura += valor;
            creditoDisponivel -= valor;
        }
    }

    public void pagarFatura() {
        this.saldo -= this.fatura;
        this.fatura = 0;
        this.creditoDisponivel = this.limiteCredito;
    }

    public void listaDepositos(int valor) {
        historicoDeposito.add(LocalDateTime.now() + " - " + valor);
    }

    public void listaSaques(int valor) {
        historicoSaque.add(LocalDateTime.now() + " - " + valor);
    }

    public String mostrarHistoricoSaques() {
        if (historicoSaque.isEmpty()) {
            return "Nenhum saque foi realizado ainda. ";
        } else {
            for (String registro : historicoSaque) {
                System.out.println(registro);
            }
        }
        return null;
    }

    public String mostrarHistoricoDepositos() {
        if (historicoDeposito.isEmpty()) {
            return "Nenhum saque foi realizado ainda. ";
        } else {
            for (String registro : historicoDeposito) {
                System.out.println(registro);
            }
        }
        return null;
    }

    @Override
    public void sacar(int sacar) {
        if (this.saldo >= sacar) {
            this.saldo -= sacar;
            this.realizacaoSaque = true;
        } else if (this.estadoConta != null &&
                (this.estadoConta.equals(Estado.BLOQUEADO) || Objects.equals(this.estadoConta, Estado.INATIVO))) {
            realizacaoSaque = false;
        }

    }


    public String toString() {
        return " Nome : " + super.nome + "\n CPF: " + this.cpf + "\n Número da conta: " + super.numeroConta + "\n Estado da conta: " + super.estadoConta +
                "\n Saldo: R$" + super.saldo + "\n Limite de crédito: " + this.limiteCredito + "\n Crédito disponivel :" + this.creditoDisponivel + "\n Fatura a pagar: " +
                this.fatura + "\n Tipo de conta: " + this.tipo;
    }


    public int getFatura() {
        return fatura;
    }

    public int getCreditoDisponivel() {
        return creditoDisponivel;
    }

    public int getLimiteCredito() {
        return limiteCredito;
    }

    public String getCpf() {
        return this.cpf;
    }

    public TipoConta getTipo() {
        return this.tipo;
    }

    public void setTipoBasic() {
        this.tipo = TipoConta.BASICO;
    }

    public void setTipoPreferencial() {
        this.tipo = TipoConta.PREFERENCIAL;
    }

    public void setTipoPremium() {
        this.tipo = TipoConta.PREMIUM;
    }

    public void setTipoPrivate() {
        this.tipo = TipoConta.PRIVATE;
    }

    public static boolean somenteLetras(String nome){
        return nome.matches("[\\p{L} ]+");
    }

    public String criarPessoaFisica() {
        PrintWriter pwf = null;
        try {
            String dadosPF = "dadosPF.txt";
            File f = new File(dadosPF);
            FileWriter fwp = new FileWriter(f);
            pwf = new PrintWriter(fwp);

            Scanner teclado = new Scanner(System.in);
            String letras;
            int[] nascimento = new int[3];
            String[] datas = {"dia", "mês", "ano"};

            System.out.print("Digite seu nome: ");
            nome = teclado.nextLine();
            if (!somenteLetras(nome)){
                System.out.println("ERRO! - Nome inválido. Use apenas letras.");
                System.exit(0);
            } else {
                this.nome = nome;
                pwf.println(nome);
            }
            for (int i = 0; i < 3; i++) {
                System.out.print("Digite o seu " + datas[i] + " de nascimento: ");
                nascimento[i] = teclado.nextInt();
            }

            LocalDate dataNascimento = LocalDate.of(nascimento[2], nascimento[1], nascimento[0]);
            LocalDate hoje = LocalDate.now();
            int idade = Period.between(dataNascimento, hoje).getYears();

            if (idade >= 18) {
                teclado.nextLine(); // limpar buffer
                System.out.print("Digite seu cpf: ");
                letras = teclado.nextLine();
                this.cpf = letras;
                pwf.println(cpf);
                return "Conta criada com sucesso! \nConectando....";
            } else {
                return "Você não atingiu a maioridade e não pode criar uma conta.";
            }

        } catch (IOException e) {
            return "ERRO! - erro na leitura de dados: " + e.getMessage();
        } finally {
            if (pwf != null) {
                pwf.close();
            }
        }
    }

    public static ContaPessoaFisica CarregarDadosPessoaFisica() {
        String dadosPF = "dadosPF.txt";
        try (Scanner teclado = new Scanner(new File(dadosPF))) {
            String nome = teclado.hasNextLine() ? teclado.nextLine() : "";
            String cpf = teclado.hasNextLine() ? teclado.nextLine() : "";
            return new ContaPessoaFisica(nome, cpf);
        } catch (IOException e) {
            System.out.println("Erro! não foi possivel carregar os dados: " + e.getMessage());
            return null;
        }
    }

    public static ContaPessoaFisica verificarCpf (String acharCpf){
        try {
            Scanner teclado = new Scanner(new File("dadosPF.txt"));
            while (teclado.hasNextLine()) {
                String nome = teclado.nextLine();
                String cpf = teclado.hasNextLine() ? teclado.nextLine() : "";

                if (cpf.equals(acharCpf)) {
                    return new ContaPessoaFisica(nome, cpf);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro! - Não foi possivel acessar o arquivo" + e.getMessage());
        }
        return null;
    }

    public static boolean pequenaGambiarraPF (String cpf){
        return verificarCpf(cpf) != null;
    }

}

