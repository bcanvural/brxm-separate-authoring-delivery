package org.example.beans;
/*
 * Copyright 2014-2019 Hippo B.V. (http://www.onehippo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.hippoecm.hst.content.beans.ContentNodeBinder;
import org.hippoecm.hst.content.beans.ContentNodeBindingException;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;

import javax.jcr.RepositoryException;

@HippoEssentialsGenerated(internalName = "myproject:bannerdocument")
@Node(jcrType = "myproject:bannerdocument")
public class Banner extends BaseDocument implements ContentNodeBinder {

    private String title;

    @HippoEssentialsGenerated(internalName = "myproject:title")
    public String getTitle() {
        if (title == null) {
            title = getProperty("myproject:title");
        }
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    @HippoEssentialsGenerated(internalName = "myproject:content")
    public HippoHtml getContent() {
        return getHippoHtml("myproject:content");
    }

    @HippoEssentialsGenerated(internalName = "myproject:image")
    public HippoGalleryImageSet getImage() {
        return getLinkedBean("myproject:image", HippoGalleryImageSet.class);
    }

    @HippoEssentialsGenerated(internalName = "myproject:link")
    public HippoBean getLink() {
        return getLinkedBean("myproject:link", HippoBean.class);
    }

    public boolean bind(Object content, javax.jcr.Node node) throws ContentNodeBindingException {
        try {
            Banner banner = (Banner) content;
            node.setProperty("myproject:title", banner.getTitle());
            return true;
        } catch (RepositoryException e) {
            throw new ContentNodeBindingException(e);
        }
    }

}
