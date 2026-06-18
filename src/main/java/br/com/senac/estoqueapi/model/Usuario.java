package br.com.senac.estoqueapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "usuario_nome", nullable = false, length = 100)
    private String usuarioNome;

    @Column(name = "empresa_nome", length = 50)
    private String empresaNome;

    @Column(name = "usuario_email", nullable = false, unique = true, length = 100)
    private String usuarioEmail;

    @Column(name = "usuario_cpf", nullable = false, unique = true, length = 14)
    private String usuarioCpf;

    @Column(name = "usuario_senha", nullable = false, length = 255)
    private String usuarioSenha;

    @Column(name = "usuario_status")
    private Integer usuarioStatus;

    @PrePersist
    public void prePersist() {
        if (usuarioStatus == null) {
            usuarioStatus = 1;
        }
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    public String getEmpresaNome() {
        return empresaNome;
    }

    public void setEmpresaNome(String empresaNome) {
        this.empresaNome = empresaNome;
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }

    public String getUsuarioCpf() {
        return usuarioCpf;
    }

    public void setUsuarioCpf(String usuarioCpf) {
        this.usuarioCpf = usuarioCpf;
    }

    public String getUsuarioSenha() {
        return usuarioSenha;
    }

    public void setUsuarioSenha(String usuarioSenha) {
        this.usuarioSenha = usuarioSenha;
    }

    public Integer getUsuarioStatus() {
        return usuarioStatus;
    }

    public void setUsuarioStatus(Integer usuarioStatus) {
        this.usuarioStatus = usuarioStatus;
    }
}
