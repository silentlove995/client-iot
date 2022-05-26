package com.iot.client.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.iot.client.constants.AppConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author datdv
 */
@Component
public class QRCodeGenerator {

    private final String root = "/home/abc/image";


    private final String url = "http://monoku.ga:9091";

    public String generateQRCodeImage(String text, String filePath)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);

        String nameFile = String.format("QRCode_%s.png" , DateTimeUtils.getDateTimeNow("yyyyMMddHHmmssSSS"));
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

}
