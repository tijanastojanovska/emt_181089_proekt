import React from 'react';
import {useHistory} from 'react-router-dom';

const TicketAdd = (props) => {

    const history = useHistory();
    //state i funkcijata
    const [formData, updateFormData] = React.useState({
        ticketName: "",
        price: 0,
        soldTickets: 0,
        //bidejki pocetna i krajna se Destinacija objekti, gi postavuvam na null
        pocetna: null,
        krajna: null
    })
//e e nastan
    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }
//koga ke se klikne submit, pod koi se vneseni vo formata da se pratat vo onAddTicket
    const onFormSubmit = (e) => {
        e.preventDefault();
        //debugger
        //od formata gi zema vnesenite
        const ticketName = formData.ticketName;
        const price = formData.price;
        const soldTickets = formData.soldTickets;
        const pocetnaId = formData.pocetna;
        const krajnaId = formData.krajna;
        //kon onAddTicket gi predavam
        props.onAddTicket(ticketName, price, soldTickets, pocetnaId, krajnaId);
        history.push("/tickets");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="ticketName">Ticket name</label>
                        <input type="text"
                               className="form-control"
                               id="ticketName"
                               name="ticketName"
                               required
                               placeholder="Enter ticket name"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="price">Price</label>
                        <input type="number"
                               className="form-control"
                               id="price"
                               name="price"
                               placeholder="Price"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="soldTickets">soldTickets</label>
                        <input type="text"
                               className="form-control"
                               id="soldTickets"
                               name="soldTickets"
                               placeholder="soldTickets"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Pocetna</label>
                        <select name="pocetna" id="pocetna" className="form-control" onChange={handleChange}>
 {/*preku props pristapuvam do site destinacii, koi vo App (roditelot) gi postaviv vo pocetna so this.state.destinacii*/}
                            {props.pocetna.map((term) =>
  //bidejki imam DestinacijaId za da dojdam do id, moram od DestinacijaId da go pristapam id so
                                <option value={term.id.id}>{term.name}</option>
                            )}
                        </select>
                    </div>
                    <div className="form-group">
                        <label>Krajna</label>
                        <select name="krajna" id="krajna" className="form-control" onChange={handleChange}>
 {/*preku props pristapuvam do site destinacii, koi vo App (roditelot) gi postaviv vo pocetna so this.state.destinacii*/}
                            {props.krajna.map((term) =>
                 //bidejki imam DestinacijaId mora so .id da se pristapi
                                <option value={term.id.id}>{term.name}</option>
                            )}
                        </select>
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default TicketAdd;