package ztbsuper.lousysterm.storage.services;

import org.springframework.stereotype.Component;
import ztbsuper.lousysterm.base.BaseMapper;
import ztbsuper.lousysterm.storage.apis.requests.CreateStorageRecordRequest;
import ztbsuper.lousysterm.storage.apis.responses.StorageRecordResponse;
import ztbsuper.lousysterm.storage.models.StorageRecordModel;

@Component
public class StorageRecordMapper extends BaseMapper {
    public StorageRecordMapper() {
        register(StorageRecordModel.class, StorageRecordResponse.class);
        register(StorageRecordModel.class, CreateStorageRecordRequest.class);
    }
}
