package com.testePratico.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

public class funcionario extends pessoa{
    private BigDecimal salario;
    private String funcao;

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFunção() {
        return funcao;
    }

    public void setFunção(String função) {
        this.funcao = função;
    }

    public funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento); // chama o construtor da classe Pessoa
        this.salario = salario;
        this.funcao = funcao;
    }
}
