import java.awt.image.BufferedImage;
import java.util.UUID;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

public class QRGenerator {

    public static class QRResult {
        public String text;          // texto aleatorio generado
        public BufferedImage image;  // imagen QR generada
    }

    public static QRResult generate() throws Exception {
        QRResult result = new QRResult();

        // 1. Generar texto aleatorio
        result.text = UUID.randomUUID().toString();

        // 2. Generar imagen QR
        BitMatrix matrix = new MultiFormatWriter()
                .encode(result.text, BarcodeFormat.QR_CODE, 300, 300);

        result.image = MatrixToImageWriter.toBufferedImage(matrix);

        return result;
    }
}
