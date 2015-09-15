package ztbsuper.lousysterm.storage.apis.responses;

public class CreateStorageRecordResponse {
    private Long itemID;
    private String msg;

    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long id) {
        this.itemID = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
