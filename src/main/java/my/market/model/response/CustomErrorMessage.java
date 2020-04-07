package my.market.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class CustomErrorMessage {
    private final String message;
    private final Date date;


}
