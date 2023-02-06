package br.com.diloan.wallet.model.repository;

import br.com.diloan.wallet.model.bussness.InstrumentQuote;
import br.com.diloan.wallet.model.bussness.UserTrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InstrumentQuoteRepository extends JpaRepository<InstrumentQuote, Long> {

    List<InstrumentQuote> findByDate(LocalDate data);

    List<InstrumentQuote> findByDateBetween(LocalDate data1, LocalDate data2);

    @Query(value = "SELECT * FROM instrument_quote WHERE date = :data and simbol = :simbol", nativeQuery = true)
    Optional<InstrumentQuote> findByPeriodAndInstrument(LocalDate data, String simbol);

}
