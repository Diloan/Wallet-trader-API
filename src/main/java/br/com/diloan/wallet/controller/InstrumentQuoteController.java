package br.com.diloan.wallet.controller;

import br.com.diloan.wallet.controller.dto.InstrumentQuoteDTO;
import br.com.diloan.wallet.controller.service.InstrumentQuoteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/instruments")
public class InstrumentQuoteController {

    @Autowired
    private InstrumentQuoteServices instrumentQuoteServices;

    @GetMapping
    public ResponseEntity<List<InstrumentQuoteDTO>> listInstruments(@RequestParam(value = "data1") String data1, @RequestParam(value = "data2") String data2) {
        return ResponseEntity.ok(instrumentQuoteServices.listInstruments(LocalDate.parse(data1), LocalDate.parse(data2)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstrumentQuoteDTO> getInstrumentDetails(@PathVariable Long id) {
        return ResponseEntity.ok(instrumentQuoteServices.getInstrumentDetails(id));
    }

}
