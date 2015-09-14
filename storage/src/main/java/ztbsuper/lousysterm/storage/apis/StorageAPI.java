package ztbsuper.lousysterm.storage.apis;


import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ztbsuper.lousysterm.storage.apis.requests.CreateStorageRecordRequest;
import ztbsuper.lousysterm.storage.apis.responses.StorageRecordResponse;
import ztbsuper.lousysterm.storage.services.StorageRecordService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Component
@Path("storage_record")
@Api(tags = {"storage record", "done"})
@Produces({"application/json"})
@Consumes({"application/json"})
public class StorageAPI {

    @Autowired
    StorageRecordService storageRecordService;

    @POST
    @ApiOperation(value = "创建一条称重记录",
            response = StorageRecordResponse.class,
            responseContainer = "List")
    public Response createRecord(@ApiParam(value = "创建一条记录", required = true) CreateStorageRecordRequest request) {
        storageRecordService.save(request);
        return Response.status(201).build();
    }

    @GET
    @ApiOperation(value = "获取所有的记录")
    @Path("list")
    public Response queryAll() {
        return Response.ok().entity(storageRecordService.queryAll()).build();
    }

    @GET
    @ApiOperation(value = "下载excel记录")
    @Path("export.excel")
    @Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public Response exportExcel() throws IOException {
        byte[] data = storageRecordService.exportExcel();
        return Response.ok(data).header("Content-Disposition", "attachment; filename=export.xls").build();
    }


}
