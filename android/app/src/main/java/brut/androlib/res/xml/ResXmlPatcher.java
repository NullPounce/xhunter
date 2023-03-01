/*
 *  Copyright (C) 2010 Ryszard Wiśniewski <brut.alll@gmail.com>
 *  Copyright (C) 2010 Connor Tumbleson <connor.tumbleson@gmail.com>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package brut.androlib.res.xml;

import brut.androlib.AndrolibException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;

public final class ResXmlPatcher {

    /**
     * Removes "debug" tag from file
     *
     * @param file AndroidManifest file
     * @throws AndrolibException Error reading Manifest file
     */
    public static void removeApplicationDebugTag(File file) throws AndrolibException {
        if (file.exists()) {
            try {
                Document doc = loadDocument(file);
                Node application = doc.getElementsByTagName("application").item(0);

                // load attr
                NamedNodeMap attr = application.getAttributes();
                Node debugAttr = attr.getNamedItem("android:debuggable");

                // remove application:debuggable
                if (debugAttr != null) {
                    attr.removeNamedItem("android:debuggable");
                }

                saveDocument(file, doc);

            } catch (SAXException | ParserConfigurationException | IOException | TransformerException ignored) {
            }
        }
    }
