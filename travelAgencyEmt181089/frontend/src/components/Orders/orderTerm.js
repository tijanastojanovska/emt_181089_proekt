import React from 'react';
import {Link} from 'react-router-dom';
//prima props od roditel, tuka prikazuvam sekoja naracka sto ima-currency, status, id, quantity
const OrderTerm = (props) => {
    return (
        <tr>
            <td scope={"col"}>{props.term.currency}</td>
            <td scope={"col"}>{props.term.status}</td>
            <td scope={"col"}>{props.term.ticketId}</td>
            <td scope={"col"}>{props.term.quantity}</td>
        </tr>
    )
}

export default OrderTerm;