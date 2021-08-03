import axios from '../custom-axios/axios';
const TicketService ={
    fetchDestinacii: () => {
        return axios.get("/destinacii");
    },
    fetchTickets: () => {
        return axios.get("/tickets");
    },
    deleteTicket: (id) => {
        //zatoa sto id mi e TicketId mora na toa id, da zemam .id
        return axios.delete(`/tickets/delete/${id.id}`);
    },
    addTicket: (ticketName, price, soldTickets, pocetna, krajna) => {
        //debugger
        //site atributi koi mi se potrebni za da kreiram
        return axios.post("/tickets/add", {
            "ticketName" : ticketName,
            "soldTickets" : soldTickets,
            "priceNumber" : price,
            "pocetna" : {},
            "krajna": {},
            "pocetnaId" : pocetna,
            "krajnaId" : krajna
        });
    },
    getTicket: (id) => {
        return axios.get(`/tickets/${id.id.id}`);
    }
}
export default TicketService;