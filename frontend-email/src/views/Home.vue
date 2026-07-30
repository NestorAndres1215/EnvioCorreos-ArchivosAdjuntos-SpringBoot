<template>
  <div class="home">
    <div class="bg-orb orb-1"></div>
    <div class="bg-orb orb-2"></div>
    <div class="grid-overlay"></div>

    <div class="content">
      <div class="badge">Sistema de Contacto</div>

      <h1 class="title">
        <span class="title-line">Bienvenido</span>
        <span class="title-accent">al sistema</span>
      </h1>

      <p class="subtitle">
        Conecta con nosotros de manera rápida, segura y sin complicaciones.
      </p>

      <router-link to="/contacto" class="cta-link">
        <button class="cta-btn">
          <span class="btn-text">Ir al formulario</span>
          <span class="btn-icon">→</span>
          <div class="btn-shine"></div>
        </button>
      </router-link>

      <div class="footer-note">
        <span class="dot"></span>
        Disponible 24/7
      </div>
    </div>
  </div>
</template>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Syne:wght@400;600;700;800&family=DM+Sans:wght@300;400;500&display=swap");

.home {
  font-family: "DM Sans", sans-serif;
  min-height: 100vh;
  background: #080b14;
  display: flex;
  align-items: center;
  justify-content: center;
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
    rgba(99, 102, 241, 0.25) 0%,
    transparent 70%
  );
  top: -100px;
  left: -150px;
  animation: float 8s ease-in-out infinite;
}

.orb-2 {
  width: 400px;
  height: 400px;
  background: radial-gradient(
    circle,
    rgba(236, 72, 153, 0.18) 0%,
    transparent 70%
  );
  bottom: -80px;
  right: -100px;
  animation: float 10s ease-in-out infinite reverse;
}

.grid-overlay {
  position: absolute;
  inset: 0;
  background-image:
    linear-gradient(rgba(255, 255, 255, 0.03) 1px, transparent 1px),
    linear-gradient(90deg, rgba(255, 255, 255, 0.03) 1px, transparent 1px);
  background-size: 60px 60px;
  pointer-events: none;
}

.content {
  position: relative;
  z-index: 10;
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 0;
  animation: fadeUp 0.9s cubic-bezier(0.16, 1, 0.3, 1) both;
}

.badge {
  font-family: "DM Sans", sans-serif;
  font-size: 0.7rem;
  font-weight: 500;
  letter-spacing: 0.18em;
  text-transform: uppercase;
  color: #818cf8;
  background: rgba(99, 102, 241, 0.12);
  border: 1px solid rgba(99, 102, 241, 0.25);
  padding: 6px 16px;
  border-radius: 100px;
  margin-bottom: 32px;
  animation: fadeUp 0.9s 0.1s cubic-bezier(0.16, 1, 0.3, 1) both;
}

.title {
  font-family: "Syne", sans-serif;
  display: flex;
  flex-direction: column;
  line-height: 1.05;
  margin-bottom: 20px;
}

.title-line {
  font-size: clamp(3.5rem, 8vw, 6.5rem);
  font-weight: 800;
  color: #f1f5f9;
  letter-spacing: -0.03em;
  animation: fadeUp 0.9s 0.2s cubic-bezier(0.16, 1, 0.3, 1) both;
}

.title-accent {
  font-size: clamp(3.5rem, 8vw, 6.5rem);
  font-weight: 700;
  letter-spacing: -0.03em;
  background: linear-gradient(135deg, #818cf8 0%, #ec4899 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  animation: fadeUp 0.9s 0.3s cubic-bezier(0.16, 1, 0.3, 1) both;
}

.subtitle {
  font-size: 1.05rem;
  font-weight: 300;
  color: #64748b;
  max-width: 380px;
  line-height: 1.7;
  margin-bottom: 44px;
  animation: fadeUp 0.9s 0.4s cubic-bezier(0.16, 1, 0.3, 1) both;
}

.cta-link {
  text-decoration: none;
  animation: fadeUp 0.9s 0.5s cubic-bezier(0.16, 1, 0.3, 1) both;
}

.cta-btn {
  position: relative;
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 16px 36px;
  border: none;
  border-radius: 14px;
  background: linear-gradient(135deg, #6366f1 0%, #8b5cf6 100%);
  cursor: pointer;
  overflow: hidden;
  transition:
    transform 0.2s ease,
    box-shadow 0.2s ease;
  box-shadow:
    0 0 0 1px rgba(99, 102, 241, 0.4),
    0 20px 40px rgba(99, 102, 241, 0.3);
}

.cta-btn:hover {
  transform: translateY(-3px) scale(1.02);
  box-shadow:
    0 0 0 1px rgba(99, 102, 241, 0.6),
    0 28px 50px rgba(99, 102, 241, 0.45);
}

.cta-btn:active {
  transform: translateY(0) scale(0.99);
}

.btn-text {
  font-family: "DM Sans", sans-serif;
  font-size: 1rem;
  font-weight: 500;
  color: #fff;
  letter-spacing: 0.01em;
  position: relative;
  z-index: 1;
}

.btn-icon {
  font-size: 1.1rem;
  color: rgba(255, 255, 255, 0.8);
  transition: transform 0.2s ease;
  position: relative;
  z-index: 1;
}

.cta-btn:hover .btn-icon {
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
    rgba(255, 255, 255, 0.15),
    transparent
  );
  transition: left 0.5s ease;
}

.cta-btn:hover .btn-shine {
  left: 160%;
}

.footer-note {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-top: 28px;
  font-size: 0.8rem;
  color: #334155;
  font-weight: 400;
  animation: fadeUp 0.9s 0.6s cubic-bezier(0.16, 1, 0.3, 1) both;
}

.dot {
  width: 7px;
  height: 7px;
  border-radius: 50%;
  background: #22c55e;
  box-shadow: 0 0 8px rgba(34, 197, 94, 0.6);
  animation: pulse 2s infinite;
}

@keyframes fadeUp {
  from {
    opacity: 0;
    transform: translateY(24px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@keyframes float {
  0%,
  100% {
    transform: translateY(0px) scale(1);
  }
  50% {
    transform: translateY(-30px) scale(1.05);
  }
}

@keyframes pulse {
  0%,
  100% {
    box-shadow: 0 0 8px rgba(34, 197, 94, 0.6);
  }
  50% {
    box-shadow: 0 0 16px rgba(34, 197, 94, 1);
  }
}
</style>
