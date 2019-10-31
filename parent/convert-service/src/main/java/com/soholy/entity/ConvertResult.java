package com.soholy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConvertResult {

    private Double longitude;

    private Double latitude;

    private Integer make;
}
