package ztbsuper.lousysterm.storage.models;

import ztbsuper.lousysterm.base.models.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "storage_record")
public class StorageRecordModel extends BaseModel {

    @Column(name = "item_code")
    private String itemCode;

    @Column(name = "weight")
    private String weight;

    public String getItemCode() {
        return itemCode;
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
}
