package com.textencryption.controller;

import com.textencryption.service.EncryptionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/encryption")
public class EncryptionController {

    private final EncryptionService encryptionService;

    public EncryptionController(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @PostMapping("/encrypt")
    public String encrypt(@RequestParam String text) {
        try {
            return encryptionService.encryptText(text);
        } catch (Exception e) {
            return "Error encrypting text: " + e.getMessage();
        }
    }

    @PostMapping("/decrypt")
    public String decrypt(@RequestParam String text) {
        try {
            return encryptionService.decryptText(text);
        } catch (Exception e) {
            return "Error decrypting text: " + e.getMessage();
        }
    }
}
