package helloworld.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class TspInfo implements Serializable {

    @NotNull
    @JsonProperty("custRef")
    private String custRef;

    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("tspCreatedBy")
    private String tspCreatedBy;

    @NotNull
    @JsonProperty("tspLastUpdatedBy")
    private String tspLastUpdatedBy;

    @NotNull
    @JsonProperty("tspCreatedTime")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime tspCreatedTime;

    @NotNull
    @JsonProperty("tspLastUpdatedTime")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime tspLastUpdatedTime;
}
