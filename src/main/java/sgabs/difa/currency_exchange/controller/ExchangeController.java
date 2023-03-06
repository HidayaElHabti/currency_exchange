package sgabs.difa.currency_exchange.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sgabs.difa.currency_exchange.Service.ExchangeService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/exchange")
public class ExchangeController {
    Logger logger = LoggerFactory.getLogger(ExchangeController.class);
    private final ExchangeService exchangeService;

    @Autowired
    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping(value = "/currencies")
    public ResponseEntity<List<String>> getCurrencies() {
        logger.trace("Method getCurrencies invoked with HTTP OK status");
        return new ResponseEntity< >(exchangeService.getCurrencies(), HttpStatus.OK);
    }

    @GetMapping("/{from_currency}")
    public ResponseEntity<Double> getSellRate(@PathVariable(name = "from_currency")String from_currency){
        Optional<Double> rate = exchangeService.getSellRate(from_currency);
        if(rate.isPresent()) {
            logger.trace("Method getSellRate("+from_currency+") invoked with HTTP OK status");
            return new ResponseEntity<>(rate.get(), HttpStatus.OK);
        }
        logger.error("Method getSellRate("+from_currency+") invoked with HTTP NOT_FOUND status");
        return new ResponseEntity< >(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{from_currency}/{value}")
    public ResponseEntity<Double> convert(
            @PathVariable(name = "from_currency")String from_currency,
            @PathVariable(name = "value")double value){
        Optional<Double> res = exchangeService.convert(from_currency, value);
        if(res.isPresent()) {
            logger.trace("Method convert("+from_currency+","+value+") invoked with HTTP OK status");
            return new ResponseEntity<>(res.get(), HttpStatus.OK);
        }
        logger.error("Method convert("+from_currency+","+value+") invoked with HTTP NOT_FOUND status");
        return new ResponseEntity< >(null, HttpStatus.NOT_FOUND);
    }
}
