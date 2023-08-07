package br.com.diloan.wallet.service;


import br.com.diloan.wallet.dto.InstrumentQuoteDTO;
import br.com.diloan.wallet.dto.UserTradeDTO;
import br.com.diloan.wallet.model.InstrumentQuote;
import br.com.diloan.wallet.model.UserTrade;
import br.com.diloan.wallet.repository.InstrumentQuoteRepository;
import br.com.diloan.wallet.repository.UserTradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserTradeServices {
    @Autowired
    private UserTradeRepository userTradeRepository;

    @Autowired
    private InstrumentQuoteRepository instrumentQuoteRepository;

    public List<UserTradeDTO> listQuotes(LocalDate startDate, LocalDate endDate) {

        List<UserTrade> userTrades = userTradeRepository.findDistinctByDataBetween(startDate, endDate);

        double quoteQuantity = 0.0;
        for (UserTrade item : userTrades) {
            InstrumentQuoteDTO instrumentQuoteDTO = getQuotesDetails(startDate, item.getInstrument());
            quoteQuantity = item.getTipo_operacao().equalsIgnoreCase("C") ? quoteQuantity + item.getQuantidade() : quoteQuantity - item.getQuantidade();
            double saldo = instrumentQuoteDTO.getPrice() * quoteQuantity;
            double rendimento = calculatesStockYield(item.getValor_total(), instrumentQuoteDTO.getPrice(), quoteQuantity);
            item.setQuantidade(quoteQuantity);
            item.setSaldo(Double.parseDouble(new DecimalFormat("#.##").format(saldo).replace(",",".")));
            item.setRendimento(rendimento);

        }

        return UserTradeDTO.converter(userTrades);

    }

    public UserTradeDTO getQuotes(@PathVariable Long id) {
        Optional<UserTrade> trade = userTradeRepository.findById(id);
        if (trade.isPresent()) {
            return new UserTradeDTO(trade.get());
        }

        throw new IllegalArgumentException("Not found a quote with id: " + id);
    }

    public InstrumentQuoteDTO getQuotesDetails(LocalDate startDate, String simbol) {
        Optional<InstrumentQuote> instrumentQuote = instrumentQuoteRepository.findByPeriodAndInstrument(startDate, simbol);
        if (instrumentQuote.isPresent()) {
            return new InstrumentQuoteDTO(instrumentQuote.get());
        }

        throw new IllegalArgumentException("Não foi encontrada nenhuma acao com id: " + startDate);
    }

    public double calculatesStockYield(double amount, double priceDay, double quantity) {
        double stockYield;
        double balance = calculateBalance(priceDay, quantity);

        stockYield = (balance - amount) / amount * 100;
        System.out.println("Valor rendimento: " + stockYield);
        System.out.println("Saldo: " + balance);

        return Double.parseDouble(new DecimalFormat("#.##").format(stockYield).replace(",","."));
    }

    public double calculateBalance(double priceDay, double quantity) {
        double result;
        String balance;

        result = quantity * priceDay;
        balance = new DecimalFormat("#.##").format(result);
        System.out.println("Valor formatado: " + balance);

        return result;
    }

}
