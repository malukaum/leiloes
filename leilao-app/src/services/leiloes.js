import axios from "axios";

const api = axios.create({
    baseURL: "/leiloes"
});

export default api;
