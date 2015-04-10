package grsu.project.report;

import grsu.project.data.TimestampConfiguration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportInputParameters {

	private Date startDate;
	private Date endDate;
	private List<Integer> params = new ArrayList<Integer>();
	private TimestampConfiguration timestampConfiguration = new TimestampConfiguration();

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Integer> getParams() {
		return params;
	}

	public void setParams(List<Integer> params) {
		this.params = params;
	}

	public TimestampConfiguration getTimestampConfiguration() {
		return timestampConfiguration;
	}

	public void setTimestampConfiguration(String timestampConfiguration) {
		this.timestampConfiguration.setTimestampFormat(timestampConfiguration);
	}

	@Override
	public String toString() {
		if (checkReportInputParameters()) {
			StringBuilder toString = new StringBuilder();
			if (startDate != null) {
				toString.append(timestampConfiguration.getTimestampFormat()
						.format(startDate) + " ");
			} else {
				toString.append("From scratch ");
			}
			if (endDate != null) {
				toString.append(timestampConfiguration.getTimestampFormat()
						.format(endDate) + " ");
			} else {
				toString.append("To end ");
			}
			for (int x : params) {
				toString.append(x + " ");
			}
			return toString.toString();
		}
		return "Error parameters";
	}

	private Boolean checkReportInputParameters() {
		if (params.size() > 0) {
			for (int x : params) {
				if (x < 1 && x > 3) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

}
