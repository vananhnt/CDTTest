package cfg.nodes;

import org.eclipse.cdt.core.dom.ast.ASTNodeProperty;
import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.ExpansionOverlapsBoundaryException;
import org.eclipse.cdt.core.dom.ast.IASTFileLocation;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTNodeLocation;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.ast.IASTNode.CopyStyle;
import org.eclipse.cdt.core.parser.IToken;
// Node is ansestor of all Node in cfg
public class CFGNode {	
	private CFGNode prev;
	private CFGNode next;
	private IASTNode data;
	
	public CFGNode(){
		this.prev = null;
		this.next = null;
	}
	
	public CFGNode( CFGNode prev, CFGNode next){
		this.prev = prev;
		this.next = next;		
	}
	
	public CFGNode(CFGNode node){
		this.prev = node.getPrev();
		this.next = node.getNext();
		this.data = node.getData();
	}

// get set
	public CFGNode getNext() {
		return next;
	}

	public void setNext(CFGNode next) {
		this.next = next;
	}

	public CFGNode getPrev() {
		return prev;
	}

	public void setPrev(CFGNode prev) {
		this.prev = prev;
	}

	public IASTNode getData() {
		return data;
	}

	public void setData(IASTNode data) {
		this.data = data;
	}
	
	
	
}
