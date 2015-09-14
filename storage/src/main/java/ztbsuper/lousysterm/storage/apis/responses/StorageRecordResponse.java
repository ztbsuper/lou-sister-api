package ztbsuper.lousysterm.storage.apis.responses;

import java.util.Date;

public class StorageRecordResponse {
    private Long id;
    private String itemCode;
    private String weight;
    private Date timeCreated;

    public String getItemCode() {
        return itemCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }
}
