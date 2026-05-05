import { useState } from "react";

const styles = {
  wrapper: {
    minHeight: "100vh",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    background: "#0f0f0f",
    fontFamily: "'DM Sans', sans-serif",
    padding: "1rem",
  },
  card: {
    background: "#1a1a1a",
    border: "1px solid #2a2a2a",
    borderRadius: "16px",
    padding: "2.5rem 2rem",
    width: "100%",
    maxWidth: "400px",
  },
  footer: {
    position: "fixed",
    bottom: "12px",
    width: "100%",
    textAlign: "center",
    fontSize: "12px",
    color: "#777",
    letterSpacing: "0.08em",
    opacity: 0.7,
  },
  iconCircle: {
    width: "52px",
    height: "52px",
    borderRadius: "50%",
    background: "#1e2a1e",
    border: "1px solid #2d3d2d",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    margin: "0 auto 1.5rem",
  },
  heading: {
    fontSize: "22px",
    fontWeight: "600",
    color: "#f0f0f0",
    textAlign: "center",
    marginBottom: "0.5rem",
    letterSpacing: "-0.3px",
  },
  subtitle: {
    fontSize: "14px",
    color: "#666",
    textAlign: "center",
    marginBottom: "1.75rem",
    lineHeight: "1.6",
  },
  emailHighlight: {
    color: "#7ebe7e",
    fontWeight: "500",
  },
  label: {
    display: "block",
    fontSize: "13px",
    color: "#888",
    marginBottom: "6px",
    letterSpacing: "0.02em",
  },
  input: {
    width: "100%",
    padding: "12px 14px",
    fontSize: "15px",
    background: "#111",
    border: "1px solid #2a2a2a",
    borderRadius: "10px",
    color: "#f0f0f0",
    outline: "none",
    marginBottom: "1rem",
    transition: "border-color 0.2s",
    boxSizing: "border-box",
  },
  inputFocus: {
    borderColor: "#7ebe7e",
  },
  btn: {
    width: "100%",
    padding: "13px",
    fontSize: "15px",
    fontWeight: "600",
    background: "#7ebe7e",
    color: "#0f1a0f",
    border: "none",
    borderRadius: "10px",
    cursor: "pointer",
    letterSpacing: "0.01em",
    transition: "opacity 0.2s, transform 0.1s",
  },
  btnDisabled: {
    opacity: 0.5,
    cursor: "not-allowed",
  },
  alertSuccess: {
    background: "#1a2e1a",
    border: "1px solid #2d4a2d",
    color: "#7ebe7e",
    fontSize: "13px",
    padding: "10px 14px",
    borderRadius: "8px",
    marginBottom: "1rem",
  },
  alertError: {
    background: "#2e1a1a",
    border: "1px solid #4a2d2d",
    color: "#e07070",
    fontSize: "13px",
    padding: "10px 14px",
    borderRadius: "8px",
    marginBottom: "1rem",
  },
  backLink: {
    fontSize: "13px",
    color: "#555",
    textAlign: "center",
    marginTop: "1rem",
    cursor: "pointer",
    transition: "color 0.2s",
  },
  successIcon: {
    width: "64px",
    height: "64px",
    borderRadius: "50%",
    background: "#1e2e1e",
    border: "1px solid #3a5a3a",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    margin: "0 auto 1.5rem",
  },
};

function IconEmail() {
  return (
    <svg width="22" height="22" fill="none" stroke="#7ebe7e" strokeWidth="1.8" viewBox="0 0 24 24">
      <rect x="2" y="4" width="20" height="16" rx="3" />
      <path d="M2 7l10 7 10-7" />
    </svg>
  );
}

function IconLock() {
  return (
    <svg width="22" height="22" fill="none" stroke="#7ebe7e" strokeWidth="1.8" viewBox="0 0 24 24">
      <rect x="3" y="11" width="18" height="11" rx="2" />
      <path d="M7 11V7a5 5 0 0 1 10 0v4" />
    </svg>
  );
}

function IconCheck() {
  return (
    <svg width="28" height="28" fill="none" stroke="#7ebe7e" strokeWidth="2" viewBox="0 0 24 24">
      <path d="M5 13l4 4L19 7" strokeLinecap="round" strokeLinejoin="round" />
    </svg>
  );
}

function InputField({ label, ...props }) {
  const [focused, setFocused] = useState(false);
  return (
    <div>
      <label style={styles.label}>{label}</label>
      <input
        style={{ ...styles.input, ...(focused ? styles.inputFocus : {}) }}
        onFocus={() => setFocused(true)}
        onBlur={() => setFocused(false)}
        {...props}
      />
    </div>
  );
}

function Alert({ type, message }) {
  if (!message) return null;
  return <div style={type === "error" ? styles.alertError : styles.alertSuccess}>{message}</div>;
}

