package com.app.ecom.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data

public class UserRequest {
    @NotNull
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private AddressDTO address;
}
