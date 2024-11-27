package com.example.LifeLongLearningTool.Dto;

import java.util.UUID;

public class UserDTO {
    private String uuid;
    private String email;
    private String name;

    public UUID getUuid()
    {
        return UUID.fromString(this.uuid);
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }


}