function TelaEmail({ onSuccess }) {
  const [email, setEmail] = useState("");
  const [loading, setLoading] = useState(false);
  const [alert, setAlert] = useState(null);

  async function handleSubmit() {
    if (!email.trim()) {
      setAlert({ type: "error", message: "Por favor, informe seu e-mail." });
      return;
    }
    setLoading(true);
    setAlert(null);
    try {
      const res = await fetch("https://validarcodigo.onrender.com/api/enviar", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email: email.trim() }),
      });
      if (res.ok) {
        localStorage.setItem("email", email.trim());
        onSuccess(email.trim());
      } else {
        setAlert({ type: "error", message: "Erro ao enviar o código. Tente novamente." });
      }
    } catch {
      setAlert({ type: "error", message: "Erro de conexão. Verifique o servidor." });
    } finally {
      setLoading(false);
    }
  }

  return (
    <div style={styles.card}>
      <div style={styles.iconCircle}>
        <IconEmail />
      </div>
      <h2 style={styles.heading}>Verificar e-mail</h2>
      <p style={styles.subtitle}>Digite seu e-mail para receber o código de verificação.</p>
      <Alert {...(alert || {})} message={alert?.message} />
      <InputField
        label="E-mail"
        type="email"
        placeholder="seu@email.com"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        onKeyDown={(e) => e.key === "Enter" && handleSubmit()}
      />
      <button
        style={{ ...styles.btn, ...(loading ? styles.btnDisabled : {}) }}
        onClick={handleSubmit}
        disabled={loading}
      >
        {loading ? "Enviando..." : "Enviar código"}
      </button>
    </div>
  );
}

function TelaCodigo({ email, onSuccess, onVoltar }) {
  const [codigoUsuario, setCodigoUsuario] = useState("");
  const [loading, setLoading] = useState(false);
  const [alert, setAlert] = useState(null);

  async function handleSubmit() {
    if (!codigoUsuario.trim()) {
      setAlert({ type: "error", message: "Por favor, informe o código." });
      return;
    }
    setLoading(true);
    setAlert(null);
    try {
      const res = await fetch("https://validarcodigo.onrender.com/api/validar", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, codigoUsuario: Number(codigoUsuario.trim()) }),
      });
      const data = await res.json();
      if (data.success) {
        localStorage.removeItem("email");
        onSuccess(data.message);
      } else {
        setAlert({ type: "error", message: data.message || "Código inválido, tente novamente." });
      }
    } catch {
      setAlert({ type: "error", message: "Erro de conexão. Verifique o servidor." });
    } finally {
      setLoading(false);
    }
  }

  return (
    <div style={styles.card}>
      <div style={styles.iconCircle}>
        <IconLock />
      </div>
      <h2 style={styles.heading}>Digite o código</h2>
      <p style={styles.subtitle}>
        Enviamos um código para <span style={styles.emailHighlight}>{email}</span>
      </p>
      <Alert {...(alert || {})} message={alert?.message} />
      <InputField
        label="Código de verificação"
        type="text"
        placeholder="Ex: 123456"
        maxLength={10}
        value={codigoUsuario}
        onChange={(e) => setCodigoUsuario(e.target.value)}
        onKeyDown={(e) => e.key === "Enter" && handleSubmit()}
      />
      <button
        style={{ ...styles.btn, ...(loading ? styles.btnDisabled : {}) }}
        onClick={handleSubmit}
        disabled={loading}
      >
        {loading ? "Verificando..." : "Verificar código"}
      </button>
      <p style={styles.backLink} onClick={onVoltar}>
        ← Usar outro e-mail
      </p>
    </div>
  );
}

function TelaSucesso({ message }) {
  return (
    <div style={styles.card}>
      <div style={styles.successIcon}>
        <IconCheck />
      </div>
      <h2 style={styles.heading}>Tudo certo!</h2>
      <p style={styles.subtitle}>{message || "Código validado com sucesso."}</p>
    </div>
  );
}

export default function VerificacaoEmail() {
  const [tela, setTela] = useState("email");
  const [email, setEmail] = useState("");
  const [successMsg, setSuccessMsg] = useState("");

  return (
    <>
      <link
        href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;600&display=swap"
        rel="stylesheet"
      />
      <div style={styles.wrapper}>
        {tela === "email" && (
          <TelaEmail onSuccess={(e) => { setEmail(e); setTela("codigo"); }} />
        )}
        {tela === "codigo" && (
          <TelaCodigo
            email={email}
            onSuccess={(msg) => { setSuccessMsg(msg); setTela("sucesso"); }}
            onVoltar={() => setTela("email")}
          />
        )}
        {tela === "sucesso" && <TelaSucesso message={successMsg} />}
      </div>

      <div style={styles.footer}>
        @Phelipe,2026
      </div>
    </>
  );
}