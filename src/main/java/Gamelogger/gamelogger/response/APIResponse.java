package Gamelogger.gamelogger.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIResponse<T> {

    private String message;

    private int statusCode;

    private T data;
}
