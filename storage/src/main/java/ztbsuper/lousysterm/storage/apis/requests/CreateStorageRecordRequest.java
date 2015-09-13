package ztbsuper.lousysterm.storage.apis.requests;

public class CreateStorageRecordRequest {
    private String itemCode;
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
