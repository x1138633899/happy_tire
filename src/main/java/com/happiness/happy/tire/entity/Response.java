package com.happiness.happy.tire.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    private int code;
    private String msg;
    private Object object;
}
