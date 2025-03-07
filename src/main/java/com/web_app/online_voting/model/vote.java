package com.web_app.online_voting.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vote")
public class vote {
    @Id
    public String id;
    public String voterId;
    public String candidateId;
    public String electionId;
    public String districtId;
}
