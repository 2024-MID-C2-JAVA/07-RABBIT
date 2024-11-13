package co.sofka.command.dto.request;

import co.sofka.command.dto.DinHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestMs<T> {

    private DinHeader dinHeader;
    private T dinBody;
}
