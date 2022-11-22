package com.labcomu.edu.resource;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
public class Organization {
    private String name;
    private String url;
    private Collection<Researcher> researchers;

}
