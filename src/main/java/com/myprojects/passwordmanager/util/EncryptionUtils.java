package com.myprojects.passwordmanager.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
public class EncryptionUtils {

    private static final String ENCRYPTION_KEY = System.getenv("ENCRYPTION_KEY"); // Use a secure key here

    private static final StandardPBEStringEncryptor textEncryptor = new StandardPBEStringEncryptor();

    static {
        if (ENCRYPTION_KEY == null || ENCRYPTION_KEY.isEmpty()) {
            throw new IllegalArgumentException("ENCRYPTION_KEY environment variable is not set");
        }
        textEncryptor.setPassword(ENCRYPTION_KEY);
        textEncryptor.setAlgorithm("PBEWithMD5AndDES");
    }

    public static String encrypt(String input) {
        return textEncryptor.encrypt(input);
    }

    public static String decrypt(String encryptedInput) {
        return textEncryptor.decrypt(encryptedInput);
    }

}
