package co.sofka.command.dto.response;

import co.sofka.command.dto.DinHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseMs<T> {
    private DinHeader dinHeader;
    private T dinBody;
    private DinError dinError;
}
