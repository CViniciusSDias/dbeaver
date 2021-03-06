/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2016 Serge Rieder (serge@jkiss.org)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (version 2)
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.jkiss.dbeaver.lang.parser;

import org.eclipse.jface.text.rules.IToken;
import org.jkiss.dbeaver.lang.SCMNode;
import org.jkiss.dbeaver.lang.SCMNodeParser;
import org.jkiss.dbeaver.lang.SCMRoot;
import org.jkiss.dbeaver.lang.SCMSourceScanner;
import org.jkiss.dbeaver.lang.base.SCMEUndefined;

/**
 * DocumentParser.
 */
public class DocumentParser {

    private final SCMNodeParser nodeParser;

    public DocumentParser(SCMNodeParser nodeParser) {
        this.nodeParser = nodeParser;
    }

    public SCMNode parseTree(SCMSourceScanner scanner) {
        SCMRoot documentNode = new SCMRoot(scanner);

        for (; ; ) {
            IToken token = scanner.nextToken();
            if (token.isEOF()) {
                break;
            }

            SCMNode node = nodeParser.parseNode(documentNode, token, scanner);
            documentNode.addChild(node);
        }

        return documentNode;
    }

}
