package br.com.bancoGust.core;

import br.com.bancoGust.core.Cliente;
import br.com.bancoGust.core.TipoJuridico;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class contaPessoaJuridica extends Cliente {
    private String cnpj;
    private String razaosocial;
    private TipoJuridico tipo;
    private int faturamentoAnual;
    private int taxaSaque;
    private double ImpostoRendaAnual;

    public contaPessoaJuridica(String nome, String cnpj, String razaosocial) {
        super(nome);
        super.saldo = 0;
        this.cnpj = cnpj;
        this.razaosocial = razaosocial;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public String getRazaoSocial() {
        return this.razaosocial;
    }

    private void setRazaoSocial(String novaRazao) {
        this.razaosocial = novaRazao;
    }

    // Alterado para getTipo para seguir a convenção de getters
    public TipoJuridico getTipo() {
        return this.tipo;
    }

    private void setTipo(TipoJuridico novoTipo) {
        this.tipo = novoTipo;
    }

    public int getFaturamentoAnual() {
        return this.faturamentoAnual;
    }

    @Override
    public String toString() {
        return "CNPJ: " + this.cnpj +
                "\nEmpresa: " + super.nome +
                "\nRazão Social: " + this.razaosocial +
                "\nTipo de Conta: " + this.tipo +
                "\nFaturamento Anual: " + this.faturamentoAnual +
                "\nCaixa da Empresa: " + super.saldo +
                "\nImposto anual: " + this.ImpostoRendaAnual;
    }

    private void definirTipoConta(int valor) {
        if (valor <= 81000) {
            this.tipo = TipoJuridico.MEI;
            validarTaxa();
            calcularImpostoSobreRendaAnual();
        } else if (valor <= 4800000) {
            this.tipo = TipoJuridico.LTDA;
            validarTaxa();
            calcularImpostoSobreRendaAnual();
        } else {
            this.tipo = TipoJuridico.SA;
            validarTaxa();
            calcularImpostoSobreRendaAnual();
        }
    }

    public void faturaAnual(int valor) {
        this.faturamentoAnual = valor;
        definirTipoConta(valor);
    }

    @Override
    public void Depositar(int valor) {
        if (valor >= 1) {
            super.saldo += valor;
        }
    }

    @Override
    public void sacar(int sacar) {
        // 'classificado' provavelmente é uma variável da classe Cliente ou de ContaPessoaJuridica
        // que indica se a conta já teve sua taxa validada.
        if (!classificado) {
            validarTaxa();
            classificado = true;
        }

        if (super.saldo >= (sacar + taxaSaque)) {
            super.saldo -= (sacar + taxaSaque);
        }
    }

    public void validarTaxa() {
        if (this.tipo.equals(TipoJuridico.MEI)) {
            this.taxaSaque = 5;
        } else if (this.tipo.equals(TipoJuridico.LTDA)) {
            this.taxaSaque = 100;
        } else {
            this.taxaSaque = 1000;
        }
    }

    private void calcularImpostoSobreRendaAnual() {
        if (this.tipo.equals(TipoJuridico.MEI)) {
            ImpostoRendaAnual = faturamentoAnual * 0.02;
        } else if (this.tipo.equals(TipoJuridico.LTDA)) {
            ImpostoRendaAnual = faturamentoAnual * 0.08;
        } else {
            ImpostoRendaAnual = faturamentoAnual * 0.15;
        }
    }

    public double calcularParcelaImposto(int parcelas) {
        return (double) ImpostoRendaAnual / parcelas;
    }

    public String criarContaJuridica() {
        PrintWriter pwj = null;
        try {
            String dadosPJ = "dadosPJ.txt";
            File f = new File(dadosPJ);
            // O 'true' no construtor de FileWriter adiciona ao arquivo existente, em vez de sobrescrever.
            FileWriter fwj = new FileWriter(f, true);
            pwj = new PrintWriter(fwj);

            Scanner teclado = new Scanner(System.in);

            String nomeFantasia;
            System.out.println("Digite o nome fantasia da empresa: ");
            nomeFantasia = teclado.nextLine();
            if (!somenteLetras(nomeFantasia)){
                System.out.println("ERRO! - Nome fantasia inválido. Use apenas letras.");
                return null;
            } else {
                pwj.println(nomeFantasia);
                super.nome = nomeFantasia;
            }

            String razaoSocialDigitada;
            System.out.println("Digite a razão social da empresa: ");
            razaoSocialDigitada = teclado.nextLine();
            if (!somenteLetras(razaoSocialDigitada)){
                System.out.println("ERRO! - Razão social inválida. Use apenas letras.");
                return null;
            } else {
                pwj.println(razaoSocialDigitada);
                this.razaosocial = razaoSocialDigitada;
            }

            String cnpjDigitado;
            System.out.println("Digite o cnpj da empresa: ");
            cnpjDigitado = teclado.nextLine();
            pwj.println(cnpjDigitado);
            this.cnpj = cnpjDigitado;

            System.out.println("Conta jurídica criada com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro ao ler/escrever dados! " + e.getMessage());
        } finally {
            if (pwj != null){
                pwj.close();
            }
        }
        return null;
    }

    public static contaPessoaJuridica carregarDadosJuridico() {
        String arquivo = "dadosPJ.txt";
        // Usando try-with-resources para garantir que o Scanner seja fechado automaticamente
        try (Scanner leitorArquivo = new Scanner(new File(arquivo))) {
            String nomeFantasia = leitorArquivo.hasNextLine() ? leitorArquivo.nextLine() : "";
            String razaoSocial = leitorArquivo.hasNextLine() ? leitorArquivo.nextLine() : "";
            String cnpj = leitorArquivo.hasNextLine() ? leitorArquivo.nextLine() : "";
            return new contaPessoaJuridica(nomeFantasia, cnpj, razaoSocial);
        } catch (IOException e) {
            System.out.println("Erro! Não foi possível carregar os dados: " + e.getMessage());
            return null;
        }
    }

    public static contaPessoaJuridica verificarCnpj(String acharCnpj) {
        try {
            Scanner leitorArquivo = new Scanner(new File("dadosPJ.txt"));
            while (leitorArquivo.hasNextLine()) {
                String nome = leitorArquivo.nextLine();
                String razaoSocial = leitorArquivo.hasNextLine() ? leitorArquivo.nextLine() : "";
                String cnpj = leitorArquivo.hasNextLine() ? leitorArquivo.nextLine() : "";

                if (cnpj.equals(acharCnpj)) {
                    leitorArquivo.close(); // Fechar o scanner assim que o CNPJ for encontrado
                    return new contaPessoaJuridica(nome, cnpj, razaoSocial);
                }
            }
            leitorArquivo.close(); // Fechar o scanner se o CNPJ não for encontrado
        } catch (IOException e) {
            System.out.println("ERRO! - Não foi possível acessar o arquivo" + e.getMessage());
        }
        return null;
    }

    // Renomeado para maior clareza, indicando que verifica a existência de um CNPJ
    public static boolean cnpjExiste(String cnpj) {
        return verificarCnpj(cnpj) != null;
    }

    // Método genérico para validar se uma string contém apenas letras e espaços
    public static boolean somenteLetras(String texto){
        return texto.matches("[\\p{L} ]+");
    }
}