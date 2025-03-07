package com.web_app.online_voting.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "party")
public class party {
    @Id
    public String id;
    public String partyName;
    public String partyEmail;
    public String partyDescription;

    public String electionId;
}
