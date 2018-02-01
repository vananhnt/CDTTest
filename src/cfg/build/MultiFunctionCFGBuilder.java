package cfg.build;

import java.util.ArrayList;

import org.eclipse.cdt.core.dom.ast.IASTBinaryExpression;
import org.eclipse.cdt.core.dom.ast.IASTDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTDeclarationStatement;
import org.eclipse.cdt.core.dom.ast.IASTExpression;
import org.eclipse.cdt.core.dom.ast.IASTExpressionStatement;
import org.eclipse.cdt.core.dom.ast.IASTFunctionCallExpression;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTIdExpression;
import org.eclipse.cdt.core.dom.ast.IASTInitializerClause;
import org.eclipse.cdt.core.dom.ast.IASTName;
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPNodeFactory;

import cfg.build.index.IASTVariable;
import cfg.node.BeginNode;
import cfg.node.CFGNode;
import cfg.node.DecisionNode;
import cfg.node.EndConditionNode;
import cfg.node.EndFunctionNode;
import cfg.node.EndNode;
import cfg.node.FunctionCallNode;
import cfg.node.PlainNode;
import cfg.node.UndefinedNode;
import cfg.utils.ExpressionModifier;
import cfg.utils.FunctionHelper;


/**
 * Noi cac ham trong file, them cac lenh global
 * @author va
 * chua xet th goi ham trog condition
 * (new MultiFunctionCFGBuilder(ast)).build(func);
 */

public class MultiFunctionCFGBuilder {
	private ASTFactory ast;

	public MultiFunctionCFGBuilder() {}
	
	public MultiFunctionCFGBuilder(ASTFactory ast) {
		this.ast = ast;

	}

	//Them cac cau lenh khoi tao toan cuc
	public ControlFlowGraph build(IASTFunctionDefinition func) {
		if (func == null) {
			return null;
		}
		ControlFlowGraph prvCfg = new ControlFlowGraph(); 
		CPPNodeFactory factory = new CPPNodeFactory();
		ASTFactory ast = new ASTFactory(func.getTranslationUnit());
		ArrayList<IASTDeclaration> declarations = ast.getGlobarVarList();
		ControlFlowGraph subCfg;
		for (IASTDeclaration declaration : declarations) {
			IASTDeclarationStatement statement = factory.newDeclarationStatement(declaration.copy());
			subCfg = (new ControlFlowGraphBuilder()).createSubGraph(statement);
			if (prvCfg == null) {
				prvCfg = subCfg;
			}else {
				prvCfg.concat(subCfg);	
			}
		}
		ControlFlowGraph mainFuncCfg = getVtseCFG(func);
		prvCfg.concat(mainFuncCfg);
	
		CFGNode newStart = iterateNode(prvCfg.getStart(), prvCfg.exit, func);
		EndFunctionNode endFunction = new EndFunctionNode(func);
		prvCfg.concat(new ControlFlowGraph(endFunction, endFunction));
		
		return prvCfg;	
	}
	
	private ArrayList<ControlFlowGraph> createList() {
		ArrayList<ControlFlowGraph> list = new ArrayList<>();
		ControlFlowGraph cfg;
		for (IASTFunctionDefinition func: ast.getListFunction()) {
			cfg = new ControlFlowGraph(func);
		
			list.add(cfg);
		}
		return list;
	}
	
	private ControlFlowGraph getVtseCFG(IASTFunctionDefinition func) {
		for (ControlFlowGraph cfg : createList()) {
			if (cfg.getNameFunction().equals(func.getDeclarator().getName().toString())) {
				return cfg;
			}
		}
		return null;
	} 
	
	/**
	 * @param node, end, func
	 * Ham duyet cfg va xu ly FunctionCallNode
	 */
	private CFGNode iterateNode(CFGNode node, CFGNode end, IASTFunctionDefinition func) {
		if (node == null) {
			node = null;
		}  else if (node instanceof DecisionNode) {
			((DecisionNode) node).setThenNode(iterateNode(((DecisionNode) node).getThenNode(), end, func));
			((DecisionNode) node).setElseNode(iterateNode(((DecisionNode) node).getElseNode(), end, func));		
		} else if (node instanceof EndConditionNode) {	
			
		} else if (node instanceof BeginNode)  {
			node.setNext(iterateNode(node.getNext(), end, func));
			((BeginNode) node).getEndNode().setNext(iterateNode(((BeginNode) node).getEndNode().getNext(), end, func));
		} else if (node instanceof FunctionCallNode){
			ControlFlowGraph functionGraph = createFuncGraph(((FunctionCallNode) node).getFunctionCall(), func);
			
			if (functionGraph != null) {
				CFGNode pause = node.getNext();
				//todo
				node = iterateNode(functionGraph.getStart(), end, func);
				functionGraph.getExit().setNext(iterateNode(pause, end, func));
			}	
		} else if (node instanceof EndNode) {
			
		} else {
			node.setNext(iterateNode(node.getNext(), end, func));	
		}		
		return node;
	}
	
