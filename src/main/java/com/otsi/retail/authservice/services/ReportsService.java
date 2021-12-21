package com.otsi.retail.authservice.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.otsi.retail.authservice.requestModel.ColorCodeVo;
import com.otsi.retail.authservice.requestModel.ReportVo;

@Component
public interface ReportsService {

	List<ReportVo> getUsersByRole(Long clientId) throws Exception;

	List<ReportVo> getActiveUsers(Long clientId) throws Exception;

	List<ReportVo> StoresVsEmployees(Long clientId) throws Exception;

	String SaveColorCodes(List<ColorCodeVo> colorCodes);

}
