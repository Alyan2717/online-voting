package com.web_app.online_voting.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "district")
public class district {
    @Id
    public String id;
    public String districtCode;
    public String districtName;
}
