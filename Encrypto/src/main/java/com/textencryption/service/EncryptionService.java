package com.textencryption.service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

    private static SecretKey secretKey;

    public EncryptionService() {
        try {
            secretKey = generateKey(); // Generate AES Key
        } catch (Exception e) {
            throw new RuntimeException("Error initializing encryption service: " + e.getMessage());
        }
    }

    private SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256); // Using AES-256
        return keyGenerator.generateKey();
    }

    public String encryptText(String plainText) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            return "Error encrypting text: " + e.getMessage();
        }
    }

    public String decryptText(String encryptedText) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            return "Error decrypting text: " + e.getMessage();
        }
    }
}
