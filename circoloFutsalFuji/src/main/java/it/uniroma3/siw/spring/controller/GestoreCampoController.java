package it.uniroma3.siw.spring.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.spring.controller.validator.CampoValidator;
import it.uniroma3.siw.spring.middleware.FileUploadUtil;
import it.uniroma3.siw.spring.model.Campo;
import it.uniroma3.siw.spring.service.CampoService;

@Controller
public class GestoreCampoController {

	@Autowired
	private CampoService campoService;

	@Autowired
	private CampoValidator campoValidator;

	@RequestMapping(value = "/aggiungiCampo", method = RequestMethod.GET)
	public String newCampo(Model model) {
		model.addAttribute("campo", new Campo());
		return "aggiungiCampoForm.html";
	}

	@RequestMapping(value = "/aggiungiCampo", method = RequestMethod.POST)
	public String salvaCampo(@ModelAttribute(name = "campo") Campo campo, Model model, HttpSession session,
			@RequestParam("image1") MultipartFile multipartFile1, @RequestParam("image2") MultipartFile multipartFile2,
			BindingResult campoBindingResult) throws IOException {

		String fileName1;
		String fileName2;
		if (session.getAttribute("image1") != null) {
			fileName1 = (String) session.getAttribute("image1");
		}
		if (session.getAttribute("image2") != null) {
			fileName2 = (String) session.getAttribute("image2");
		}

		fileName1 = StringUtils.cleanPath(multipartFile1.getOriginalFilename());
		fileName2 = StringUtils.cleanPath(multipartFile2.getOriginalFilename());
		campo.setImg1(fileName1);
		campo.setImg2(fileName2);

		campoValidator.validate(campo, campoBindingResult);

		if (!campoBindingResult.hasErrors()) {

			Campo savedCampo = this.campoService.inserisci(campo);

			String uploadDir = "campo-photos/" + savedCampo.getId();

			try {
				FileUploadUtil.saveFile(uploadDir, fileName1, multipartFile1);
				FileUploadUtil.saveFile(uploadDir, fileName2, multipartFile2);
			} catch (IOException e) {
				campoBindingResult.rejectValue("img1", "required");
				campoBindingResult.rejectValue("img2", "required");
				return "aggiungiCampoForm.html";
			}

			model.addAttribute("campi", this.campoService.tutti());
			session.invalidate();
			return "campi.html";
		}

		else {
			session.setAttribute("image1", fileName1);
			session.setAttribute("image2", fileName2);
			return "aggiungiCampoForm.html";
		}
	}
}
