package com.example.ordermanagement.service.forms;
import com.example.ordermanagement.domain.model.OrderStatus;
import com.example.shared_kernel.domain.financial.Currency;
import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.List;

@Data
//formite moze da imaat getters i setters
public class OrderForm implements Source {

    @NotNull
    private Currency currency;
    private OrderStatus status;

    @Valid
    @NotEmpty
    private List<ReservationForm> items = new ArrayList<>();

    @Override
    public void setSystemId(String systemId) {

    }

    @Override
    public String getSystemId() {
        return null;
    }
}
