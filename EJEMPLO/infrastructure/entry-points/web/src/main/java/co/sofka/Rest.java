package co.sofka;

import co.sofka.data.GetAccountDTO;
import co.sofka.data.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
public class Rest {

    private final Handler handler;

    public Rest(Handler handler) {
        this.handler = handler;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> getAccountById(@RequestBody GetAccountDTO dto) {

        ResponseDTO responseDTO = handler.getAccountById(dto);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
