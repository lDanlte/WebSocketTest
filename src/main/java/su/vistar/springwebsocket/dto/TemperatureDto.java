package su.vistar.springwebsocket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author dantonov
 */
public class TemperatureDto {
    
    @JsonProperty("areaId")
    private long areaId;
    
    @JsonProperty("datetime")
    private String datetime;
    
    @JsonProperty("value")
    private float value;

    public TemperatureDto() {}

    public TemperatureDto(long areaId, String datetime, float value) {
        this.areaId = areaId;
        this.datetime = datetime;
        this.value = value;
    }

    public long getAreaId() { return areaId; }
    public void setAreaId(long areaId) { this.areaId = areaId; }

    public String getDatetime() { return datetime; }
    public void setDatetime(String datetime) { this.datetime = datetime; }

    public float getValue() { return value; }
    public void setValue(float value) { this.value = value; }
    
}
