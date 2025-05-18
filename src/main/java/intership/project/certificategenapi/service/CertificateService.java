package intership.project.certificategenapi.service;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import intership.project.certificategenapi.model.CertificateDTO;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class CertificateService {

    public ResponseEntity<byte[]> generateCertificate(CertificateDTO certificateDTO) throws Exception{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD);
        Font subjectFont = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD);

        document.add(new Paragraph("Certificado de Conclusão", titleFont));
        document.add(Chunk.NEWLINE);
        document.add(new Paragraph("Certificamos que " + certificateDTO.participantsName(), subjectFont));
        document.add(new Paragraph("concluiu o curso: " + certificateDTO.courseName(), subjectFont));
        document.add(new Paragraph("Data: " + java.time.LocalDate.now(), subjectFont));

        document.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "certificado.pdf");
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=certificado.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(outputStream.toByteArray());

    }

    public ResponseEntity<byte[]> generateCertificateByTemplate(@RequestBody CertificateDTO certificateDTO, String templateNameFile) throws Exception{

    }

}
