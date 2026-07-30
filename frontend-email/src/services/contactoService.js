import axios from "axios";

const API_URL = "http://localhost:8080/contacto/enviar";

export const enviarContacto = async (contacto, archivo) => {
    const formData = new FormData();

    formData.append(
        "contacto",
        new Blob(
            [JSON.stringify(contacto)],
            { type: "application/json" }
        )
    );

    if (archivo) {
        formData.append("archivo", archivo);
    }


    const response = await axios.post(
        API_URL,
        formData,
        {
            headers: {
                "Content-Type": "multipart/form-data"
            }
        }
    );

    return response.data;

};