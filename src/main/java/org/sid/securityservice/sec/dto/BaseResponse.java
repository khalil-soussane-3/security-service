package org.sid.securityservice.sec.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseResponse {
    Boolean success;
    String error;

    public static BaseResponse success() {
        return new BaseResponse(true, null);
    }
}
