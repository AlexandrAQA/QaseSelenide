package com.aqa.model;

import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor()
@Builder
public class Tag {
    public int id;
    public String name;
}
