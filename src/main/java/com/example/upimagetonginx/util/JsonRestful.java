package com.example.upimagetonginx.util;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonRestful {


    private int code;
    private String msg;
    private Object object;

}
