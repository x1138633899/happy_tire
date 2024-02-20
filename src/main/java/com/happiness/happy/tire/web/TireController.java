package com.happiness.happy.tire.web;

import com.happiness.happy.tire.entity.Response;
import com.happiness.happy.tire.entity.TireInRecord;
import com.happiness.happy.tire.entity.TireInfo;
import com.happiness.happy.tire.entity.TireOutRecord;
import com.happiness.happy.tire.server.TireInRecordService;
import com.happiness.happy.tire.server.TireInfoService;
import com.happiness.happy.tire.server.TireOutRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/tire")
public class TireController {

    @Autowired
    private TireInfoService tireInfoService;
    @Autowired
    private TireInRecordService tireInRecordService;
    @Autowired
    private TireOutRecordService tireOutRecordService;

    @PostMapping("/info/page")
    public Response page(@RequestBody(required = true) TireInfo tireInfo) {
        return tireInfoService.getPage(tireInfo, tireInfo.getPageNum(), tireInfo.getSize());
    }

    @PostMapping("/info/add")
    public Response addTireInfo(@Valid @RequestBody(required = true) TireInfo tireInfo) {
        return tireInfoService.addTireInfo(tireInfo);
    }

    @PostMapping("/info/update")
    public Response updateTireInfo(@Valid @RequestBody(required = true) TireInfo tireInfo) {
        return tireInfoService.updateTireInfo(tireInfo);
    }

    @PostMapping("/record/in")
    public Response recordIn(@Valid @RequestBody(required = true) TireInRecord tireInRecord) {
        return tireInRecordService.tireInRecord(tireInRecord);
    }

    @PostMapping("/record/out")
    public Response recordOut(@Valid @RequestBody(required = true) TireOutRecord tireOutRecord) {
        return tireOutRecordService.tireOutRecord(tireOutRecord);
    }


}
