import axios from "axios";

const api = axios.create({
    baseURL: "/empresas"
});

export default api;
