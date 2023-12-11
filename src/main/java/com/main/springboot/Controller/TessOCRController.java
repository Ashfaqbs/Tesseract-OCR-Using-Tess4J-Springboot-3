/**
 * Code developed by Ashfaq (© [Year])
 * GitHub: https://github.com/DarkSharkAsh
 */

package com.main.springboot.Controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
//source='https://www.youtube.com/watch?v=8_M9LaWE1Uc ,https://sourceforge.net/projects/tess4j/'
@RestController
public class TessOCRController {

	@PostMapping("/v2/OCR")
	public String default_View(@RequestParam("file") MultipartFile file) {
		String result = " ";

		// logic Save the file temporarily and access
		File tempFile = new File(System.getProperty("java.io.tmpdir") + "/" + UUID.randomUUID().toString() + ".jpg");

		try {
			Tesseract tesseract = new Tesseract();
			file.transferTo(tempFile);
			tesseract.setDatapath("C:\\Users\\Ashfaq PC\\Downloads\\Tesseract-JAVA-OCR\\Tess4J\\tessdata");

			result = tesseract.doOCR(tempFile);

			System.out.println("sentence : " + result);
		} catch (TesseractException | IllegalStateException | IOException e) {
			// TODO: handle exception
		}
		tempFile.delete();

		return result;
	}

	@GetMapping("/v1/OCR")
	public String default_View() {

		String result = " ";
//		"C:\Users\Ashfaq PC\Downloads\textimg2.png" ,"C:\Users\Ashfaq PC\Downloads\textimg1.png","C:\Users\Ashfaq PC\Downloads\textimg.png"
//		"C:\Users\Ashfaq PC\Downloads\imagetext.jpg"
		File file = new File("\\Users\\Ashfaq PC\\Downloads\\textimg1.png");
		Tesseract tesseract = new Tesseract();
		try {
			tesseract.setDatapath("C:\\Users\\Ashfaq PC\\Downloads\\Tesseract-JAVA-OCR\\Tess4J\\tessdata");
			result = tesseract.doOCR(file);
			System.out.println("sentence : " + result);
		} catch (TesseractException e) {
			// TODO: handle exception
		}

		return result;
	}

}
