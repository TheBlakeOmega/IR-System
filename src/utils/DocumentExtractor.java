package utils;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import documents.Document;

public class DocumentExtractor {
	
	private PDFParser parser;
	private PDFTextStripper pdfStripper;
	private PDDocument pdDoc;
	private COSDocument cosDoc;
	
	public Document getDocument(File pdf) throws IOException {
		
		parser = new PDFParser(new RandomAccessFile(pdf, "r"));
		parser.parse();
        cosDoc = parser.getDocument();
        pdfStripper = new PDFTextStripper();
        pdDoc = new PDDocument(cosDoc);
        pdDoc.getNumberOfPages();
        pdfStripper.setStartPage(0);
        pdfStripper.setEndPage(pdDoc.getNumberOfPages());
        String text = pdfStripper.getText(pdDoc);
		Document doc = new Document(pdf, text);
		return doc;
		
	}
}
