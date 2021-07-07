package Aplicacao;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        final WeldContainer container = new Weld().initialize();
//        final Aplicacao aplicacao = container.select(Aplicacao.class).get();
//        cadastar(aplicacao);
    }

    private static void cadastar(Aplicacao aplicacao) {
        var input = new Scanner(System.in);
        System.out.println("Bem vindo");
        aplicacao.createUsuario(input);
    }
}
