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

}
