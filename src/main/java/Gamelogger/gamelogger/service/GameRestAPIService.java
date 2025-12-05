package Gamelogger.gamelogger.service;

import Gamelogger.gamelogger.entity.Game;
import Gamelogger.gamelogger.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameRestAPIService {
    @Autowired
    GameRepository gameRepository;

    public List<Game> getGames() {return gameRepository.findAll();}
}
