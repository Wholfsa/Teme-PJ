package com.example.lab10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CarteWebController {
    private final CarteRepository carteRepository;

    @Autowired
    public CarteWebController(CarteRepository carteRepository) {
        this.carteRepository = carteRepository;
    }

    @GetMapping("/lista-carti")
    public String index(Model model) {
        model.addAttribute("mesaj", "Lista cartilor preluate din repository");
        model.addAttribute("carti", carteRepository.findAll());
        return "carti";
    }

    @PostMapping("/adauga")
    public String adaugaCarte(@ModelAttribute Carte carte, Model model) {
        if (carte.getIsbn().isEmpty() || carte.getTitlu().isEmpty() || carte.getAutor().isEmpty()) {
            model.addAttribute("mesaj", "Adaugarea nu se realizează dacă nu completaţi toate caracteristicile!");
        } else {
            carteRepository.save(carte);
            model.addAttribute("mesaj", "Adaugare realizata cu succes!");
        }
        model.addAttribute("carti", carteRepository.findAll());
        return "carti";
    }

    @PostMapping("/modifica")
    public String modificaCarte(@ModelAttribute Carte carte, Model model) {
        if (carteRepository.existsById(carte.getIsbn())) {
            carteRepository.save(carte);//todo
            model.addAttribute("mesaj", "Cartea cu ISBN-ul " +
                    carte.getIsbn() + " a fost modificata!");
        } else {
            model.addAttribute("mesaj", "Nu se gaseste nici o carte cu isbn-ul introdus.");
        }
        model.addAttribute("carti", carteRepository.findAll());
        return "carti";
    }

    @PostMapping("/sterge")
    public String stergeCarte(@RequestParam String isbn, Model model) {
        if (carteRepository.existsById(isbn)) {
            carteRepository.deleteById(isbn);
            model.addAttribute("mesaj", "Cartea cu ISBN-ul " + isbn + " a fost stearsa!");
        } else {
            model.addAttribute("mesaj", "Nu se gaseste nici o carte cu isbn-ul introdus.");
        }
        model.addAttribute("carti", carteRepository.findAll());
        return "carti";
    }

    @PostMapping("/filtreaza")
    public String filtreazaCarti(@RequestParam(required = false) String autor, Model model) {
        List<Carte> carti;
        if (autor != null && !autor.isEmpty()) {
            carti = carteRepository.findByAutor(autor);
            model.addAttribute("mesaj", "Cărțile următoare aparțin autorului " + autor + ".");
        } else {
            carti = carteRepository.findAll();
            model.addAttribute("mesaj", "Toate cărțile sunt afișate.");
        }
        model.addAttribute("carti", carti);
        return "carti";
    }
}