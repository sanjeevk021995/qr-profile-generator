package qr_profile_generator.service;


package com.example.demo.service;

import com.example.demo.model.User;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@Service
public class QRService {

    private static final Logger logger = LoggerFactory.getLogger(QRService.class);

    public String generateQRCode(User user) {
        String data =  "Name: " + user.getName() + "\nAge: " + user.getAge() + "\nAddress: " + user.getAddress(); // Data for QR
        String filePath = "user_" + user.getId() + ".png"; // Dynamic file name
        try {
            BitMatrix matrix = com.google.zxing.qrcode.QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, 300, 300);
            Path path = FileSystems.getDefault().getPath(filePath);
            MatrixToImageWriter.writeToPath(matrix, "PNG", path); // Save the image
            logger.info("QR code generated for user: {}", user.getId());
            return filePath; // Return the file path
        } catch (WriterException | IOException e) {
            logger.error("Error generating QR code: {}", e.getMessage());
            return null; // Or throw an exception
        }
    }
}