package helloworld.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CustomerRegistration {
    @JsonProperty("cId")
    private String cId;

    @JsonProperty("addressLine1")
    private String addressLine1;

    @JsonProperty("city")
    private String city;

    @JsonProperty("stateName")
    private String state;

    @JsonProperty("zipcode")
    private String zipCode;

    @JsonProperty("createdBy")
    private String createdBy;

    @JsonProperty("lastUpdatedBy")
    private String lastUpdatedBy;

    @JsonProperty("createdTime")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;

    @JsonProperty("lastUpdatedTime")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdatedTime;

    @JsonProperty("TspInfo")
    private List<TspInfo> tspInfo;

}
