package com.gdou.common.exception;

import com.gdou.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LhException extends RuntimeException {

    private ExceptionEnum exceptionEnum;


}
