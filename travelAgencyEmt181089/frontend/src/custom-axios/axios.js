import axios from "axios";

const instance = axios.create({
    //na koe url komunicira so ticket
    baseURL: 'http://localhost:9090/api',
    headers: {
        'Access-Control-Allow-Origin' : '*'
    }
})

export default instance;