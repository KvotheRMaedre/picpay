package tech.kvothe.picpay.entity;

import jakarta.persistence.*;

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
}
