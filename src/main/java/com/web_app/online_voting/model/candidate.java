package com.web_app.online_voting.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "candidate")
public class candidate {
    @Id
    public String id;
    public String candidateName;
    public String candidateAddress;
    @Indexed(unique = true)
    public String candidatePhone;
    @Indexed(unique = true)
    public String candidateEmail;

    public String districtId;
    public String partyId;
}
