package com.unitserver.unit.requestModels;

import javax.validation.constraints.Pattern;

public class Request {
    @Pattern(regexp = "\\A\\p{ASCII}*\\z", message = "not ascii")
    public String password;
}
