/**
 * Copyright (c) Microsoft Corporation
 * 
 * All rights reserved. 
 * 
 * MIT License
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files 
 * (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, 
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, 
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR 
 * ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH 
 * THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.microsoftopentechnologies.azuremanagementutil.rest;

import com.microsoft.windowsazure.management.models.SubscriptionGetResponse;
import com.microsoftopentechnologies.azuremanagementutil.model.Subscription;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class SubscriptionTransformer {
    public static Subscription transform(SubscriptionGetResponse response) {
        Subscription subscription = new Subscription();
        subscription.setSubscriptionID(response.getSubscriptionID());
        subscription.setSubscriptionName(response.getSubscriptionName());
        subscription.setSubscriptionStatus(response.getSubscriptionStatus().name());
        subscription.setAccountAdminLiveEmailId(response.getAccountAdminLiveEmailId());
        subscription.setServiceAdminLiveEmailId(response.getServiceAdminLiveEmailId());
        subscription.setMaxCoreCount(response.getMaximumCoreCount());
        subscription.setMaxStorageAccounts(response.getMaximumStorageAccounts());
        subscription.setMaxHostedServices(response.getMaximumHostedServices());
        subscription.setCurrentCoreCount(response.getCurrentCoreCount());
        subscription.setCurrentHostedServices(response.getCurrentHostedServices());
        subscription.setCurrentStorageAccounts(response.getCurrentStorageAccounts());
        printSubscription(subscription);
        return subscription;
    }

    private static void printSubscription(Subscription subscription) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Subscription.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(subscription, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
