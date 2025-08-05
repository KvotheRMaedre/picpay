package tech.kvothe.picpay.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "wallet_type")
public class WalletType {

    @Id
    private Long id;

    @Column(name = "description")
    private String description;

    public WalletType() {
    }

    public WalletType(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public enum Values {
        USER(1L, "user"),
        MERCHANT(2L, "merchant");

        private final long id;
        private final String description;

        Values(long id, String description) {
            this.id = id;
            this.description = description;
        }

        public WalletType toWalletType(){
            return new WalletType(id, description);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WalletType that = (WalletType) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }
}
