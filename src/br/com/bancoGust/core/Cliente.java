package br.com.bancoGust.core;
import java.util.Objects;
import java.util.Random;

public abstract class  Cliente {
    protected String nome;
    protected int numeroConta;
    protected double saldo;
    protected Estado estadoConta;
    protected static int unidadeBanco = 2;
    protected boolean classificado, realizacaoSaque, autorizado;


    public Cliente(String nome){
        super();
        this.nome = nome;
        this.estadoConta = Estado.ATIVO;
        this.saldo = 0;
        this.classificado = false;
        gerarNovoNumero();
    }

    Random geradorNumeros = new Random();


    public abstract void sacar(int sacar);

    protected void gerarNovoNumero(){
        this.numeroConta = geradorNumeros.nextInt(1000);
    }


    public void bloquearConta(){
        this.estadoConta = Estado.BLOQUEADO;
    }


    public void reativarConta(){
        this.estadoConta = Estado.ATIVO;
    }


    public void contaInativa(){
        this.estadoConta = Estado.INATIVO;
        this.saldo = 0;

    }


    public void mudarNumeroConta(){
        gerarNovoNumero();
    }

    public void mudarNomeCliente(String novoNome) {
        this.nome = novoNome;
    }




    public int getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public Estado getEstadoConta() {
        return estadoConta;
    }

    public static int getUnidadeBanco() {
        return unidadeBanco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getRealizacaoSaque(){
        return this.realizacaoSaque;
    }

    public abstract void Depositar(int valor);
}
