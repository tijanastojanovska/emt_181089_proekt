import React from 'react';
import {Link} from 'react-router-dom';
// sekoj ticket, kako poseben element
//prima props od roditelot
const ticketTerm = (props) => {
    return (
        <tr>
            {/*preku props.term gi pristapuvam vrednostite*/}
            <td scope={"col"}>{props.term.ticketName}</td>
            {/*bidejki price e od tip Money, koj vo sebe ima amount i currency,
            preku .amount pristapuvam tocno amount koj se prikazuva*/}
            <td scope={"col"}>{props.term.price.amount}</td>
            <td scope={"col"}>{props.term.soldTickets}</td>
            {/*bidejki pocetna i krajna se od tip Destinacija, koja vo sebe ima poveke atributi,
            preku .name pristapuvam tocno do imeto koe se prikazuva*/}
            <td scope={"col"}>{props.term.pocetna.name}</td>
            <td scope={"col"}>{props.term.krajna.name}</td>
            <td scope={"col"} className={"text-right"}>
                {/*kopce za brisenje kade onClick se predava id*/}
                <a title={"Delete"} className={"btn btn-danger"}
                   onClick={() => props.onDelete(props.term.id)}>
                    Delete
                </a>
            </td>
            {/*kopce za rezerviranje, onClick se predava celiot ticket*/}
            <Link className={"btn btn-info ml-2"}
                  onClick={() => props.onReserve(props.term)}
                  to={`/tickets/reserve`}>
                Reserve
            </Link>
        </tr>
    )
}

export default ticketTerm;