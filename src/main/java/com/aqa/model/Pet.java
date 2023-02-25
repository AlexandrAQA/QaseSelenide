package com.aqa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import static lombok.AccessLevel.PRIVATE;

@Builder
@Data
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor()
public class Pet {
    public long id;
    public Category category;
    public String name;
    public List<String> photoUrls;
    public List<Tag> tags;
    public Status status;
}
