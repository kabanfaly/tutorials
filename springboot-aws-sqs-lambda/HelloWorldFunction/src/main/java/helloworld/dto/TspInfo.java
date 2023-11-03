package helloworld.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TspInfo {

    @JsonProperty("custRef")
    private String custRef;

    @JsonProperty("name")
    private String name;

    @JsonProperty("tspCreatedBy")
    private String tspCreatedBy;

    @JsonProperty("tspLastUpdatedBy")
    private String tspLastUpdatedBy;

    @JsonProperty("tspCreatedTime")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime tspCreatedTime;

    @JsonProperty("tspLastUpdatedTime")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime tspLastUpdatedTime;
}
