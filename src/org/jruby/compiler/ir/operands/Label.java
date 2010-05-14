package org.jruby.compiler.ir.operands;
import org.jruby.compiler.ir.representations.InlinerInfo;

public class Label extends Operand
{
    public final String _label;

    public static int index = 0;

    public Label(String l) { _label = l; }

    public String toString() { return _label; }

    public int hashCode() { return _label.hashCode(); }

    public boolean equals(Object o) { return (o instanceof Label) && _label.equals(((Label)o)._label); }

    public Operand cloneForInlining(InlinerInfo ii) { return ii.getRenamedLabel(this); }
}
