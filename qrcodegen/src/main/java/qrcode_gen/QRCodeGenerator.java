package qrcode_gen;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.UUID;

public class QRCodeGenerator {

    private static String QRCODE_PATH = "qr-codes/";

    public static void main(String[] args) throws Exception {
        QRCodeGenerator cg = new QRCodeGenerator();
        QRCodeMessage qrCode = new QRCodeMessage("Amazon", "Super discounts",
                "Your discount code: " + UUID.randomUUID(), LocalDateTime.now());
        println(cg.writeQRCode(qrCode));
        println(cg.readQRCode(QRCODE_PATH + "Amazon-qrcode.png"));
    }

    private String writeQRCode(QRCodeMessage qr) throws Exception {
        String qrcode = QRCODE_PATH + qr.getName() + "-qrcode.png";
        Hashtable<EncodeHintType, String> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = writer.encode(String.format("%s\n%s\n%s\n%s",
                qr.getName(), qr.getMessage(), qr.getDiscountCode(), qr.getLocalDateTime()),
                BarcodeFormat.QR_CODE, 350, 350, hints);

        Path path = FileSystems.getDefault().getPath(qrcode);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        return "QRCODE is generated successfully....";
    }

    private String readQRCode(String qrcodeImage) throws Exception {
        BufferedImage bufferedImage = ImageIO.read(new File(qrcodeImage));
        LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
        Result result = new MultiFormatReader().decode(binaryBitmap);
        return result.getText();

    }

    private static void println(String str) {
        System.out.println(str);
    }
}
