package dev.duguetvincent.jobsboardback.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import dev.duguetvincent.jobsboardback.entity.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfExportService {

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String[] HEADERS = {"Titre", "Entreprise", "Lieu", "Remote", "Contrat", "Statut", "Candidature", "URL"};

    public byte[] exportJobs(List<Job> jobs) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Document document = new Document(PageSize.A4.rotate(), 20, 20, 40, 30);
            PdfWriter.getInstance(document, out);
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph title = new Paragraph("Export Jobs Board", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(16);
            document.add(title);

            PdfPTable table = new PdfPTable(HEADERS.length);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{3f, 2.5f, 2f, 1.5f, 1.5f, 1.5f, 1.8f, 3f});

            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9, Color.WHITE);
            for (String header : HEADERS) {
                PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
                cell.setBackgroundColor(new Color(45, 85, 160));
                cell.setPadding(6);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 8);
            boolean alternate = false;
            for (Job job : jobs) {
                Color bg = alternate ? new Color(240, 244, 255) : Color.WHITE;
                addCell(table, job.getTitle(), cellFont, bg);
                addCell(table, job.getCompany(), cellFont, bg);
                addCell(table, job.getLocation(), cellFont, bg);
                addCell(table, job.getRemote() != null ? job.getRemote().name() : "", cellFont, bg);
                addCell(table, job.getContractType() != null ? job.getContractType().name() : "", cellFont, bg);
                addCell(table, job.getStatus() != null ? job.getStatus().name() : "", cellFont, bg);
                addCell(table, job.getAppliedAt() != null ? job.getAppliedAt().format(DATE_FMT) : "", cellFont, bg);
                addCell(table, job.getUrl() != null ? job.getUrl() : "", cellFont, bg);
                alternate = !alternate;
            }

            document.add(table);
            document.close();
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération du PDF", e);
        }
    }

    private void addCell(PdfPTable table, String value, Font font, Color bg) {
        PdfPCell cell = new PdfPCell(new Phrase(value != null ? value : "", font));
        cell.setBackgroundColor(bg);
        cell.setPadding(5);
        table.addCell(cell);
    }
}
