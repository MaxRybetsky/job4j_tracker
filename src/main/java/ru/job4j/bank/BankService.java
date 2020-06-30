package ru.job4j.bank;

import java.util.*;

public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    public void addAccount(String passport, Account account) {
        User user = this.findByPassport(passport).orElse(null);
        if (user != null) {
            List<Account> accounts = users.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    public Optional<User> findByPassport(String passport) {
        return users.keySet()
                .stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst();
    }

    public Optional<Account> findByRequisite(String passport, String requisite) {
        User user = this.findByPassport(passport).orElse(null);
        return user != null
                ? users.get(user)
                        .stream()
                        .filter(account -> account.getRequisite().equals(requisite))
                        .findFirst()
                : Optional.empty();
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        Account srcAccount = this.findByRequisite(srcPassport, srcRequisite).orElse(null);
        Account destAccount = this.findByRequisite(destPassport, destRequisite).orElse(null);
        if (srcAccount == null || srcAccount.getBalance() < amount || destAccount == null) {
            return false;
        }
        srcAccount.setBalance(srcAccount.getBalance() - amount);
        destAccount.setBalance(destAccount.getBalance() + amount);
        return true;
    }
}