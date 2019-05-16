package com.exception;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.NoSuchElementException;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class NormalException extends NoSuchElementException {
    public NormalException(String message) {
        super(message);// 把参数传递给Throwable的带String参数的构造方法
    }

}
