import React from 'react';
import OrderTerm from './orderTerm';
import {Link} from 'react-router-dom';

const Orders = (props) => {
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"table-responsive"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>Currency</th>
                            <th scope={"col"}>Status</th>
                            <th scope={"col"}>Ticket name</th>
                            <th scope={"col"}>Quantity</th>
                        </tr>
                        </thead>
                        <tbody>
                        {/*preku props gi pristapuvam site orders koi vo roditelot, App gi
                        predadov i preku Orderterm komponentata gi prikazuvam (vo nea e html so site podatoci)*/}
                        {props.orders.map((term) => {
                            return (
                                <OrderTerm term={term} />
                            );
                        })}
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    )
}

export default Orders;