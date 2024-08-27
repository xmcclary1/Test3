package com.example.talent_api.domain;

import java.util.ArrayList;
// import java.util.Dictionary;
// import java.util.Hashtable;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
	private String id = new ObjectId().toString();

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private long phone;
	private String password;
	private String role;
    // private String resumeId;    // Combination username + applicationID
    
    // private Dictionary<String, String> jobListingIds = new Hashtable<>();
    private List<String> jobListingIds = new ArrayList<String>();
    // private String bucketName;
}
