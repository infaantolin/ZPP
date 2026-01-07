package com.arik.zereginak.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arik.zereginak.model.Zeregina;

@RestController
@RequestMapping("/zereginak")
public class ZereginakController {

	private  final ArrayList<Zeregina> zereginLista = new ArrayList<>();
    private  int kontagailua = 0;
    
    // Eraikitzailea, hasierako zereginekin
    public ZereginakController() {
    	zereginLista.add(new Zeregina(1, "Estudiar Spring Boot", false));
    	zereginLista.add(new Zeregina(2, "Hacer la compra", true));
    	kontagailua = 3;
    }

    // Lortu zeregin guztiak
    @GetMapping
    public List<Zeregina> getZeregina() {
        return zereginLista;
    }

    // Zeregin berria gehitu
    @PostMapping
    public Zeregina addZeregina(@RequestBody Zeregina berria) {
    	berria.setId(kontagailua);
    	kontagailua++;
    	zereginLista.add(berria);
        return berria;
    }

    // Zeregin bat ezabatu ID bidez
    @DeleteMapping("/{id}")
    public String deleteZeregina(@PathVariable int id) {
        boolean removed = zereginLista.removeIf(zeregina -> zeregina.getId() == id);
        return removed ? "Zeregina ezabatua" : "Zeregina ez da aurkitu";
    }
    
    @PutMapping("/{id}")
    public String zereginaOsatu(@PathVariable int id) {
        for (Zeregina zeregina : zereginLista) {
            if (zeregina.getId() == id) {
            	zeregina.setOsatua(true);
                return "Zeregina osatua bezala markatu da";
            }
        }
        return "Zeregina ez da aurkitu";
    }
}
