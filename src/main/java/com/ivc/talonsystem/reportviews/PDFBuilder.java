package com.ivc.talonsystem.reportviews;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
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
import com.ivc.talonsystem.entity.UnitDepart;
import com.ivc.talonsystem.entity.User;
import com.ivc.talonsystem.entity.Violation;
import com.ivc.talonsystem.util.BuildString;
import com.ivc.talonsystem.util.DateFormatter;
import com.ivc.talonsystem.util.GenerateMapUtil;

public class PDFBuilder extends AbstractITextPdfView {
	
//	@Autowired
//	private ApplicationContext appContext;
	
//	@Autowired
//	Rotate event;
	
//	public String FONT = "src/main/resources/fonts/times.ttf";
	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		/*end of font selectors*/
		List<Violation> violationlist=(List<Violation>)model.get("listViolations");
		User user=(User)request.getSession().getAttribute("currentuser");
		/*header*/
		StringBuilder sb=new StringBuilder();
		sb.append("Список изъятых талонов по ");
		AbstractCompany comp=(AbstractCompany)model.get("company");
		if(comp!=null) {
			sb.append(comp.getCallname());
	        doc.addTitle(sb.toString());
		}else {
			sb.append(user.getCompany().getCallname());
			doc.addTitle(sb.toString());
		}
		Date d1=(Date)model.get("date1");
		Date d2=(Date)model.get("date2");
		if(d1!=null&&d2!=null) {
			sb.append(" c ");
			sb.append(DateFormatter.format(d1));
			sb.append(" по ");
			sb.append(DateFormatter.format(d2));
		}else if(d1!=null&&d2==null) {
			sb.append(" на ");
			sb.append(DateFormatter.format(d1));
		}else if(d1==null&&d2!=null) {
			sb.append(" на ");
			sb.append(DateFormatter.format(d2));
		}
		
		Paragraph header=new Paragraph(selector1.process(sb.toString()));
		header.setAlignment(Rectangle.ALIGN_CENTER);
		header.setSpacingAfter(10.0f);
		/*end of header*/
		doc.add(header);
		
