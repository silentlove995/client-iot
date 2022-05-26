package com.iot.client.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.iot.client.constants.AppConstant;
import com.iot.client.utils.dto.GenCardCodeDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.UUID;

/**
 * @author datdv
 */
@Component
public class CodeGeneratorUtils {

    private final String root = "/home/abc/image";


    private final String url = "http://monoku.ga:9091";

    public String generateQRCodeImage(String text, String filePath)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);

        String nameFile = String.format("QRCode_%s.png", com.iot.client.utils.DateTimeUtils.getDateTimeNow("yyyyMMddHHmmssSSS"));
        Path rootCustomFileName = Paths.get(root + filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, AppConstant.image.type.PNG, rootCustomFileName.resolve(nameFile));

        return url + "/image" + filePath + "/" + nameFile;
    }


    public byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, AppConstant.image.type.PNG, pngOutputStream);
        return pngOutputStream.toByteArray();
    }

    public GenCardCodeDTO generateCodeCard(int lastCode) {
        lastCode++;
        GenCardCodeDTO result = new GenCardCodeDTO();
        StringBuilder resultCode = new StringBuilder();
        int start = 9 - (int) (Math.log10(lastCode) + 1);
        while (start > 0) {
            resultCode.append("0");
            start--;
        }
        result.setLastCode(lastCode);
        resultCode.append(lastCode);
        result.setCodeCard(resultCode.toString());
        return result;
    }

    public String randomPassword() {
        Random rand = new Random();
        int n = rand.nextInt(899999);
        n = n + 100000;
        return n + "";
    }

    public String randomUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
