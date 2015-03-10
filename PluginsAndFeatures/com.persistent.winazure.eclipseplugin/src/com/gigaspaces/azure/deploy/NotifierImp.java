/**
* Copyright 2015 Microsoft Open Technologies, Inc.
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
package com.gigaspaces.azure.deploy;

import java.util.concurrent.atomic.AtomicInteger;

import com.microsoftopentechnologies.azurecommons.deploy.UploadProgressEventArgs;
import com.microsoftopentechnologies.azuremanagementutil.model.Notifier;

import waeclipseplugin.Activator;

class NotifierImp implements Notifier {
	
	private final AtomicInteger percent = new AtomicInteger(0);

	@Override
	public void notifyProgress(int step) {
		UploadProgressEventArgs event = new UploadProgressEventArgs(this);
		int currentPercentage = percent.get();
		percent.set(currentPercentage + step);
		event.setPercentage(percent.get());
		Activator.getDefault().fireUploadProgressEvent(event);
	}

	public int getPercent() {
		return percent.get();
	}
}