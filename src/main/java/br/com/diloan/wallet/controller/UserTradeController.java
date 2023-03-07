package br.com.diloan.wallet.controller;

import br.com.diloan.wallet.controller.dto.UserTradeDTO;
import br.com.diloan.wallet.controller.service.UserTradeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/qoutes")
public class UserTradeController {

    @Autowired
    private UserTradeServices userTradeServices;

    @GetMapping
    public ResponseEntity<List<UserTradeDTO>> listQuotes(@RequestParam(value = "data1") String data1, @RequestParam(value = "data2") String data2) {
        return ResponseEntity.ok(userTradeServices.listQuotes(LocalDate.parse(data1), LocalDate.parse(data2)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserTradeDTO> getQuotes(@PathVariable Long id) {
        return ResponseEntity.ok(userTradeServices.getQuotes(id));
    }


}
