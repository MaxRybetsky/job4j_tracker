package ru.job4j.bank;

import java.util.*;
import java.util.stream.Collectors;

public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    public void addAccount(String passport, Account account) {
        User user = this.findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    public User findByPassport(String passport) {
        List<User> result = users.keySet()
                .stream()
                .filter(user -> user.getPassport().equals(passport))
                .collect(Collectors.toList());
        return result.size() != 0 ? result.get(0) : null;
    }

    public Account findByRequisite(String passport, String requisite) {
        List<Account> result;
        User user = this.findByPassport(passport);
        if (user != null) {
            result = users.get(user)
                    .stream()
                    .filter(account -> account.getRequisite().equals(requisite))
                    .collect(Collectors.toList());
        } else {
            result = new ArrayList<>();
        }
        return result.size() != 0 ? result.get(0) : null;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        Account srcAccount = this.findByRequisite(srcPassport, srcRequisite);
        Account destAccount = this.findByRequisite(destPassport, destRequisite);
        if (srcAccount == null || srcAccount.getBalance() < amount || destAccount == null) {
            return false;
        }
        srcAccount.setBalance(srcAccount.getBalance() - amount);
        destAccount.setBalance(destAccount.getBalance() + amount);
        return true;
    }
}