	private boolean isVoid(IASTFunctionCallExpression callExpression) {
		String funcName = callExpression.getFunctionNameExpression().toString();
		IASTFunctionDefinition func = FunctionHelper.getFunction(ast.getListFunction(), funcName);
		String type = FunctionHelper.getFunctionType(func);
		return type.equals("void") ? true : false;
	}

	private ControlFlowGraph createFuncGraph(IASTFunctionCallExpression callExpression, IASTFunctionDefinition currentFunc) {
		
		ControlFlowGraph cfg = new ControlFlowGraph();
		String funcName = callExpression.getFunctionNameExpression().toString();
		//System.out.println(funcName);
		IASTFunctionDefinition func = FunctionHelper.getFunction(ast.getListFunction(), funcName);
		
		if (func == null) {
			System.err.println("Not found function " + funcName);
			UndefinedNode undef = new UndefinedNode();
			cfg = new ControlFlowGraph(undef, undef);
			return cfg;
		}
		CPPNodeFactory factory = (CPPNodeFactory) func.getTranslationUnit().getASTNodeFactory();
		//Cho tham so = params	
			cfg.concat(createArguments(callExpression, currentFunc));
		
		//Noi voi than cua ham duoc goi
			cfg.concat(new ControlFlowGraph(func));
		
		//Tao ra node: ham duoc goi = return neu khong phai void
		if (!isVoid(callExpression)) {
			IASTIdExpression left = (IASTIdExpression) ExpressionModifier.changeFunctionCallExpression(callExpression, func);
			IASTName nameRight = factory.newName(("return_" + funcName).toCharArray());
			IASTIdExpression right = factory.newIdExpression(nameRight);
			IASTBinaryExpression binaryExp = factory.newBinaryExpression(IASTBinaryExpression.op_assign, left, right);
			IASTExpressionStatement statement = factory.newExpressionStatement(binaryExp);
			
			CFGNode plainNode = new PlainNode(statement); //tao ra plainNode khong co ten ham dang sau
			cfg.concat(new ControlFlowGraph(plainNode, plainNode));
		}
		
		EndFunctionNode endFunction = new EndFunctionNode(func);
		cfg.concat(new ControlFlowGraph(endFunction, endFunction));
		//System.out.println("concated function");
		return cfg;
	}
	/**
	 * @param callExpression
	 * @param currentFunc
	 * Tra ve cac Node xu ly tham so cua ham (neu co)
	 */
	private ControlFlowGraph createArguments(IASTFunctionCallExpression callExpression, IASTFunctionDefinition currentFunc) {
		ControlFlowGraph cfg = new ControlFlowGraph();
		String funcName = callExpression.getFunctionNameExpression().toString();
		CFGNode plainNode;
		IASTFunctionDefinition func = FunctionHelper.getFunction(ast.getListFunction(), funcName);
		
		ArrayList<IASTVariable> params = FunctionHelper.getParameters(func);
		IASTInitializerClause[] arguments = callExpression.getArguments();
		IASTBinaryExpression expression;
		IASTExpressionStatement statement;
		IASTExpression right;
		IASTName leftName;
		IASTIdExpression left;
		String leftNameStr;
		String offset = "";
//		CFGNode declNode;
//		IASTDeclarationStatement declStatement;
//		IASTDeclarator declarator;
//		IASTSimpleDeclaration declaration;

		CPPNodeFactory factory = (CPPNodeFactory) func.getTranslationUnit().getASTNodeFactory();
		
		for (int i = 0; i < arguments.length ; i++) {
			leftNameStr = params.get(i).getName().toString();			
			leftNameStr += "_" + funcName;
			offset = "";
//			for (IASTNode node : arguments) {
//				offset += "_" +  node.toString();
//			}
			leftNameStr += offset;
			leftName = factory.newName(leftNameStr.toCharArray());
			left = factory.newIdExpression(leftName);
//			IASTDeclSpecifier type = params.get(i).getType().copy();
//			declarator = factory.newDeclarator(leftName);
//			declaration = factory.newSimpleDeclaration(type);
//			declaration.addDeclarator(declarator);
//			declStatement = factory.newDeclarationStatement(declaration);
//			declNode = new PlainNode(declStatement, func);
//			cfg.concat(new ControlFlowGraph(declNode, declNode));
			
//			IASTName rightName = factory.newName((arguments[i].getRawSignature().toCharArray()));
//			IASTIdExpression right = factory.newIdExpression(rightName);
			
			right = (IASTExpression) ExpressionModifier.changeVariableName((IASTExpression) arguments[i].copy(), currentFunc);
			expression = factory.newBinaryExpression(IASTBinaryExpression.op_assign, left, right);
			statement = factory.newExpressionStatement(expression);
			plainNode = new PlainNode(statement);
			cfg.concat(new ControlFlowGraph(plainNode, plainNode));
		}
		return cfg;
	}
	
	
	}
