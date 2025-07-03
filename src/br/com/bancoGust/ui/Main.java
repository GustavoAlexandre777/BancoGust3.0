package br.com.bancoGust.ui;

import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        try{
            MenuPrincipal menu = new MenuPrincipal();
            menu.exibirMenuPrincipal();
        } catch (InputMismatchException e) {
            System.out.println("Erro! - Você deve digitar um número inteiro (sem casas decimais) e não é aceito entrada de caracteres alfabéticos. ");
        }
    }
}
