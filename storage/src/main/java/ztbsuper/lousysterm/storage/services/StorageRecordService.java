package ztbsuper.lousysterm.storage.services;

import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ztbsuper.lousysterm.storage.DAOs.StorageDAO;
import ztbsuper.lousysterm.storage.apis.requests.CreateStorageRecordRequest;
import ztbsuper.lousysterm.storage.apis.responses.StorageRecordResponse;
import ztbsuper.lousysterm.storage.models.StorageRecordModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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

    public byte[] exportExcel() throws IOException {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("记录表");

        List<String> headers = Lists.newArrayList("编号", "编号", "重量(KG)", "日期");

        writeExcelHeader(headers, sheet);

        List<StorageRecordResponse> storageRecordResponses = queryAll();
        int rowNumber = 1;
        for (StorageRecordResponse storageRecordResponse : storageRecordResponses) {
            ArrayList<String> list = Lists.newArrayList(storageRecordResponse.getId().toString(),
                    storageRecordResponse.getItemCode(),
                    storageRecordResponse.getWeight(),
                    storageRecordResponse.getTimeCreated().toString());
            writeRow(list, sheet, rowNumber);
            rowNumber++;
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();


        wb.write(byteArrayOutputStream);
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    private void writeExcelHeader(List<String> list, Sheet sheet) {
        writeRow(list, sheet, 0);
    }

    private void writeRow(List<String> list, Sheet sheet, int rowNumber) {
        Row row = sheet.createRow(rowNumber);
        int cellNumber = 0;
        for (Object cellValue : list) {
            row.createCell(cellNumber).setCellValue(cellValue.toString());
            cellNumber++;
        }
    }

}
