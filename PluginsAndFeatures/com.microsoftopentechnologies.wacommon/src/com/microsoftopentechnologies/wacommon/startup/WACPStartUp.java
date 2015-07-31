/**
* Copyright Microsoft Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.microsoftopentechnologies.wacommon.startup;

import java.io.File;

import org.eclipse.ui.IStartup;

import com.microsoftopentechnologies.wacommon.Activator;
import com.microsoftopentechnologies.wacommon.utils.FileUtil;
import com.microsoftopentechnologies.wacommon.utils.Messages;
import com.microsoftopentechnologies.wacommon.utils.PluginUtil;

/**
 * This class gets executed after the Workbench initializes.
 */
public class WACPStartUp implements IStartup {

	public void earlyStartup() {

    	//this code is for copying encutil.exe in plugins folder
        copyPluginComponents();
    }

    private void copyPluginComponents() {
        try {
        	String pluginInstLoc = String.format("%s%s%s",
        			PluginUtil.pluginFolder,
        			File.separator, Messages.waCommonFolderID);
        	if (!new File(pluginInstLoc).exists()) {
                new File(pluginInstLoc).mkdir();
            }
            String enctFile = String.format("%s%s%s", pluginInstLoc,
            		File.separator, Messages.encFileName);

            // Check for encutil.exe
            if (new File(enctFile).exists()) {
            	new File(enctFile).delete();
            }
            FileUtil.copyResourceFile(Messages.encFileEntry,enctFile);
        } catch (Exception e) {
            Activator.getDefault().log(e.getMessage(), e);
        }
		
	}
}
