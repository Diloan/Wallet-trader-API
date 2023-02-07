package br.com.diloan.wallet.controller.service;


import br.com.diloan.wallet.controller.dto.InstrumentQuoteDTO;
import br.com.diloan.wallet.controller.dto.UserTradeDTO;
import br.com.diloan.wallet.model.bussness.InstrumentQuote;
import br.com.diloan.wallet.model.bussness.UserTrade;
import br.com.diloan.wallet.model.repository.InstrumentQuoteRepository;
import br.com.diloan.wallet.model.repository.UserTradeRepository;
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

    public List<UserTradeDTO> listarAcoes(LocalDate data1, LocalDate data2) {

        InstrumentQuoteDTO userTradesNew;
        double quantidadeAcoes = 0;
        double saldo;
        double rendimento;
        List<UserTrade> userTrades = userTradeRepository.findDistinctByDataBetween(data1, data2);

        for (UserTrade item : userTrades) {
            userTradesNew = detalharInstrument(data1, item.getInstrument());
            quantidadeAcoes = item.getTipo_operacao().equalsIgnoreCase("C") ? quantidadeAcoes + item.getQuantidade() : quantidadeAcoes - item.getQuantidade();
            saldo = userTradesNew.getPrice() * quantidadeAcoes;
            rendimento = calculaRedimentoAcoes(item.getValor_total(), userTradesNew.getPrice(), quantidadeAcoes);
            item.setQuantidade(quantidadeAcoes);
            item.setSaldo(Double.parseDouble(new DecimalFormat("#.##").format(saldo).replace(",",".")));
            item.setRendimento(rendimento);

        }

        return UserTradeDTO.converter(userTrades);

    }

    public UserTradeDTO detalharAcao(@PathVariable Long id) {
        Optional<UserTrade> trade = userTradeRepository.findById(id);
        if (trade.isPresent()) {
            return new UserTradeDTO(trade.get());
        }

        throw new IllegalArgumentException("Não foi encontrada nenhuma acao com id: " + id);
    }

    public InstrumentQuoteDTO detalharInstrument(LocalDate dataInicio, String simbol) {
        Optional<InstrumentQuote> instrumentQuote = instrumentQuoteRepository.findByPeriodAndInstrument(dataInicio, simbol);
        if (instrumentQuote.isPresent()) {
            return new InstrumentQuoteDTO(instrumentQuote.get());
        }

        throw new IllegalArgumentException("Não foi encontrada nenhuma acao com id: " + dataInicio);
    }

    public double calculaRedimentoAcoes(double valorTotal, double precoDia, double quantidade) {
        double rendimentoAcoes = 0;
        double saldo = calculaSaldo(precoDia, quantidade);

        rendimentoAcoes = (saldo - valorTotal) / valorTotal * 100;
        System.out.println("Valor rendimento: " + rendimentoAcoes);
        System.out.println("Saldo: " + saldo);

        return Double.parseDouble(new DecimalFormat("#.##").format(rendimentoAcoes).replace(",","."));
    }

    public double calculaSaldo(double precoDia, double quantidade) {
        double resultado;
        String saldo;

        resultado = quantidade * precoDia;
        saldo = new DecimalFormat("#.##").format(resultado);
        System.out.println("Valor formatado: " + saldo);

        return resultado;
    }

}
