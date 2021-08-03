import axios from "axios";

const instance = axios.create({
    //na koe url komunicira so order
    baseURL: 'http://localhost:9091/api',
    headers: {
        'Access-Control-Allow-Origin' : '*'
    }
})

export default instance;