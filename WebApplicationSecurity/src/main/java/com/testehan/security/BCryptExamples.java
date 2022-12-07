package com.testehan.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;

public class BCryptExamples {

    // purpos is to do a benchmark test that compares the hash creation of ByCrypt with Sha3-256
    // a hashing that takes longer could mean protection against brute force attacks
    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    @Fork(value = 1, warmups = 2)
    @BenchmarkMode(Mode.Throughput)
    public static void generate10ByCryptHashes() {
        for (int i = 0; i < 1; i++) {
            final String password = "password12345";

            final String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        }
    }

    @Benchmark
    @Fork(value = 1, warmups = 2)
    @BenchmarkMode(Mode.Throughput)
    public static void generate10Sha3256ashes() {
        for (int i = 0; i < 1; i++) {
            final String password = "password12345" + BCrypt.gensalt(); // we also want the salt

            String hashedPassword = new DigestUtils("SHA3-256").digestAsHex(password);
        }
    }

    private static void simpleByCryptExample() {
        final String password = "password12345";

        final String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        if (BCrypt.checkpw(password, hashedPassword)) {
            System.out.println("Password is correct");
        } else {
            System.out.println("Password is incorect");
        }
    }
}
