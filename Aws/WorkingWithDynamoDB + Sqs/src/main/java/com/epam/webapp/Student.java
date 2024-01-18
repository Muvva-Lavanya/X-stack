package com.epam.webapp;
import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.UUID;

@DynamoDbBean
@Data
public class Student {
    private UUID id;
    private String name;
    private String email;
    private String mbl;

    @DynamoDbPartitionKey
    public UUID getId() {
        return id;
    }
}
