package Gamelogger.gamelogger.controller;

import Gamelogger.gamelogger.entity.Game;
import Gamelogger.gamelogger.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/game")
public class GameController {

    /*Subject to change im not sure kasi how restapi works*/

    @Autowired
    GameService gameService;

    @GetMapping("/list")
    public List<Game> getGames(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String developer) {
        if (title != null) {
            return gameService.getGamesByTitle(title);
        } else if (developer != null) {
            return gameService.getGameFromDeveloper(developer);
        } else {
            return gameService.getGames();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Game> addGame(@RequestBody Game game){
        Game createdGame = gameService.addGame(game);
        return new ResponseEntity<>(createdGame, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Game> updateGame(@RequestBody Game game){
        Game resultGame = gameService.updateGame(game);
        if (resultGame != null) {
            return new ResponseEntity<>(resultGame, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable Integer gameID) {
        gameService.deleteGame(gameID);
        return new ResponseEntity<>("Game deleted successfully", HttpStatus.OK);
    }



}
