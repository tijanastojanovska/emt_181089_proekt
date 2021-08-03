import axios from '../custom-axios/ordersAxios';
const OrderService ={
//reserveTicket prima cel ticket i quantity koe go vnesuva korisnikot
    reserveTicket: (ticket, quantity) => {
   //prensocuva kon post metodot vo kontrolerot na order do akcijata za rezervacija
        return axios.post("/orders/reserve", {
            "ticket" : ticket,
            "quantity": quantity
        });
    },
    //da gi zeme site naracki
    fetchOrders: () => {
        return axios.get("/orders");
    },
}
//mora export za da moze da se koristi
export default OrderService;