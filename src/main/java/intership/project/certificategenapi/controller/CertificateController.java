package intership.project.certificategenapi.controller;


import intership.project.certificategenapi.model.CertificateDTO;
import intership.project.certificategenapi.service.CertificateService;
import intership.project.certificategenapi.service.TemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certificates")
public class CertificateController  {

    CertificateService certificateService;
    TemplateService templateService;

    CertificateController(CertificateService certificateService, TemplateService templateService) {
        this.certificateService = certificateService;
        this.templateService = templateService;
    }

    @PostMapping("/generate")
    public ResponseEntity<byte[]>generateCertificate(@RequestBody CertificateDTO certificateDTO) {

        try{
            return this.certificateService.generateCertificate(certificateDTO);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/templates")
    public ResponseEntity<?> listTemplates(){
        try{
            return this.templateService.listTemplates();
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
