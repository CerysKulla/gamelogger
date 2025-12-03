package Gamelogger.gamelogger.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

//JPA Entity
@Setter
@Getter
@Entity
@Table(name = "Game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Integer gameID;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "developer", length = 255)
    private String developer;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "platform", length = 100)
    private String platform;

    @Column(name = "hours_played", precision = 5, scale = 2)
    private BigDecimal hoursPlayed = BigDecimal.ZERO;

    public Game() {}

    public Game(String title) {
        this.title = title;
    }


}
