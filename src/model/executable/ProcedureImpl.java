package model.executable;

import java.util.ArrayList;
import java.util.List;

import exception.SyntacticErrorException;
import model.TurtleLog;
import util.Utils;
import model.Executable;
import model.GlobalVariables;

public class ProcedureImpl{
	
	private String name;
	// TODO (cx15): when called, push a new stackFrame onto stack, populate it using variables from previous stackframe, subsequent command access value from the new stackfrome 
	private List<Variable> params;
	private CodeBlock procedure;
	
	public void init(String name,
			 CodeBlock params,
			 CodeBlock procedure,
			 GlobalVariables globalVars) throws SyntacticErrorException {
		this.name = name;
		validateInitParamList(params);
		this.procedure = procedure;
		haveVarRefsPointToRightObjs(globalVars);
	}

	public double execute(TurtleLog log, List<Executable> argument) 
			throws SyntacticErrorException {
		// $sp down
		List<Executable> calleeSaved = new ArrayList<>();
		for (Variable param : params)
			calleeSaved.add(param.getExpression());
		// overwrite args
		for (int i = 0; i < argument.size(); i++)
			params.get(i).setExpression(argument.get(i));
		double ret = procedure.execute(log);
		// $sp up
		for (int i = 0; i < calleeSaved.size(); i++)
			params.get(i).setExpression(calleeSaved.get(i));
		return ret;
	}

	public String getName() {
		return this.name;
	}
	
	public List<Variable> getParams() {
		return params;
	}
	
	private void validateInitParamList(CodeBlock params)
			throws SyntacticErrorException {
		this.params = new ArrayList<>();
		for (Executable e : params.unravel()) {
			if ( !(e instanceof Variable) )
				throw new SyntacticErrorException();
			this.params.add( (Variable)e );
		}
	}
	
	private void haveVarRefsPointToRightObjs(GlobalVariables globalVars) {
		for (Variable var : procedure.getVarRefs()) {
			Variable arg = Utils.listContains(params, var.getName());
			var.setExpression(
					(arg != null)? arg : globalVars.get(var.getName())
			);
		}
	}
}
