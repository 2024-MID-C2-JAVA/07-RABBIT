package com.bank.management;


import com.bank.management.data.AuthRequestDTO;
import com.bank.management.data.RegisterRequestDTO;
import com.bank.management.data.RequestMs;
import com.bank.management.data.ResponseMs;
import com.bank.management.enums.DinErrorCode;
import com.bank.management.gateway.CustomerRepository;
import com.bank.management.usecase.appservice.CreateUserUseCase;
import com.bank.management.usecase.appservice.UserEventCreatedUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth/v1")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CreateUserUseCase useCase;
    private final PasswordEncoder passwordEncoder;
    private final UserEventCreatedUseCase userEventCreateUseCase;
    private final CustomerRepository customerRepository;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, CreateUserUseCase useCase, PasswordEncoder passwordEncoder, UserEventCreatedUseCase userEventCreateUseCase, CustomerRepository customerRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.useCase = useCase;
        this.passwordEncoder = passwordEncoder;
        this.userEventCreateUseCase = userEventCreateUseCase;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseMs<Map<String, String>>> login(@RequestBody @Valid RequestMs<AuthRequestDTO> authRequest) {
        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getDinBody().getUsername(), authRequest.getDinBody().getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtil.generateToken(userDetails);

            Optional<Customer> customerFound = customerRepository.findByUsername(userDetails.getUsername());
            String customerId = "";
            if (customerFound.isPresent()) {
                customerId = customerFound.get().getId();
            }


            Map<String, String> responseData = new HashMap<>();
            responseData.put("token", token);
            responseData.put("customerId", customerId);

            return ResponseBuilder.buildResponse(
                    authRequest.getDinHeader(),
                    responseData,
                    DinErrorCode.SUCCESS,
                    HttpStatus.OK,
                    "Authentication successful."
            );
        } catch (AuthenticationException e) {
            return ResponseBuilder.buildResponse(
                    authRequest.getDinHeader(),
                    null,
                    DinErrorCode.BAD_CREDENTIALS,
                    HttpStatus.UNAUTHORIZED,
                    "Authentication failed. Invalid username or password."
            );
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseMs<Map<String, String>>> register(@RequestBody @Valid RequestMs<RegisterRequestDTO> registerRequest) {
        try {

            User user = new User(
                    registerRequest.getDinBody().getUsername(),
                    passwordEncoder.encode(registerRequest.getDinBody().getPassword()),
                    registerRequest.getDinBody().getRoles());

            User userCreated = useCase.apply(user);

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userCreated.getUsername(),
                            registerRequest.getDinBody().getPassword(),
                            registerRequest.getDinBody().getRoles().stream()
                                    .map(SimpleGrantedAuthority::new)
                                    .collect(Collectors.toList())
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtil.generateToken(userDetails);
            CompletableFuture<String> customerIdFuture = new CompletableFuture<>();

            userEventCreateUseCase.apply(new UserEventCreate.Builder()
                    .name(registerRequest.getDinBody().getName())
                    .lastname(registerRequest.getDinBody().getLastname())
                    .username(registerRequest.getDinBody().getUsername())
                    .customerIdFuture(customerIdFuture)
                    .build());

            String customerId = customerIdFuture.join();

            Map<String, String> responseData = new HashMap<>();
            responseData.put("token", token);
            responseData.put("customerId", customerId);



            return ResponseBuilder.buildResponse(
                    registerRequest.getDinHeader(),
                    responseData,
                    DinErrorCode.SUCCESS,
                    HttpStatus.CREATED,
                    "User created and authenticated successfully."
            );
        } catch (IllegalArgumentException e) {
            return ResponseBuilder.buildResponse(
                    registerRequest.getDinHeader(),
                    null,
                    DinErrorCode.BAD_REQUEST,
                    HttpStatus.BAD_REQUEST,
                    "Invalid input data."
            );
        }
    }

}
