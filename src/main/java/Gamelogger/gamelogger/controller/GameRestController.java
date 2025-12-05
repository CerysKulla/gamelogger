package Gamelogger.gamelogger.controller;

import Gamelogger.gamelogger.entity.Game;
import Gamelogger.gamelogger.response.APIResponse;
import Gamelogger.gamelogger.service.GameRestAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/game")
public class GameRestController {

    @Autowired
    GameRestAPIService gameRestAPIService;

    @GetMapping("/list")
    public ResponseEntity<?> getGameList() {
        APIResponse<List<Game>> api = new APIResponse<>("Game List", 200, gameRestAPIService.getGames());
        return ResponseEntity.ok(api);
    }


}
