package com.virtusa.springboot.BankingSystemSpringBoot.helper;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class UidGenerator {

    private Random random = new Random();

    public Long generate14DigitAccountNumber() {
        Long accountNumber;

        int firstDigit = 1 + random.nextInt(8);

        long remainingDigits = (long) (random.nextDouble() * 1_000_000_000_000_0L);

        accountNumber = Long.parseLong(firstDigit + String.format("%013d", remainingDigits));

        return accountNumber;
    }

    public Long generate16DigitCardNumber() {

        Long cardNumber;

        int firstDigit = 1 + random.nextInt(8);

        long remainingDigits = (long) (random.nextDouble() * 1_000_000_000_000_000L);

        cardNumber = Long.parseLong(firstDigit + String.format("%015d", remainingDigits));

        return cardNumber;
    }

    public Integer generate6DigitTPin() {
        Integer tPin;
        int firstDigit = 1 + random.nextInt(8);
        int remainingDigits = random.nextInt(100000);
        tPin = Integer.parseInt(firstDigit + String.format("%05d", remainingDigits));

        return tPin;
    }

    public Integer generate4DigitCardPin() {
        Integer cardPin;
        int firstDigit = 1 + random.nextInt(8);
        int remainingDigits = random.nextInt(1000);
        cardPin = Integer.parseInt(firstDigit + String.format("%03d", remainingDigits));

        return cardPin;
    }

    public Integer generate3DigitCardCvv() {
        Integer cardCvv;
        int firstDigit = 1 + random.nextInt(8);
        int remainingDigits = random.nextInt(100);
        cardCvv = Integer.parseInt(firstDigit + String.format("%02d", remainingDigits));

        return cardCvv;
    }


    public Long generate12DigitApplicationNumber() {
        Long applicationNumber;

        int firstDigit = 1 + random.nextInt(8);

        long remainingDigits = (long) (random.nextDouble() * 1_000_000_000_00L);

        applicationNumber = Long.parseLong(firstDigit + String.format("%011d", remainingDigits));

        return applicationNumber;
    }
}
