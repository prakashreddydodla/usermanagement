package com.otsi.retail.authservice.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.otsi.retail.authservice.requestModel.ReportVo;

@Component
public interface ReportsService {

	List<ReportVo> getUsersByRole(Long clientId);

	List<ReportVo> getActiveUsers(Long clientId);

	List<ReportVo> StoresVsEmployees(Long clientId);

	String SaveColorCodes(List<String> colorCodes);

}
