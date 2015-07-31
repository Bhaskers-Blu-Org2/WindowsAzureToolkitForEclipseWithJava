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
package com.microsoftopentechnologies.azurecommons.deploy.tasks;

import java.util.concurrent.Callable;

import javax.swing.event.EventListenerList;

import com.microsoftopentechnologies.azurecommons.deploy.util.PublishData;
import com.microsoftopentechnologies.azuremanagementutil.exception.InvalidThumbprintException;
import com.microsoftopentechnologies.azuremanagementutil.rest.WindowsAzureServiceManagement;


public abstract class LoadingTask<T> implements Callable<T>, Runnable {
	
	protected PublishData data;
	private T dataResult;
	
	protected EventListenerList listeners = new EventListenerList();
	
	public void addLoadingAccountListener(LoadingAccoutListener listener) {
		listeners.add(LoadingAccoutListener.class, listener);
	}
	
	public void removeLoadingAccountListener(LoadingAccoutListener listener) {
		listeners.remove(LoadingAccoutListener.class, listener);
	}

	public LoadingTask(PublishData data) {
		this.data = data;
	}

	public T getDataResult() {
		return dataResult;
	}

	protected abstract void setDataResult(T data);

	@Override
	public abstract T call() throws Exception;

	private void doTask() throws Exception {
		dataResult = call();
		synchronized (data) {
			setDataResult(dataResult);
		}

	}

	@Override
	public void run() {

		try {
			doTask();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected synchronized WindowsAzureServiceManagement getServiceInstance() {
		WindowsAzureServiceManagement instance = null;
		try {
			instance = new WindowsAzureServiceManagement();
		} catch (InvalidThumbprintException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	protected void fireRestAPIErrorEvent(AccountCachingExceptionEvent e) {
		Object[] list = listeners.getListenerList();
		for (int i = 0; i < list.length; i += 2) {
			if (list[i] == LoadingAccoutListener.class) {
				((LoadingAccoutListener) list[i + 1]).onRestAPIError(e);
			}
		}	
	}
}
