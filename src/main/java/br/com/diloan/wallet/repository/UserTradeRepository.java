package br.com.diloan.wallet.repository;

import br.com.diloan.wallet.model.UserTrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface UserTradeRepository extends JpaRepository<UserTrade, Long> {

    List<UserTrade> findByData(LocalDate data);

    @Query(value = "SELECT * FROM user_trade WHERE data between :dataInicio and :dataFim order by data"
            , nativeQuery = true)
    List<UserTrade> findDistinctByDataBetween(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);

    //List<UserTrade> findDistinctByDataBetween(LocalDate dataInicio, LocalDate dataFim);

}
