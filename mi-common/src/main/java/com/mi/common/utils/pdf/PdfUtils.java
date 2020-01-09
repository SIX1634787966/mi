package com.mi.common.utils.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.util.ClassUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class PdfUtils {
    public static void exoprt(HttpServletRequest request, HttpServletResponse response,String title, String text){
        BaseFont chinese = null;
        try {
            chinese = BaseFont.createFont( ClassUtils.getDefaultClassLoader().getResource("").getPath() +"static/file/msyh.ttc,0", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Font font = new Font(chinese, 14, Font.NORMAL);
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        // 下载文件能正常显示中文
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(title+".pdf", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Step 1—Create a Document.
        Document document = new Document();
//Step 2—Get a PdfWriter instance.
        try {
            try {
                PdfWriter.getInstance(document, response.getOutputStream());
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//Step 3—Open the Document.
        document.open();
//Step 4—Add content.
        try {
            document.add(new Paragraph(text,font));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
//Step 5—Close the Document.
        document.close();
    }
}
