package it.uniroma3.siw.spring.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.spring.middleware.FileUploadUtil;
import it.uniroma3.siw.spring.model.Campo;
import it.uniroma3.siw.spring.service.CampoService;

@Controller
public class CampoController {

	@Autowired
	private CampoService campoService;
	
	@RequestMapping(value = "/campo/save", method = RequestMethod.POST)
	public String salvaCampo(Model model, @RequestParam("image1") MultipartFile multipartFile1, 
											@RequestParam("image2") MultipartFile multipartFile2) throws IOException{
		String fileName1 = StringUtils.cleanPath(multipartFile1.getOriginalFilename());
		String fileName2 = StringUtils.cleanPath(multipartFile2.getOriginalFilename());
		Campo c1 = new Campo();
		c1.setLarghezza(10);
		c1.setLunghezza(10);
		c1.setPrezzo(10);
		c1.setTipoCampo("out");
		c1.setTipoTerreno("sintetico");
		c1.setImg1(fileName1);
		c1.setImg2(fileName2);
		
		Campo savedCampo = this.campoService.inserisci(c1);
		
		String uploadDir = "campo-photos/" + savedCampo.getId();
		
		FileUploadUtil.saveFile(uploadDir, fileName1, multipartFile1);
		FileUploadUtil.saveFile(uploadDir, fileName2, multipartFile2);
		
		model.addAttribute("campi", this.campoService.tutti());
		
		return "campi.html";
	}
}
