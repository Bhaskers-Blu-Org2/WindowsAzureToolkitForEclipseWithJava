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

package com.microsoftopentechnologies.azurecommons.deploy.model;

import java.io.Serializable;
import java.util.Date;

public class RemoteDesktopDescriptor implements Serializable {

	private static final long serialVersionUID = -5473860018650709333L;
	private final String userName;
	private final String password;
	private final Date expirationDate;
	private final String publicKey;
	private final boolean startRemoteRDP;
	private final boolean enabled;
	
	public RemoteDesktopDescriptor(String userName, String password,
			Date expirationDate, String publicKey,
			boolean startRemoteRDP, boolean enabled) {
		this.userName = userName;
		this.password = password;
		this.expirationDate = expirationDate;
		this.publicKey = publicKey;
		this.startRemoteRDP = startRemoteRDP;
		this.enabled = enabled;
	}
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the expirationDate
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}

	/**
	 * @return the publicKey
	 */
	public String getPublicKey() {
		return publicKey;
	}

	public boolean isStartRemoteRDP() {
		return startRemoteRDP;
	}

	public boolean isEnabled() {
		return enabled;
	}
}
