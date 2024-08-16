package com.seuusuario.expensetracker.config;

import com.seuusuario.expensetracker.entity.Category;
import com.seuusuario.expensetracker.entity.Transaction;
import com.seuusuario.expensetracker.entity.User;
import com.seuusuario.expensetracker.repository.CategoryRepository;
import com.seuusuario.expensetracker.repository.TransactionRepository;
import com.seuusuario.expensetracker.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, CategoryRepository categoryRepository, TransactionRepository transactionRepository) {
        return args -> {
            User user = new User();
            user.setUsername("gustavoInTech");
            user.setEmail("gustavoInTech@example.com");
            user.setPassword("password");
            userRepository.save(user);

            Category category = new Category();
            category.setName("Food");
            category.setDescription("Food-related expenses");
            categoryRepository.save(category);

            Transaction transaction = new Transaction();
            transaction.setType("EXPENSE");
            transaction.setAmount(new BigDecimal("15.00"));
            transaction.setDate(LocalDateTime.now());
            transaction.setUser(user);
            transaction.setCategory(category);
            transactionRepository.save(transaction);
        };
    }
}
