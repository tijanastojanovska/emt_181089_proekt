import React from 'react';
import TicketTerm from '../TicketTerm/ticketTerm';
import {Link} from 'react-router-dom';
//prima props
const tickets = (props) => {
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"table-responsive"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>Name</th>
                            <th scope={"col"}>Price</th>
                            <th scope={"col"}>Sold Tickets</th>
                            <th scope={"col"}>Pocetna</th>
                            <th scope={"col"}>Krajna</th>
                        </tr>
                        </thead>
                        <tbody>
                        {/*preku props koi gi predadov od roditelot, gi pristapuvam biletite*/}
                        {props.tickets.map((term) => {
                            return (
                                //go prikazuva sekoj item (bilet) i delete i reserve
                                <TicketTerm term={term} onDelete={props.onDelete} onReserve={props.onReserve}/>
                            );
                        })}
                        </tbody>
                    </table>
                </div>
                <div className="col mb-3">
                    <div className="row">
                        <div className="col-sm-12 col-md-12">
                            {/*za dodavanje bilet, stavam link do akcijata vo TicketResource, na pateka /tickets/add
                            (delot localhost:9090/api go zema preku axios*/}
                            <Link className={"btn btn-block btn-dark"} to={"/tickets/add"}>Add new ticket</Link>
                        </div>
                    </div>
                </div>
                <div className="col mb-3">
                    <div className="row">
                        <div className="col-sm-12 col-md-12">
                            <Link className={"btn btn-block btn-dark"} to={"/orders"}>See orders</Link>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default tickets;