import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import Destinacii from '../Destinacii/destinacii';
import Orders from '../Orders/orders';
import TicketReserve from '../Tickets/TicketReserve/ticketReserve';
import Tickets from '../Tickets/TicketList/tickets';
import TicketService from "../../repository/ticketRepository";
import Header from '../Header/header';
import TicketAdd from "../Tickets/TicketAdd/ticketAdd";
import OrderService from "../../repository/orderRepository";
class App extends Component {

    constructor(props) {
        super(props);
 //prazni listi vo koi se prakaat podatocite kon decata
        this.state = {
            destinacii: [],
            tickets: [],
            selectedTicket: {},
            orders:[]
        }
    }
//bidejki e klasna komponenta imam render
    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div className="container">
   {/*tuka se rutite koi gi pristapuvame, koristam browser router, napisan e delot od patekata koj se menuva, primer /tickets,
   dodeka pocetniot del od patekata za localhost:9090 ili 9091/api go zema preku axios koj e vklucen vo soodvetiniot servis,
   propagirajki nanazad ja popolnuva soodvetnata ruta za da ja pogodi tocnata akcija*/}
                        <Route path={"/destinacii"} exact render={() =>
        //so this.state.destinacii se prakaat od roditelot kon deteto-komponentata Desinacija
                            <Destinacii destinacii={this.state.destinacii}/>}/>
                        <Route path={"/orders"} exact render={() =>
       //so this.state.orders se prakaat od roditelot kon deteto-komponentata Orders
                            <Orders orders={this.state.orders}/>}/>
                        <Route path={"/tickets/add"} exact render={() =>
 //komponentata za TicketAdd, vo nea gi prakam site destinacii vo pocetna i krajna kon deteto so this.state.destinacii
// za da moze da se odbira od niv vo formata koja se prikazuva
                            <TicketAdd pocetna={this.state.destinacii}
                        //so ova gi predavam kon deteto, koe ke gi primi kako props
                                       krajna={this.state.destinacii}
                      //callback za da se povika akcijata add
                                       onAddTicket={this.addTicket}/>}/>
                        <Route path={"/tickets/reserve"} exact render={() =>
          //za reserve ima posebna komponenta kade go prakam odbraniot tiket
                            <TicketReserve selectedTicket={this.state.selectedTicket}
                              //callback za akcijata za rezerviranje
                                       onReserveTicket={this.reserveTicket}/>}/>
                        <Route path={"/tickets"} exact render={() =>
                            <Tickets tickets={this.state.tickets}
                                     //callbacks
                                     onDelete={this.deleteTicket}
                                     onReserve={this.getTicket}
                            />}/>

                        <Redirect to={"/tickets"}/>
                    </div>
                </main>
            </Router>
        );
    }

    componentDidMount() {
        this.loadDestinacii();
        this.loadTickets();
        this.loadOrders();
    }
//getTicket mi treba za da go zemam biletot koj go odbral korisnikot za rezerviranje, preku id
    getTicket = (id) => {
        TicketService.getTicket(id)
            .then((data) => {
                this.setState({
                    selectedTicket: data.data
                })
            })
    }
    //za da se prikazat destinaciite
    loadDestinacii = () => {
        TicketService.fetchDestinacii()
            .then((data) => {
                this.setState({
                    destinacii: data.data
                })
            });
    }
//za da se prikazat biletite
    loadTickets = () => {
        TicketService.fetchTickets()
            .then((data) => {
                this.setState({
                    tickets: data.data
                })
            });
    }
    //za prikaz na narackite
    loadOrders = () => {
        OrderService.fetchOrders()
            .then((data) => {
                //debugger;
                this.setState({
                    orders: data.data,
                    redirect: "/orders"
                });
                return <Redirect to='/orders' />
            });
    }
    //za brisenje na odreden bilet preku id
    deleteTicket = (id) => {
        TicketService.deleteTicket(id)
            .then(() => {
                this.loadTickets();
            });
    }
    reserveTicket = (ticket, quantity) => {
        //cel tiket i vneseno quantity od korisnik
        OrderService.reserveTicket(ticket, quantity)
            .then(() => {
                //po naracka, da se prikazat site orders
                this.loadOrders();
            });
    }
    addTicket = (ticketName, price, soldTickets, pocetna, krajna) => {
        //atributite koi se potrebni za kreiranje bilet
        TicketService.addTicket(ticketName, price, soldTickets, pocetna, krajna)
            .then(() => {
                //da se prikazat site bileti
                this.loadTickets();
            });
    }

    getTicket = (id) => {
        TicketService.getTicket(id)
            .then((data) => {
                this.setState({
                    //preku id, odbraniot tiket, koj mi treba naracka
                    selectedTicket: data.data
                })
            })
    }
}
//mora export na site komponenti za da moze da se koristat
export default App;