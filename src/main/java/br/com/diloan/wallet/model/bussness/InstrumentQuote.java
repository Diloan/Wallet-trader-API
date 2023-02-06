package br.com.diloan.wallet.model.bussness;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class InstrumentQuote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String simbol;
    private double price;
    private LocalDate date;

    public InstrumentQuote() {
    }

    public InstrumentQuote(Long id, String simbol, double price, LocalDate date) {
        this.id = id;
        this.simbol = simbol;
        this.price = price;
        this.date = date;
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
        InstrumentQuote other = (InstrumentQuote) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
