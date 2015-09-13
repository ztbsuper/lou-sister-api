package ztbsuper.lousysterm.storage.services;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ztbsuper.lousysterm.storage.DAOs.StorageDAO;
import ztbsuper.lousysterm.storage.apis.requests.CreateStorageRecordRequest;
import ztbsuper.lousysterm.storage.apis.responses.StorageRecordResponse;
import ztbsuper.lousysterm.storage.models.StorageRecordModel;

import java.util.List;

@Component
public class StorageRecordService {

    @Autowired
    StorageDAO storageDAO;

    @Autowired
    StorageRecordMapper storageRecordMapper;

    @Transactional
    public void save(CreateStorageRecordRequest request) {
        StorageRecordModel storageRecordModel = storageRecordMapper.map(request, StorageRecordModel.class);
        storageDAO.save(storageRecordModel);

    }

    public List<StorageRecordResponse> queryAll() {
        List<StorageRecordModel> storageRecordModels = storageDAO.queryAll();
        List<StorageRecordResponse> result = Lists.newArrayList();
        for (StorageRecordModel storageRecordModel : storageRecordModels) {
            result.add(storageRecordMapper.map(storageRecordModel, StorageRecordResponse.class));
        }
        return result;
    }

}
