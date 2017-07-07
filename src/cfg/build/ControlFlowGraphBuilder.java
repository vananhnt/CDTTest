package cfg.build;

import org.eclipse.cdt.core.dom.ast.IASTCompoundStatement;
import org.eclipse.cdt.core.dom.ast.IASTDoStatement;
import org.eclipse.cdt.core.dom.ast.IASTForStatement;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTIfStatement;
import org.eclipse.cdt.core.dom.ast.IASTReturnStatement;
import org.eclipse.cdt.core.dom.ast.IASTStatement;
import org.eclipse.cdt.core.dom.ast.IASTWhileStatement;

import cfg.nodes.EndNode;
import cfg.nodes.PlainNode;

public class ControlFlowGraphBuilder {
	
	/*
	 * Builds the graph.
	 */
	
	public ControlFlowGraph build (IASTFunctionDefinition def) {
		return createSubGraph(def.getBody());
	}
	private ControlFlowGraph createSubGraph(IASTStatement body) {
		if (body instanceof IASTCompoundStatement) {
			IASTCompoundStatement comp = (IASTCompoundStatement) body;
			for (IASTStatement statement : comp.getStatements()) {
				createSubGraph(statement);
			}
		} else if (body instanceof IASTIfStatement) {
			return createIf((IASTIfStatement) body);
		} else if (body instanceof IASTForStatement) {
			return createFor((IASTForStatement) body);
		} else if (body instanceof IASTDoStatement) {
			return creatDo((IASTDoStatement) body);
		} else if (body instanceof IASTWhileStatement) {
			return createWhile((IASTWhileStatement) body);
		} else if (body instanceof IASTReturnStatement) {
			System.out.println("return");		
		} else {
			PlainNode plainNode = new PlainNode(body);
		    System.out.println(plainNode.getStatement()); 
			return new ControlFlowGraph(plainNode, plainNode);
		}
		return null;
	}

	private ControlFlowGraph createWhile(IASTWhileStatement body) {
		// TODO Auto-generated method stub
		return null;
	}

	private ControlFlowGraph creatDo(IASTDoStatement body) {
		// TODO Auto-generated method stub
		return null;
	}

	private ControlFlowGraph createIf(IASTIfStatement body) {
		System.out.println("IfStatement");
		return null;
	}

	private ControlFlowGraph createFor(IASTForStatement body) {
		System.out.println("ForStatement");
		return null;
	}
	
	/*/
	 * main: print the graph
	 */
	public static void  main(String[] args) {
		IASTFunctionDefinition func = (new ASTGenerator("./test.c")).getFunction(0);
		ControlFlowGraph cfg = (new ControlFlowGraphBuilder()).build(func);
		String cfg_status = (cfg == null) ? "null" : "not null";
		System.out.println(cfg_status);

	}
}