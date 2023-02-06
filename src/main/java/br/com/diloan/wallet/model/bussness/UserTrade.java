package br.com.diloan.wallet.model.bussness;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class UserTrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    private String tipo_operacao;
    private String mercado;
    private String prazo;
    private String instrument;
    private String especificacao;
    private double quantidade;
    private double preco;
    private double valor_total;
    @Transient
    private double saldo;
    @Transient
    private double rendimento;


    public UserTrade() {
    }

    public UserTrade(Long id, LocalDate data, String tipoOperacao, String mercado, String prazo, String instrument, String especificacao, double quantidade, double preco, double valorTotal, double saldo, double rendimento) {
        this.id = id;
        this.data = data;
        this.tipo_operacao = tipoOperacao;
        this.mercado = mercado;
        this.prazo = prazo;
        this.instrument = instrument;
        this.especificacao = especificacao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.valor_total = valorTotal;
        this.saldo = saldo;
        this.rendimento = rendimento;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public String getTipo_operacao() {
        return tipo_operacao;
    }

    public String getMercado() {
        return mercado;
    }

    public String getPrazo() {
        return prazo;
    }

    public String getInstrument() {
        return instrument;
    }

    public String getEspecificacao() {
        return especificacao;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public double getValor_total() {
        return valor_total;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getRendimento() {
        return rendimento;
    }

    public void setRendimento(double rendimento) {
        this.rendimento = rendimento;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserTrade other = (UserTrade) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
