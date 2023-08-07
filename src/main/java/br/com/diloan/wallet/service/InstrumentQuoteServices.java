package br.com.diloan.wallet.service;

import br.com.diloan.wallet.dto.InstrumentQuoteDTO;
import br.com.diloan.wallet.model.InstrumentQuote;
import br.com.diloan.wallet.repository.InstrumentQuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InstrumentQuoteServices {

    @Autowired
    private InstrumentQuoteRepository instrumentQuoteRepository;

    public List<InstrumentQuoteDTO> listInstruments(LocalDate data1, LocalDate data2) {
        List<InstrumentQuote> instrumentQuotes;
        instrumentQuotes = instrumentQuoteRepository.findByDateBetween(data1, data2);
        return InstrumentQuoteDTO.converter(instrumentQuotes);
    }

    public InstrumentQuoteDTO getInstrumentDetails(@PathVariable Long id) {
        Optional<InstrumentQuote> instrumentQuotes = instrumentQuoteRepository.findById(id);
        if (instrumentQuotes.isPresent()) {
            return new InstrumentQuoteDTO(instrumentQuotes.get());
        }

        throw new IllegalArgumentException("Não foi encontrada nenhuma instruments com id: " + id);
    }
}
