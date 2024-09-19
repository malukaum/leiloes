import axios from "axios";

const api = axios.create({
    baseURL: "/unidades"
});

export default api;
