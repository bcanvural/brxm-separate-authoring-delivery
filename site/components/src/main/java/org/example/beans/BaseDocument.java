package org.example.beans;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "basedocument")
@XmlAccessorType(XmlAccessType.NONE)
@Node(jcrType = "myproject:basedocument")
public class BaseDocument extends HippoDocument {
}
