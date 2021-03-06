/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jruby.ast;

import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;

/**
 *
 * @author enebo
 */
public class ComplexNode extends Node {
    private Node y;

    public ComplexNode(ISourcePosition position, Node y) {
        super(position);

        this.y = y;
    }
    
    @Override
    public Object accept(NodeVisitor visitor) {
       return visitor.visitComplexNode(this);
    }

    @Override
    public List<Node> childNodes() {
        return EMPTY_LIST;
    }

    @Override
    public NodeType getNodeType() {
        return NodeType.COMPLEXNODE;
    }

    public Node getNumber() {
        return y;
    }
}
