package ar.com.ada.cash.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "compras")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compra_id")
    private Integer compraId;
    private BigDecimal total;
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    @JsonIgnore
    private Usuario usuario;

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the compraId
     */
    public Integer getCompraId() {
        return compraId;
    }

    /**
     * @param compraId the compraId to set
     */
    public void setCompraId(Integer compraId) {
        this.compraId = compraId;
    }

}
