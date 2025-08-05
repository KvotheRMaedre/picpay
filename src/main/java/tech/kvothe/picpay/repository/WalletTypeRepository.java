package tech.kvothe.picpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.kvothe.picpay.entity.WalletType;

public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {
}