		/*Table content*/
		PdfPTable table = new PdfPTable(11);

        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {0.25f, 1.0f, 0.9f, 0.9f, 0.5f, 2.1f, 1.0f, 0.8f, 1.0f, 0.8f, 1.0f});
        table.setSpacingBefore(10);
        
        
        
        
         
        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.DARK_GRAY);
        cell.setPadding(5);
        cell.setPhrase(new Phrase(selector2.process("№")));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase(selector2.process("Фамилия")));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase(selector2.process("Должность")));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase(selector2.process("Место Работы")));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase(selector2.process("№ талона")));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase(selector2.process("Характер нарушения")));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase(selector2.process("Кем изъят")));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase(selector2.process("Дата изъятия")));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase(selector2.process("№ приказа")));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase(selector2.process("Дата приказа")));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase(selector2.process("Принятые меры")));
        table.addCell(cell);
        int i=0;
        for(Violation v:violationlist) {
        	i++;
        	PdfPCell cell1=new PdfPCell(new Phrase(selector3.process(String.valueOf(i))));
        	PdfPCell cell2=new PdfPCell(new Phrase(
        			selector3.process(
        					BuildString.buildstring(new String[] {v.getSurnameEmpl(),v.getNameEmpl(),v.getLastnameEmpl()}))));
        	PdfPCell cell3=new PdfPCell(new Phrase(selector3.process(v.getPost()!=null ? v.getPost().getPostname():"")));
        	PdfPCell cell4=new PdfPCell(new Phrase(selector3.process(v.getCompany().getCallname())));
        	PdfPCell cell5=new PdfPCell(new Phrase(selector3.process(v.getStubnum().toString())));
        	PdfPCell cell6=new PdfPCell(new Phrase(selector3.process(v.getViolguide().getContentViol())));
        	PdfPCell cell7=new PdfPCell(new Phrase(selector3.process(v.getSeizedFrom())));
        	PdfPCell cell8=new PdfPCell(new Phrase(selector3.process(DateFormatter.format(v.getDateseize()))));
        	PdfPCell cell9=new PdfPCell(new Phrase(selector3.process(v.getNumorder())));
        	PdfPCell cell10=new PdfPCell(new Phrase(selector3.process(DateFormatter.format(v.getDateorder()))));
        	PdfPCell cell11=new PdfPCell(new Phrase(selector3.process(v.getMeasure())));
        	if(i%2==0) {
        		cell1.setBackgroundColor(BaseColor.PINK);
        		cell2.setBackgroundColor(BaseColor.PINK);
        		cell3.setBackgroundColor(BaseColor.PINK);
        		cell4.setBackgroundColor(BaseColor.PINK);
        		cell5.setBackgroundColor(BaseColor.PINK);
        		cell6.setBackgroundColor(BaseColor.PINK);
        		cell7.setBackgroundColor(BaseColor.PINK);
        		cell8.setBackgroundColor(BaseColor.PINK);
        		cell9.setBackgroundColor(BaseColor.PINK);
        		cell10.setBackgroundColor(BaseColor.PINK);
        		cell11.setBackgroundColor(BaseColor.PINK);
        	}
        	
        	table.addCell(cell1);
        	table.addCell(cell2);
        	table.addCell(cell3);
        	table.addCell(cell4);
        	table.addCell(cell5);
        	table.addCell(cell6);
        	table.addCell(cell7);
        	table.addCell(cell8);
        	table.addCell(cell9);
        	table.addCell(cell10);
        	table.addCell(cell11);
        }
        /*end of table content*/
        doc.add(table);

        
        /*Total values*/
        if(user.getCompany() instanceof Rju || user.getCompany() instanceof UnitDepart || user.getCompany() instanceof AbstractCompany) {
        	doc.newPage();
            Paragraph total=new Paragraph(selector1.process("Итого"));
            total.setAlignment(Rectangle.ALIGN_CENTER);
            total.setSpacingBefore(10.0f);
            total.setSpacingAfter(10.0f);
    		doc.add(total);
    	
    		Map<AbstractCompany, Long> countpercompany=GenerateMapUtil.
    				getReverseSortedMap(GenerateMapUtil.getCountPerCompany(violationlist));
    		
    		/*Total table content*/
//    		int size=countpercompany.size();
//    		float[] widthsize=new float[size];
//    		for(int j=0; j<widthsize.length; j++) {
//    			widthsize[j]=1.25f;
//    		}
    		PdfPTable totaltable = new PdfPTable(2);
    		totaltable.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
    		totaltable.setWidthPercentage(25.0f);
    		totaltable.setWidths(new float[] {1.0f,0.30f});
    		totaltable.setSpacingBefore(10);
    		
    		 	PdfPCell totalcellheader = new PdfPCell();
    		 	totalcellheader.setBackgroundColor(BaseColor.DARK_GRAY);
    		 	totalcellheader.setPadding(5);
    		 	totalcellheader.setPhrase(new Phrase(selector2.process("Название предприятии")));
    		 	totaltable.addCell(totalcellheader);
    	        
    	        totalcellheader.setPhrase(new Phrase(selector2.process("Количество изъятых талонов")));
    	        totaltable.addCell(totalcellheader);
    		
    		
    		for(Map.Entry<AbstractCompany, Long> entry : countpercompany.entrySet()) {
    			PdfPCell totalcellkey = new PdfPCell();
    			totalcellkey.setPhrase(new Phrase(selector3.process(entry.getKey().getCallname())));
    			totaltable.addCell(totalcellkey);
    			
    			PdfPCell totalcellvalue = new PdfPCell();
    			totalcellvalue.setPhrase(new Phrase(selector3.process(entry.getValue().toString())));
    			totaltable.addCell(totalcellvalue);
    		}
    		doc.add(totaltable);
    		
    		 /*Total values*/
        }
       
		int overallsize=violationlist.size();
		
        Paragraph overall=new Paragraph(selector1.
        		process(BuildString.buildstring(new String[]{"Общее:",String.valueOf(overallsize)}).toString()));
        overall.setAlignment(Rectangle.ALIGN_LEFT);
        overall.setSpacingBefore(10.0f);
		doc.add(overall);
        

	}

}
