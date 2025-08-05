package tech.kvothe.picpay.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import tech.kvothe.picpay.entity.WalletType;
import tech.kvothe.picpay.repository.WalletTypeRepository;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final WalletTypeRepository walletTypeRepository;

    public DataLoader(WalletTypeRepository walletTypeRepository) {
        this.walletTypeRepository = walletTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(WalletType.Values.values())
                .map(WalletType.Values::toWalletType)
                .forEach(walletTypeRepository::save);
    }
}
