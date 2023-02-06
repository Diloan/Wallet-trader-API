package br.com.diloan.wallet.controller.dto;

import br.com.diloan.wallet.model.bussness.InstrumentQuote;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class InstrumentQuoteDTO {
    private Long id;
    private String simbol;
    private double price;
    private LocalDate date;

    public InstrumentQuoteDTO(InstrumentQuote instrumentQuote) {
        this.id = instrumentQuote.getId();
        this.simbol = instrumentQuote.getSimbol();
        this.price = instrumentQuote.getPrice();
        this.date = instrumentQuote.getDate();
    }

    public Long getId() {
        return id;
    }

    public String getSimbol() {
        return simbol;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return date;
    }

    public static List<InstrumentQuoteDTO> converter(List<InstrumentQuote> instrumentQuotes) {
        return instrumentQuotes.stream().map(InstrumentQuoteDTO::new).collect(Collectors.toList());
    }

    public static double calculaSaldo(double precoDia, double quantidade) {
        double resultado;
        String saldo;

        resultado = quantidade * precoDia;
        saldo = new DecimalFormat("#.##").format(resultado);
        System.out.println("Valor formatado: " + saldo);

        return resultado;
    }

    public static double calculaRedimentoAcoes(double valorTotal, double precoDia, double quantidade) {
        double rendimentoAcoes = 0;
        double saldo = calculaSaldo(precoDia, quantidade);

        rendimentoAcoes = (saldo - valorTotal) / valorTotal * 100;
        System.out.println("Valor rendimento: " + rendimentoAcoes);
        System.out.println("Saldo: " + saldo);
        
        return Double.parseDouble(new DecimalFormat("#.##").format(rendimentoAcoes).replace(",","."));
    }
}
