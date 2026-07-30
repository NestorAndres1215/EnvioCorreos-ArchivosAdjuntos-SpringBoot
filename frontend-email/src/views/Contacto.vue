<script setup>
import { ref } from "vue";
import { enviarContacto } from "../services/contactoService.js";
import { mostrarLoading, mostrarExito, mostrarError } from "../utils/alert.js";

const nombre = ref("");
const correo = ref("");
const asunto = ref("");
const mensaje = ref("");
const archivo = ref(null);
const archivoNombre = ref("");
const estado = ref("");

const seleccionarArchivo = (event) => {
  archivo.value = event.target.files[0];
  archivoNombre.value = archivo.value?.name ?? "";
};

const limpiarFormulario = () => {
  nombre.value = "";
  correo.value = "";
  asunto.value = "";
  mensaje.value = "";
  archivo.value = null;
  archivoNombre.value = "";
};

const enviarFormulario = async () => {
  const contacto = {
    nombre: nombre.value,
    correo: correo.value,
    asunto: asunto.value,
    mensaje: mensaje.value,
  };

  try {
    estado.value = "loading";

    mostrarLoading();

    const respuesta = await enviarContacto(contacto, archivo.value);

    estado.value = "success";

    mostrarExito(respuesta);

    limpiarFormulario();
  } catch (error) {
    estado.value = "error";
    mostrarError();
  } finally {
    estado.value = "";
  }
};
</script>

<template>
  <div class="page">
    <div class="bg-orb orb-1"></div>
    <div class="bg-orb orb-2"></div>
    <div class="grid-overlay"></div>

    <div class="card">
      <div class="card-header">
        <div class="badge">Contacto</div>
        <h2 class="title">
          Envíanos un<br /><span class="title-accent">mensaje</span>
        </h2>
        <p class="subtitle">Responderemos a la brevedad posible.</p>
      </div>

      <transition name="toast">
        <div v-if="estado === 'success'" class="toast toast-success">
          <span class="toast-icon">✓</span> Mensaje enviado correctamente
        </div>
        <div v-else-if="estado === 'error'" class="toast toast-error">
          <span class="toast-icon">✕</span> Error al enviar el correo
        </div>
      </transition>

      <form @submit.prevent="enviarFormulario" class="form">
        <div class="row">
          <div class="field">
            <label>Nombre</label>
            <input v-model="nombre" placeholder="Tu nombre completo" required />
          </div>
          <div class="field">
            <label>Correo</label>
            <input
              v-model="correo"
              type="email"
              placeholder="tu@correo.com"
              required
            />
          </div>
        </div>

        <div class="field">
          <label>Asunto</label>
          <input
            v-model="asunto"
            placeholder="¿De qué trata tu mensaje?"
            required
          />
        </div>

        <div class="field">
          <label>Mensaje</label>
          <textarea
            v-model="mensaje"
            placeholder="Escribe tu mensaje aquí..."
            rows="5"
            required
          ></textarea>
        </div>

        <div class="field">
          <label>Adjunto <span class="label-optional">(opcional)</span></label>
          <label class="file-drop" :class="{ 'has-file': archivoNombre }">
            <input type="file" @change="seleccionarArchivo" hidden />
            <span class="file-icon">{{ archivoNombre ? "📎" : "↑" }}</span>
            <span class="file-text">{{
              archivoNombre || "Haz clic para adjuntar un archivo"
            }}</span>
          </label>
        </div>

        <button
          type="submit"
          class="submit-btn"
          :disabled="estado === 'loading'"
        >
          <span v-if="estado === 'loading'" class="spinner"></span>
          <span v-else>Enviar mensaje</span>
          <span v-if="estado !== 'loading'" class="btn-arrow">→</span>
          <div class="btn-shine"></div>
        </button>
      </form>
    </div>
  </div>
</template>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Syne:wght@400;600;700;800&family=DM+Sans:wght@300;400;500&display=swap");

