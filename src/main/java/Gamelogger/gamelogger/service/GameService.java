package Gamelogger.gamelogger.service;

import Gamelogger.gamelogger.entity.Game;
import Gamelogger.gamelogger.repository.GameRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    //Get all games
    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    //Get Game by ID
    public Optional<Game> getGameById(Integer id) {
        return gameRepository.findById(id);
    }

    //Get Game by Title
    public List<Game> getGamesByTitle(String searchText) {
        return gameRepository.findAll().stream()
                .filter(game -> game.getTitle().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    //Get Game from Developer
    public List<Game> getGameFromDeveloper(String developerName) {
        return gameRepository.findAll().stream()
                .filter(game -> developerName.equals(game.getDeveloper()))
                .collect(Collectors.toList());
    }

    //Get Gate by Platform
    public List<Game> getGamesFromPlatform(String platformName) {
        return gameRepository.findAll().stream()
                .filter(game -> platformName.equals(game.getPlatform()))
                .collect(Collectors.toList());
    }

    //Add New Game
    public Game addGame(Game game) {
        gameRepository.save(game);
        return game;
    }

    //Update Game Information
    public Game updateGame(Game updatedGame){
        Optional<Game> existingGame = gameRepository.findById(updatedGame.getGameID());

        if(existingGame.isPresent()){
            Game gameToUpdate = existingGame.get();
            gameToUpdate.setTitle(updatedGame.getTitle());
            gameToUpdate.setReleaseDate(updatedGame.getReleaseDate());
            gameToUpdate.setDeveloper(updatedGame.getDeveloper());
            gameToUpdate.setDescription(updatedGame.getDescription());
            gameToUpdate.setPlatform(updatedGame.getPlatform());
            gameToUpdate.setHoursPlayed(updatedGame.getHoursPlayed());

            gameRepository.save(gameToUpdate);
            return gameToUpdate;
        }
        return null;
    }

    //Delete fame
    public void deleteGame(Integer gameId) {
        gameRepository.deleteById(gameId);
    }

    // Update hours played
    public Game updateHoursPlayed(Integer gameId, BigDecimal hours) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("[WARNING!] Game not found with ID: " + gameId));

        game.setHoursPlayed(hours);
        return gameRepository.save(game);
    }

    // Get overall hours played
    public BigDecimal getTotalHoursPlayed() {
        List<Game> games = gameRepository.findAll();
        return games.stream()
                .map(Game::getHoursPlayed)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Count total games
    public long getTotalGamesCount() {
        return gameRepository.count();
    }







}
