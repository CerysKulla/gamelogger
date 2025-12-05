package Gamelogger.gamelogger.controller;

import Gamelogger.gamelogger.entity.Game;
import Gamelogger.gamelogger.repository.GameRepository;
import Gamelogger.gamelogger.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class GameController {

    @Autowired
    GameService gameService;

    @GetMapping("/list")
    public String getGames(@RequestParam(value = "keyword", required = false) String keyword, Model model) {

        List<Game> gameList;

        if (keyword != null && !keyword.isEmpty()) {
            gameList= gameService.getGamesByTitle(keyword);
        } else {
            gameList= gameService.getGames();
        }

        model.addAttribute("keyword", keyword);
        model.addAttribute("games", gameList);
        return "list";
    }

//    Add
    @GetMapping("/add")
    public String addGame(Model model) {
        model.addAttribute("game", new Game());
        return "add";
    }

    @PostMapping("/add")
    public String addGame(@ModelAttribute Game game) {
        gameService.addGame(game);
        return "redirect:/list";
    }

//    Delete
    @GetMapping("/delete/{id}")
    public String deleteGame(@PathVariable Integer id) {
        gameService.deleteGame(id);
        return "redirect:/list";
    }

//    Edit
    @GetMapping("/edit/{id}")
    public String editGame(@PathVariable Integer id, Model model) {
        Optional<Game> optinalGame =  gameService.getGameById(id);

        if (optinalGame.isPresent()) {
            model.addAttribute("game", optinalGame.get());
            return "edit";
        }
        return "redirect:/list";
    }

    @PostMapping("/update")
    public String updateGame(@ModelAttribute Game game) {
        game.setGameID(game.getGameID());
        gameService.updateGame(game);
        return "redirect:/list";
    }

}