*,
*::before,
*::after {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

.page {
  font-family: "DM Sans", sans-serif;
  min-height: 100vh;
  background: #080b14;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 40px 20px;
  position: relative;
  overflow: hidden;
}

.bg-orb {
  position: absolute;
  border-radius: 50%;
  filter: blur(90px);
  pointer-events: none;
}

.orb-1 {
  width: 500px;
  height: 500px;
  background: radial-gradient(
    circle,
    rgba(99, 102, 241, 0.22) 0%,
    transparent 70%
  );
  top: -120px;
  left: -150px;
  animation: float 8s ease-in-out infinite;
}

.orb-2 {
  width: 400px;
  height: 400px;
  background: radial-gradient(
    circle,
    rgba(236, 72, 153, 0.15) 0%,
    transparent 70%
  );
  bottom: -80px;
  right: -100px;
  animation: float 11s ease-in-out infinite reverse;
}

.grid-overlay {
  position: absolute;
  inset: 0;
  background-image:
    linear-gradient(rgba(255, 255, 255, 0.025) 1px, transparent 1px),
    linear-gradient(90deg, rgba(255, 255, 255, 0.025) 1px, transparent 1px);
  background-size: 60px 60px;
  pointer-events: none;
}

.card {
  position: relative;
  z-index: 10;
  width: 100%;
  max-width: 620px;
  background: rgba(15, 20, 35, 0.85);
  border: 1px solid rgba(255, 255, 255, 0.07);
  border-radius: 24px;
  padding: 48px;
  backdrop-filter: blur(20px);
  box-shadow:
    0 40px 80px rgba(0, 0, 0, 0.5),
    0 0 0 1px rgba(99, 102, 241, 0.08);
  animation: fadeUp 0.8s cubic-bezier(0.16, 1, 0.3, 1) both;
}

.card-header {
  margin-bottom: 36px;
}

.badge {
  display: inline-block;
  font-size: 0.68rem;
  font-weight: 500;
  letter-spacing: 0.18em;
  text-transform: uppercase;
  color: #818cf8;
  background: rgba(99, 102, 241, 0.1);
  border: 1px solid rgba(99, 102, 241, 0.22);
  padding: 5px 14px;
  border-radius: 100px;
  margin-bottom: 20px;
}

.title {
  font-family: "Syne", sans-serif;
  font-size: 2.4rem;
  font-weight: 800;
  color: #f1f5f9;
  line-height: 1.1;
  letter-spacing: -0.02em;
  margin-bottom: 10px;
}

.title-accent {
  background: linear-gradient(135deg, #818cf8 0%, #ec4899 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.subtitle {
  font-size: 0.9rem;
  color: #475569;
  font-weight: 300;
}

.toast {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 12px 18px;
  border-radius: 12px;
  font-size: 0.88rem;
  font-weight: 500;
  margin-bottom: 24px;
}

.toast-success {
  background: rgba(34, 197, 94, 0.1);
  border: 1px solid rgba(34, 197, 94, 0.25);
  color: #4ade80;
}

.toast-error {
  background: rgba(239, 68, 68, 0.1);
  border: 1px solid rgba(239, 68, 68, 0.25);
  color: #f87171;
}

.toast-icon {
  font-size: 1rem;
}

.toast-enter-active,
.toast-leave-active {
  transition: all 0.3s ease;
}

.toast-enter-from,
.toast-leave-to {
  opacity: 0;
  transform: translateY(-8px);
}

.form {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 16px;
}

.field {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

label {
  font-size: 0.78rem;
  font-weight: 500;
  letter-spacing: 0.06em;
  text-transform: uppercase;
  color: #64748b;
}

.label-optional {
  font-size: 0.72rem;
  color: #334155;
  text-transform: none;
  letter-spacing: 0;
  font-weight: 400;
}

input,
textarea {
  font-family: "DM Sans", sans-serif;
  font-size: 0.95rem;
  font-weight: 400;
  color: #e2e8f0;
  background: rgba(255, 255, 255, 0.03);
  border: 1px solid rgba(255, 255, 255, 0.08);
  border-radius: 12px;
  padding: 14px 16px;
  outline: none;
  transition:
    border-color 0.2s,
    background 0.2s,
    box-shadow 0.2s;
  resize: none;
  width: 100%;
}

input::placeholder,
textarea::placeholder {
  color: #334155;
}

input:focus,
textarea:focus {
  border-color: rgba(99, 102, 241, 0.5);
  background: rgba(99, 102, 241, 0.05);
  box-shadow: 0 0 0 3px rgba(99, 102, 241, 0.1);
}

.file-drop {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 14px 18px;
  background: rgba(255, 255, 255, 0.03);
  border: 1px dashed rgba(255, 255, 255, 0.1);
  border-radius: 12px;
  cursor: pointer;
  transition:
    border-color 0.2s,
    background 0.2s;
  text-transform: none;
  letter-spacing: 0;
}

.file-drop:hover,
.file-drop.has-file {
  border-color: rgba(99, 102, 241, 0.4);
  background: rgba(99, 102, 241, 0.05);
}

.file-icon {
  font-size: 1.1rem;
  color: #818cf8;
}

.file-text {
  font-size: 0.9rem;
  color: #475569;
  font-weight: 400;
}

.file-drop.has-file .file-text {
  color: #a5b4fc;
}

.submit-btn {
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  width: 100%;
  padding: 16px;
  border: none;
  border-radius: 14px;
  background: linear-gradient(135deg, #6366f1 0%, #8b5cf6 100%);
  color: #fff;
  font-family: "DM Sans", sans-serif;
  font-size: 1rem;
  font-weight: 500;
  cursor: pointer;
  overflow: hidden;
  transition:
    transform 0.2s,
    box-shadow 0.2s,
    opacity 0.2s;
  box-shadow:
    0 0 0 1px rgba(99, 102, 241, 0.4),
    0 16px 36px rgba(99, 102, 241, 0.3);
  margin-top: 4px;
}

.submit-btn:hover:not(:disabled) {
  transform: translateY(-2px);
  box-shadow:
    0 0 0 1px rgba(99, 102, 241, 0.6),
    0 24px 48px rgba(99, 102, 241, 0.4);
}

.submit-btn:active:not(:disabled) {
  transform: translateY(0) scale(0.99);
}

.submit-btn:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.btn-arrow {
  transition: transform 0.2s;
}

.submit-btn:hover .btn-arrow {
  transform: translateX(4px);
}

.btn-shine {
  position: absolute;
  top: 0;
  left: -100%;
  width: 60%;
  height: 100%;
  background: linear-gradient(
    90deg,
    transparent,
    rgba(255, 255, 255, 0.12),
    transparent
  );
  transition: left 0.5s ease;
}

.submit-btn:hover .btn-shine {
  left: 160%;
}

.spinner {
  width: 18px;
  height: 18px;
  border: 2px solid rgba(255, 255, 255, 0.3);
  border-top-color: #fff;
  border-radius: 50%;
  animation: spin 0.7s linear infinite;
}

@keyframes fadeUp {
  from {
    opacity: 0;
    transform: translateY(28px);
  }

  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@keyframes float {
  0%,
  100% {
    transform: translateY(0);
  }

  50% {
    transform: translateY(-28px);
  }
}

@keyframes spin {
  to {
    transform: rotate(360deg);
  }
}

@media (max-width: 520px) {
  .card {
    padding: 32px 24px;
  }

  .row {
    grid-template-columns: 1fr;
  }

  .title {
    font-size: 1.9rem;
  }
}
</style>
