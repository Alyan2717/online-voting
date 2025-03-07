package com.web_app.online_voting.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "voter")
public class voter {
    @Id
    public String id;
    public String voterName;
    @Indexed(unique = true)
    public String voterNic;
    @Indexed(unique = true)
    public String voterEmail;
    public String voterPassword;
    @Indexed(unique = true)
    public String voterPhone;
    public String voterAddress;
    public boolean voterHasVoted;

    public String districtId;
}
