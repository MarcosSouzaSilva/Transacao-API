package br.com.mark.transacaoapi.usecases;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;

public class teste {

    private static Cara cara;

    public teste(Cara cara) {
        this.cara = cara;
    }

    public static void main(String[] args) {
        ArrayList<DoubleSummaryStatistics> stats = new ArrayList<>();
        stats.add(new DoubleSummaryStatistics());
        cara = new Cara("Marcos", 12);

        System.out.println(cara.getIdade());
        System.out.println(cara.getNome());

        stats.forEach(s -> {
            cara.setNome("Lucas");
            cara.setIdade(17);


            System.out.println(cara.getIdade());
            System.out.println(cara.getNome());
        });

        System.out.println(cara.getIdade());
        System.out.println(cara.getNome());

        stats.forEach(s -> {
            cara.setNome(null);
            cara.setIdade(0);


            System.out.println(cara.getIdade());
            System.out.println(cara.getNome());
        });

        System.out.println(cara.getIdade());
        System.out.println(cara.getNome());

        stats.forEach(s -> {
            cara.setNome("Lucas");
            cara.setIdade(17);


            System.out.println(cara.getIdade());
            System.out.println(cara.getNome());
        });

// Resetando
        stats = new ArrayList<>();

        System.out.println(cara.getIdade());
        System.out.println(cara.getNome());

        stats.clear();

        System.out.println("sdwdwd");
        System.out.println(cara.getIdade());
        System.out.println(cara.getNome());

    }
}
