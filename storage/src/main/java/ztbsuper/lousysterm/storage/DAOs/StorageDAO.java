package ztbsuper.lousysterm.storage.DAOs;

import org.springframework.stereotype.Component;
import ztbsuper.lousysterm.storage.domains.StorageQueryCriteria;
import ztbsuper.lousysterm.storage.models.StorageRecordModel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class StorageDAO {

    @PersistenceContext
    EntityManager entityManager;

    public void save(StorageRecordModel storageRecordModel) {
        entityManager.persist(storageRecordModel);
    }

    public List<StorageRecordModel> query(StorageQueryCriteria criteria) {
        return null;
    }

    public List<StorageRecordModel> queryAll() {
        String hql = "from StorageRecordModel";
        return entityManager.createQuery(hql).getResultList();
    }

}
