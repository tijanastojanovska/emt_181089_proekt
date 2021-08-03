package com.example.shared_kernel.domain.financial;

import com.example.shared_kernel.domain.base.ValueObject;
import lombok.Getter;
import lombok.NonNull;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
@Getter
public class Money implements ValueObject {
//atributite mora da se final, za da se immutable
    @Enumerated(value = EnumType.STRING)
    private final Currency currency;

    private final double amount;

    protected Money() {
        this.amount = 0.0;
        this.currency = null;
    }

    public Money(@NonNull Currency currency, @NonNull double amount) {
        this.currency = currency;
        this.amount = amount;
    }
//generira nova instanca od Money
    public static Money valueOf(Currency currency, double amount) {
        return new Money(currency,amount);
    }

    public Money add(Money money) {
        if (!currency.equals(money.currency)) {
            throw new IllegalArgumentException("Cannot add two Money objects with different currencies");
        }
        return new Money(currency,amount + money.amount);
    }

    public Money subtract(Money money) {
        if (!currency.equals(money.currency)) {
            throw new IllegalArgumentException("Cannot add two Money objects with different currencies");
        }
        return new Money(currency,amount - money.amount);
    }

    public Money multiply(int m)  {
        return new Money(currency,amount*m);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount && currency == money.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }

}
