package com.ivc.talonsystem.reportviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.ivc.talonsystem.entity.AbstractCompany;
import com.ivc.talonsystem.entity.Rju;
import com.ivc.talonsystem.entity.Violguide;
import com.ivc.talonsystem.util.BuildString;

public class PDFReportBuilderForRjuDetail extends AbstractITextPdfView {
	
	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*Report requirements:*/
			List<Rju> rjulist=(List<Rju>) model.get("companies");
			Map<Integer, Map<Violguide, Map<AbstractCompany, Integer>>> resultmap=(Map<Integer, Map<Violguide, Map<AbstractCompany, Integer>>>) model.get("report");
			Map<Integer, Map<AbstractCompany, Integer>> grandtotal=(Map<Integer, Map<AbstractCompany, Integer>>)model.get("grandtotal");
		/*End requirements:*/
		
		/*Font settings*/
			FontSelector selector1 = new FontSelector();
			Font font = FontFactory.getFont("c:/windows/fonts/times.ttf", BaseFont.IDENTITY_H, true);
			//Font font = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true);
			font.setSize(14);
			selector1.addFont(font);
			
			FontSelector selector2 = new FontSelector();
	        Font font2 = FontFactory.getFont("c:/windows/fonts/times.ttf", BaseFont.IDENTITY_H, true);
			//Font font2 = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true);
	        font2.setColor(BaseColor.WHITE);
	        selector2.addFont(font2);
	        
	        FontSelector selector3 = new FontSelector();
			Font contentfont = FontFactory.getFont("c:/windows/fonts/times.ttf", BaseFont.IDENTITY_H, true);
	        //Font contentfont = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true);
			contentfont.setSize(12);
			selector3.addFont(contentfont);
		/*end of font settings*/
		
		/*header*/
			StringBuilder sb=new StringBuilder();
			sb.append("ОТЧЕТ\r\n" + 
					"О ПРИЧИНАХ ИЗЪЯТЫХ ТАЛОНОВ-ПРЕДУПРЕЖДЕНИЯ № 1, 2, 3 ПО РЖУ АО \"УТЙ\" ");
			sb.append(model.get("datecriteria"));
			
	        doc.addTitle(sb.toString());
	        
	
			
			Paragraph header=new Paragraph(selector1.process(sb.toString()));
			header.setAlignment(Rectangle.ALIGN_CENTER);
			header.setSpacingAfter(10.0f);
			doc.add(header);
		/*end of header*/
		
		for(Map.Entry<Integer, Map<Violguide, Map<AbstractCompany, Integer>>> entry: resultmap.entrySet()) {
			/* Table content */
			int tablesize = rjulist.size() + 3;
			PdfPTable table = new PdfPTable(tablesize);
			List<Float> widths = new ArrayList<>();
			widths.add(0.25f); // for num column
			widths.add(2f);
			for (int i = 0; i < rjulist.size(); i++) {
				widths.add(0.2f);
			}
			widths.add(0.2f);
			Float[] widtharraytemp = new Float[widths.size()];
			widtharraytemp = widths.toArray(widtharraytemp);
			float[] widtharray = new float[widths.size()];
			int i = 0;

			for (Float f : widtharraytemp) {
				widtharray[i] = (f != null ? f : Float.NaN);
				i++;
			}

			table.setWidthPercentage(100.0f);
			table.setWidths(widtharray);
			table.setSpacingBefore(10);

			// define table header cell
			PdfPCell cell = new PdfPCell();
			cell.setBackgroundColor(BaseColor.DARK_GRAY);
			cell.setPadding(5);
			cell.setPhrase(new Phrase(selector2.process("№")));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell.setPhrase(new Phrase(selector2.process(BuildString.buildstring(new String[] {
					"Виды нарушений, при которых изъяты талон предупреждение №", String.valueOf(entry.getKey())
			}))));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			rjulist.stream().forEach(u -> {
				cell.setPhrase(new Phrase(selector2.process(u.getCallname())));
				cell.setRotation(90);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
			});

			cell.setPhrase(new Phrase(selector2.process("Общее")));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			int j = 0;
			for (Map.Entry<Violguide, Map<AbstractCompany, Integer>> violentry : entry.getValue().entrySet()) {
				j++;
				PdfPCell cell1 = new PdfPCell(new Phrase(selector3.process(String.valueOf(j))));
				PdfPCell cell2 = new PdfPCell(new Phrase(selector3.process(violentry.getKey().getContentViol())));
				table.addCell(cell1);
				table.addCell(cell2);
				int horsum = 0;
				for (int countpercomp : violentry.getValue().values()) {
					table.addCell(new Phrase(selector3.process((countpercomp!=0)?String.valueOf(countpercomp):"")));
					horsum += countpercomp;
				}
				PdfPCell cellhortotal = new PdfPCell(new Phrase(selector3.process((horsum!=0)?String.valueOf(horsum):"")));
				table.addCell(cellhortotal);

			}

			/* end of table content */
			j++;
			PdfPCell lastCell = new PdfPCell();
			lastCell.setBackgroundColor(BaseColor.DARK_GRAY);
			lastCell.setPhrase(selector2.process(String.valueOf(j)));
			table.addCell(lastCell);
			lastCell.setPhrase(selector2.process("Общее"));
			table.addCell(lastCell);
			/* Total values */
			int overall = 0;
			for (int total : grandtotal.get(entry.getKey()).values()) {
				lastCell.setPhrase(selector2.process((total!=0)?String.valueOf(total):""));
				table.addCell(lastCell);
				overall += total;
			}
			lastCell.setPhrase(selector2.process(String.valueOf(overall)));
			table.addCell(lastCell);
			/* end of total values */
			doc.add(table);
			doc.newPage();
		}
		
		
        

	}

}
