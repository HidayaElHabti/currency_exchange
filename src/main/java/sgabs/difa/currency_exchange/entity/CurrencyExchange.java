package sgabs.difa.currency_exchange.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "currency_exchange")
public class CurrencyExchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CurrencyExchangeId;
    private String FromCurrency;
    private double BuyRate;
    private double SellRate;


    public CurrencyExchange() {
    }

    public CurrencyExchange(
            int currencyExchangeId,
            String fromCurrency,
            double buyRate,
            double sellRate) {
        CurrencyExchangeId = currencyExchangeId;
        FromCurrency = fromCurrency;
        BuyRate = buyRate;
        SellRate = sellRate;
    }

    public CurrencyExchange(
            String fromCurrency,
            double buyRate,
            double sellRate) {
        FromCurrency = fromCurrency;
        BuyRate = buyRate;
        SellRate = sellRate;
    }

    public int getCurrencyExchangeId() {
        return CurrencyExchangeId;
    }

    public void setCurrencyExchangeId(int currencyExchangeId) {
        CurrencyExchangeId = currencyExchangeId;
    }

    public String getFromCurrency() {
        return FromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        FromCurrency = fromCurrency;
    }

    public double getBuyRate() {
        return BuyRate;
    }

    public void setBuyRate(double buyRate) {
        BuyRate = buyRate;
    }

    public double getSellRate() {
        return SellRate;
    }

    public void setSellRate(double sellRate) {
        SellRate = sellRate;
    }

    @Override
    public String toString() {
        return "CurrencyExchange{" +
                "CurrencyExchangeID=" + CurrencyExchangeId +
                ", ToCurrency='" + FromCurrency + '\'' +
                ", BuyRate=" + BuyRate +
                ", SellRate=" + SellRate +
                '}';
    }
}
