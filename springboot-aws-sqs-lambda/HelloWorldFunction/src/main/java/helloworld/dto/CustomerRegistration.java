package helloworld.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CustomerRegistration implements Serializable {
    @JsonProperty("cId")
    @NotNull
    private String cId;

    @NotNull
    @JsonProperty("accountName")
    private String accountName;

    @NotNull
    @JsonProperty("PrimaryMarketSegment")
    private String primaryMarketSegment;

    @JsonProperty("addressLine1")
    private String addressLine1;

    @JsonProperty("addressLine2")
    private String addressLine2;

    @JsonProperty("city")
    private String city;

    @JsonProperty("stateName")
    private String stateName;

    @JsonProperty("countryName")
    private String countryName;

    @JsonProperty("zipcode")
    private String zipCode;

    @NotNull
    @JsonProperty("createdBy")
    private String createdBy;

    @NotNull
    @JsonProperty("lastUpdatedBy")
    private String lastUpdatedBy;

    @NotNull
    @JsonProperty("createdTime")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;

    @NotNull
    @JsonProperty("lastUpdatedTime")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdatedTime;

    @JsonProperty("TspInfo")
    private List<@Valid TspInfo> tspInfo;
}
