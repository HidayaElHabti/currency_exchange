package sgabs.difa.currency_exchange.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sgabs.difa.currency_exchange.entity.CurrencyExchange;
import sgabs.difa.currency_exchange.repository.ExchangeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeService {
    private final ExchangeRepository exchangeRepository;

    @Autowired
    public ExchangeService(
            ExchangeRepository exchangeRepository) {
        this.exchangeRepository = exchangeRepository;
    }

    public Optional<Double> getSellRate(String from_currency){
        Optional<CurrencyExchange> c = exchangeRepository.getExchange(from_currency);
        Optional<Double> rate;
        if(c.isPresent()){
            if(from_currency.startsWith("100"))
                rate = Optional.of(c.get().getSellRate() / 100);
            else
                rate = Optional.of(c.get().getSellRate());
        }
        else
            rate = Optional.empty();
        return rate;
    }

    public Optional<Double> convert(String from_currency, double value){
        Optional<Double> r = getSellRate(from_currency);
        return r.map(aDouble -> aDouble * value);
    }

    public List<String> getCurrencies(){
        List<CurrencyExchange> list = exchangeRepository.findAll();
        List<String> currencies = new ArrayList<>();
        for(CurrencyExchange c : list){
            currencies.add(c.getFromCurrency());
        }
        return currencies;
    }
}
