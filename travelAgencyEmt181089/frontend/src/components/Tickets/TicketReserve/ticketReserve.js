import React from 'react';
import {useHistory} from 'react-router-dom';
//dete komponentata prima props od App
const TicketReserve = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
      //samo quantity ke bide vneseno od korisnikot, pa samo taa treba da ja postavam
        quantity : 0
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        //debugger
        //tuka go imam odbraniot bilet
        const selectedTicket = props.selectedTicket;
        //ke treba korisnikot da vnese kolku bileti saka
        const quantity = formData.quantity;
        props.onReserveTicket(selectedTicket, quantity);
        history.push("/orders");
    }

    return(
        <div className="row mt-5">
            <div>
                {/*da go pristapam biletot i da se prikaze vo htmlot*/}
                {props.selectedTicket.ticketName}
            </div>
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="quantity">Quantity</label>
                        <input type="number"
                               className="form-control"
                               id="quantity"
                               name="quantity"
                               required
                               placeholder="Enter quantity"
                               onChange={handleChange}
                        />
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Order</button>
                </form>
            </div>
        </div>
    )
}

export default TicketReserve;