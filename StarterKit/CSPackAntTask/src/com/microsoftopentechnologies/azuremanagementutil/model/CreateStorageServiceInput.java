/**
* Copyright Microsoft Corp.
*
* Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*	 http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package com.microsoftopentechnologies.azuremanagementutil.model;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.microsoftopentechnologies.azuremanagementutil.exception.InvalidRestAPIArgument;
import com.microsoftopentechnologies.azuremanagementutil.util.Base64;

@XmlRootElement(name = "CreateStorageServiceInput")
@XmlType(propOrder = { "serviceName", "description", "label",  "location", "affinityGroup" })
public class CreateStorageServiceInput {
	public static String invalidServiceName = "incorrect serviceName value.";
	public static String invalidLabelValue = "incorrect label value.";
	public static String invalidAffinityGroup = "incorrect AffinityGroup value.";
	public static String invalidLocation = "incorrect location value.";
	public static String invalidDescription = "the description can not be longer than 1024 characters.";
	public static String invalidLabelLength = "the label can not be longer than 100 characters.";

	private String serviceName;
	private String label;
	private String description;
	private String location;
	private UUID affinityGroup;


	public CreateStorageServiceInput() {

	}

	/**
	 * @param serviceName
	 *            the serviceName to set Required. A name for the hosted service
	 *            that is unique within Azure. This name is the DNS
	 *            prefix name and can be used to access the hosted service.
	 * @param label
	 *            the label to set Required. A name for the hosted service that
	 *            is base-64 encoded. The name can be up to 100 characters in
	 *            length. The name can be used identify the storage account for
	 *            your tracking purposes.
	 */
	private CreateStorageServiceInput(String serviceName, String label) {
		if (serviceName == null || serviceName.isEmpty())
			throw new InvalidRestAPIArgument(invalidServiceName);

		if (label == null || label.isEmpty())
			throw new InvalidRestAPIArgument(invalidLabelValue);

		this.serviceName = serviceName;

		String labelLength = label;
		try {
			this.label = Base64.encode(label.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
		}
		if (labelLength.length() > 100)
			throw new InvalidRestAPIArgument(
					invalidLabelLength);
	}

	public CreateStorageServiceInput(String serviceName, String label, UUID affinityGroup) {
		this(serviceName, label);

		if (affinityGroup == null)
			throw new InvalidRestAPIArgument(invalidAffinityGroup);

		this.affinityGroup = affinityGroup;
	}

	public CreateStorageServiceInput(String serviceName, String label, String location) {
		this(serviceName, label);

		if (location == null || location.isEmpty())
			throw new InvalidRestAPIArgument(invalidLocation);

		this.location = location;
	}

	/**
	 * @return the serviceName
	 */
	@XmlElement(name = "ServiceName",namespace="")
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * @return the label
	 */
	@XmlElement(name = "Label",namespace="")
	public String getLabel() {
		return label;
	}

	/**
	 * @return the description
	 */
	@XmlElement(name = "Description",namespace="")
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set Optional. A description for the hosted
	 *            service. The description can be up to 1024 characters in
	 *            length.
	 */
	public void setDescription(String description) {
		if (description.length() > 1024)
			throw new InvalidRestAPIArgument(
					invalidDescription);

		this.description = description;
	}

	/**
	 * @return the location
	 */
	@XmlElement(name = "Location",namespace="")
	public String getLocation() {
		return location;
	}

	/**
	 * @return the affinityGroup
	 */
	@XmlElement(name = "AffinityGroup",namespace="")
	public UUID getAffinityGroup() {
		return affinityGroup;
	}

}
