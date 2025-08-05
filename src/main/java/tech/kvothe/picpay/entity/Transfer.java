package tech.kvothe.picpay.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "payee_wallet_id")
    private Wallet payee;

    @ManyToOne
    @JoinColumn(name = "payer_wallet_id")
    private Wallet payer;

    public Transfer() {
    }

    public Transfer(BigDecimal value, Wallet payee, Wallet payer) {
        this.value = value;
        this.payee = payee;
        this.payer = payer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Wallet getPayee() {
        return payee;
    }

    public void setPayee(Wallet payee) {
        this.payee = payee;
    }

    public Wallet getPayer() {
        return payer;
    }

    public void setPayer(Wallet payer) {
        this.payer = payer;
    }
}
