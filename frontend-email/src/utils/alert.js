import Swal from "sweetalert2";


export const mostrarLoading = () => {

    Swal.fire({
        title: "Enviando correo...",
        text: "Por favor espera",
        allowOutsideClick: false,

        didOpen: () => {
            Swal.showLoading();
        }
    });

};

export const mostrarExito = (mensaje) => {

    Swal.fire({
        icon: "success",
        title: "Correo enviado",
        text: mensaje,
        confirmButtonColor: "#42b883"
    });

};

export const mostrarError = () => {

    Swal.fire({
        icon: "error",
        title: "Error",
        text: "No se pudo enviar el correo"
    });

};