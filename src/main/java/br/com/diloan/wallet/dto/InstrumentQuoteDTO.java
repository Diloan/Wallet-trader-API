package br.com.diloan.wallet.dto;

import br.com.diloan.wallet.model.InstrumentQuote;

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

}
