package com.aqa.model;


import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor()
@Builder
public class Category {
    public int id;
    public String name;
}
