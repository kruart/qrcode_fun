package qrcode_gen;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class QRCodeMessage {

    private String name;
    private String message;
    private String discountCode;
    private String localDateTime;

    public QRCodeMessage(String name, String message, String discountCode, LocalDateTime localDateTime) {
        this.name = name;
        this.message = message;
        this.discountCode = discountCode;
        this.localDateTime = formatDate(localDateTime);;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = formatDate(localDateTime);
    }

    private String formatDate(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }
}
