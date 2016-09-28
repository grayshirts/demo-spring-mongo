package ar.com.grayshirts.api.type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;


public abstract class BaseModel {

    @Id @JsonIgnore
    private ObjectId id;

    @JsonProperty("id")
    public String getIdAsStr() {
        return id!=null ? id.toString() : null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==null) return false;
        return getId().equals(((BaseModel)obj).getId());
    }

    /* Getters and Setters */

    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }
}
