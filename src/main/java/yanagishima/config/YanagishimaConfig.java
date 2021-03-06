package yanagishima.config;

import java.util.*;

public class YanagishimaConfig {

	private Properties properties;

	public YanagishimaConfig(Properties properties) {
		this.properties = properties;
	}

	public String getPrestoCoordinatorServer(String datasource) {
		return Optional.ofNullable(properties.getProperty("presto.coordinator.server." + datasource)).get();
	}

	public String getPrestoRedirectServer(String datasource) {
		return Optional.ofNullable(properties.getProperty("presto.redirect.server." + datasource)).get();
	}

	public String getCatalog(String datasource) {
		return Optional.ofNullable(properties.getProperty("catalog." + datasource)).get();
	}

	public String getSchema(String datasource) {
		return Optional.ofNullable(properties.getProperty("schema." + datasource)).get();
	}
	
	public String getUser() {
		return "yanagishima";
	}
	
	public String getSource() {
		return "yanagishima";
	}

	public int getSelectLimit() {
		return Integer.parseInt(Optional.ofNullable(properties.getProperty("select.limit")).get());
	}

	public String getAuditHttpHeaderName() {
		return properties.getProperty("audit.http.header.name");
	}

	public List<String> getDatasources() {
		return Arrays.asList(Optional.ofNullable(properties.getProperty("presto.datasources")).get().split(","));
	}

	public double getQueryMaxRunTimeSeconds() {
		return Double.parseDouble(Optional.ofNullable(properties.getProperty("presto.query.max-run-time-seconds")).get());
	}

	public double getQueryMaxRunTimeSeconds(String datasource) {
		String property = properties.getProperty("presto.query.max-run-time-seconds" + "." + datasource);
		if(property == null) {
			return getQueryMaxRunTimeSeconds();
		} else {
			return Double.parseDouble(property);
		}
	}

	public int getMaxResultFileByteSize() {
		return Integer.parseInt(Optional.ofNullable(properties.getProperty("presto.max-result-file-byte-size")).get());
	}

	public int getToValuesQueryLimit() {
		return Integer.parseInt(Optional.ofNullable(properties.getProperty("to.values.query.limit")).get());
	}

	public boolean isCheckDatasource() {
		return Boolean.parseBoolean(Optional.ofNullable(properties.getProperty("check.datasource")).orElse("false"));
	}

	public List<String> getInvisibleSchemas(String datasource, String catalog) {
		String invisibleSchemas = properties.getProperty("invisible.schema." + datasource + "." + catalog);
		if(invisibleSchemas == null) {
			return Collections.emptyList();
		} else {
			return Arrays.asList(invisibleSchemas.split(","));
		}
	}

	public Optional<String> getFluentdExecutedTag() {
		return Optional.ofNullable(properties.getProperty("fluentd.executed.tag"));
	}

	public Optional<String> getFluentdFaliedTag() {
		return Optional.ofNullable(properties.getProperty("fluentd.failed.tag"));
	}

	public Optional<String> getFluentdHost() {
		return Optional.ofNullable(properties.getProperty("fluentd.host"));
	}

	public Optional<String> getFluentdPort() {
		return Optional.ofNullable(properties.getProperty("fluentd.port"));
	}

	public boolean isUserRequired() {
		return Boolean.parseBoolean(Optional.ofNullable(properties.getProperty("user.require")).orElse("false"));
	}
}
