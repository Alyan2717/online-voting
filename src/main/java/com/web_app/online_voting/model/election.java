package com.web_app.online_voting.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "election")
public class election {
    @Id
    public String id;
    public String electionName;
    public String electionDescription;
    public String electionStartDate;
    public String electionEndDate;
}
