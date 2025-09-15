package com.testePratico;

import com.testePratico.entidades.funcionario;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Formatadores: data e número (estilo BR)
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        DecimalFormat formatterNumero = new DecimalFormat("#,##0.00", symbols);

        // 3.1 – Inserir todos os funcionários
        List<funcionario> funcionarios = new ArrayList<>(Arrays.asList(
                new funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
                new funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
                new funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
                new funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
                new funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
                new funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
                new funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
                new funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        ));

        // 3.2 – Remover João
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));

        // 3.3 – Imprimir todos os funcionários formatados
        System.out.println("\n\n--------- Lista de Funcionários ---------");
        funcionarios.forEach(f -> System.out.println(
                f.getNome() + " | " +
                        f.getDataNascimento().format(formatterData) + " | " +
                        formatterNumero.format(f.getSalario()) + " | " +
                        f.getFuncao()
        ));

        // 3.4 – Aplicar aumento de 10%
        funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(BigDecimal.valueOf(1.1))));

        // 3.5 – Agrupar por função
        Map<String, List<funcionario>> funcionariosPorFuncao =
                funcionarios.stream().collect(Collectors.groupingBy(funcionario::getFuncao));

        // 3.6 – Imprimir agrupados por função
        System.out.println("\n--------- Funcionários por Função ---------");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println(funcao + ":");
            lista.forEach(f -> System.out.println(" - " + f.getNome()));
        });

        // 3.8 – Funcionários que fazem aniversário em Outubro e Dezembro
        System.out.println("\n--------- Aniversariantes Outubro e Dezembro ---------");
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
                .forEach(f -> System.out.println(f.getNome() + " - " + f.getDataNascimento().format(formatterData)));

        // 3.9 – Funcionário mais velho
        funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(funcionario::getDataNascimento))
                .orElse(null);
        if (maisVelho != null) {
            int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
            System.out.println("\n--------- Funcionário com a Maior Idade ---------");
            System.out.println("\nMais velho: " + maisVelho.getNome() + " - " + idade + " anos");
        }

        // 3.10 – Lista em ordem alfabética
        System.out.println("\n--------- Funcionários em Ordem Alfabética ---------");
        funcionarios.stream()
                .sorted(Comparator.comparing(funcionario::getNome))
                .forEach(f -> System.out.println(f.getNome()));

        // 3.11 – Total dos salários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários: R$ " + formatterNumero.format(totalSalarios));

        // 3.12 – Quantos salários mínimos cada funcionário ganha
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\n--------- Salários em múltiplos do mínimo ---------");
        funcionarios.forEach(f -> {
            BigDecimal qtd = f.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(f.getNome() + " ganha " + qtd + " salários mínimos");
        });

    }
}