package br.com.diloan.wallet.dto;

import br.com.diloan.wallet.model.UserTrade;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class UserTradeDTO {
    private Long id;
    private LocalDate data;
    private String instrument;
    private String tipo;
    private double quantidade;
    private double preco;
    private double saldo;
    private double rendimento;


    public UserTradeDTO(UserTrade trade) {
        this.id = trade.getId();
        this.data = trade.getData();
        this.instrument = trade.getInstrument();
        this.tipo = trade.getTipo_operacao();
        this.quantidade = trade.getQuantidade();
        this.preco = trade.getPreco();
        this.saldo = trade.getSaldo();
        this.rendimento = trade.getRendimento();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public String getInstrument() {
        return instrument;
    }

    public String getTipo() {
        return tipo;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getRendimento() {
        return rendimento;
    }

    public static List<UserTradeDTO> converter(List<UserTrade> userTrades) {
        return userTrades.stream().map(UserTradeDTO::new).collect(Collectors.toList());
    }


}
