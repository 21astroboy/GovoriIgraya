package com.example.govoriigraya.controllers;

import com.example.govoriigraya.entities.Client;
import com.example.govoriigraya.excel.ExcelWriter;
import com.example.govoriigraya.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private ExcelWriter excelWriter;
    @GetMapping(value = "/appointment")
    public ResponseEntity<byte[]> retrieveDayAppointments(@RequestParam("date") Date date) {
        List<Client> appointments = clientService.getClientsByDay(date);
        String filename = "appointments_"+date.toString();
        byte[] excelContent = excelWriter.write(appointments, filename);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header("Content-Disposition", "attachment; filename=\"" + filename + ".xlsx\"")
                .body(excelContent);
    }

    @PostMapping(value = "/appointment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> appointment(@RequestParam MultiValueMap<String, String> form) {

        clientService.saveClient(form.getFirst("name"), form.getFirst("phone"), form.getFirst("email"));
        return ResponseEntity.ok().build();
    }
}
