package sgabs.difa.currency_exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sgabs.difa.currency_exchange.entity.CurrencyExchange;
import java.util.Optional;

@Repository
public interface ExchangeRepository extends JpaRepository<CurrencyExchange, Integer> {
    @Query(value = "SELECT * FROM currency_exchange WHERE from_currency = ?1", nativeQuery = true)
    Optional<CurrencyExchange> getExchange(String from_currency);


}
