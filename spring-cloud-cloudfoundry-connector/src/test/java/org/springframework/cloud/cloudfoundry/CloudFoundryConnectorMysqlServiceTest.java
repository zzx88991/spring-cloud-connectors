package org.springframework.cloud.cloudfoundry;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.cloud.service.common.MysqlServiceInfo;

/**
 * 
 * @author Ramnivas Laddad
 *
 */
public class CloudFoundryConnectorMysqlServiceTest extends AbstractCloudFoundryConnectorRelationalServiceTest {
	@Test
	public void mysqlServiceCreation() {
		String name1 = "database-1";
		String name2 = "database-2";
		when(mockEnvironment.getEnvValue("VCAP_SERVICES"))
			.thenReturn(getServicesPayload(
							getMysqlServicePayload("mysql-1", hostname, port, username, password, name1),
							getMysqlServicePayload("mysql-2", hostname, port, username, password, name2)));
		List<ServiceInfo> serviceInfos = testCloudConnector.getServiceInfos();
		
		MysqlServiceInfo info1 = (MysqlServiceInfo) getServiceInfo(serviceInfos, "mysql-1");
		MysqlServiceInfo info2 = (MysqlServiceInfo) getServiceInfo(serviceInfos, "mysql-2");

		assertServiceFoundOfType(info1, MysqlServiceInfo.class);
		assertServiceFoundOfType(info2, MysqlServiceInfo.class);
		assertEquals(getJdbcUrl("mysql", name1), info1.getJdbcUrl());
		assertEquals(getJdbcUrl("mysql", name2), info2.getJdbcUrl());
	}

	@Test
	public void mysqlServiceCreationWithLabelNoTags() {
		String name1 = "database-1";
		String name2 = "database-2";
		when(mockEnvironment.getEnvValue("VCAP_SERVICES"))
			.thenReturn(getServicesPayload(
							getMysqlServicePayloadWithLabelNoTags("mysql-1", hostname, port, username, password, name1),
							getMysqlServicePayloadWithLabelNoTags("mysql-2", hostname, port, username, password, name2)));
		List<ServiceInfo> serviceInfos = testCloudConnector.getServiceInfos();
		
		MysqlServiceInfo info1 = (MysqlServiceInfo) getServiceInfo(serviceInfos, "mysql-1");
		MysqlServiceInfo info2 = (MysqlServiceInfo) getServiceInfo(serviceInfos, "mysql-2");
		assertServiceFoundOfType(info1, MysqlServiceInfo.class);
		assertServiceFoundOfType(info2, MysqlServiceInfo.class);
		assertEquals(getJdbcUrl("mysql", name1), info1.getJdbcUrl());
		assertEquals(getJdbcUrl("mysql", name2), info2.getJdbcUrl());
	}

	@Test
	public void mysqlServiceCreationNoLabelNoTags() {
		String name1 = "database-1";
		String name2 = "database-2";
		when(mockEnvironment.getEnvValue("VCAP_SERVICES"))
				.thenReturn(getServicesPayload(
						getMysqlServicePayloadNoLabelNoTags("mysql-1", hostname, port, username, password, name1),
						getMysqlServicePayloadNoLabelNoTags("mysql-2", hostname, port, username, password, name2)));
		List<ServiceInfo> serviceInfos = testCloudConnector.getServiceInfos();

		MysqlServiceInfo info1 = (MysqlServiceInfo) getServiceInfo(serviceInfos, "mysql-1");
		MysqlServiceInfo info2 = (MysqlServiceInfo) getServiceInfo(serviceInfos, "mysql-2");
		assertServiceFoundOfType(info1, MysqlServiceInfo.class);
		assertServiceFoundOfType(info2, MysqlServiceInfo.class);
		assertEquals(getJdbcUrl("mysql", name1), info1.getJdbcUrl());
		assertEquals(getJdbcUrl("mysql", name2), info2.getJdbcUrl());
	}

	@Test
	public void mysqlServiceCreationWithLabelNoUri() {
		String name1 = "database-1";
		String name2 = "database-2";
		when(mockEnvironment.getEnvValue("VCAP_SERVICES"))
			.thenReturn(getServicesPayload(
							getMysqlServicePayloadWithLabelNoUri("mysql-1", hostname, port, username, password, name1),
							getMysqlServicePayloadWithLabelNoUri("mysql-2", hostname, port, username, password, name2)));
		List<ServiceInfo> serviceInfos = testCloudConnector.getServiceInfos();
		
		MysqlServiceInfo info1 = (MysqlServiceInfo) getServiceInfo(serviceInfos, "mysql-1");
		MysqlServiceInfo info2 = (MysqlServiceInfo) getServiceInfo(serviceInfos, "mysql-2");
		assertServiceFoundOfType(info1, MysqlServiceInfo.class);
		assertServiceFoundOfType(info2, MysqlServiceInfo.class);
		assertEquals(getJdbcUrl("mysql", name1), info1.getJdbcUrl());
		assertEquals(getJdbcUrl("mysql", name2), info2.getJdbcUrl());
	}

	private String getMysqlServicePayload(String serviceName,
										  String hostname, int port,
										  String user, String password, String name) {
		return getRelationalPayload("test-mysql-info.json", serviceName,
				hostname, port, user, password, name);
	}

	private String getMysqlServicePayloadWithLabelNoTags(String serviceName,
														 String hostname, int port,
														 String user, String password, String name) {
		return getRelationalPayload("test-mysql-info-with-label-no-tags.json", serviceName,
				hostname, port, user, password, name);
	}

	private String getMysqlServicePayloadNoLabelNoTags(String serviceName,
													   String hostname, int port,
													   String user, String password, String name) {
		return getRelationalPayload("test-mysql-info-no-label-no-tags.json", serviceName,
				hostname, port, user, password, name);
	}

	private String getMysqlServicePayloadWithLabelNoUri(String serviceName,
														String hostname, int port,
														String user, String password, String name) {
		return getRelationalPayload("test-mysql-info-with-label-no-uri.json", serviceName,
				hostname, port, user, password, name);
	}
	
}
