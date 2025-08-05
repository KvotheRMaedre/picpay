package tech.kvothe.picpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.kvothe.picpay.entity.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
