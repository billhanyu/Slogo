package model.executable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exception.SyntacticErrorException;
import model.LogHolder;
import util.Utils;
import model.Executable;

public class ProcedureImpl{
	
	private List<Variable> params;
	private CodeBlock procedure;
	
	public ProcedureImpl init(CodeBlock params, CodeBlock procedure)
			throws SyntacticErrorException {
		validateInitParamList(params);
		this.procedure = procedure;
		return this;
	}

	public double execute(LogHolder log, List<Executable> argument) 
			throws SyntacticErrorException {
		// $sp down
		List<Executable> calleeSaved = new ArrayList<>();
		for (Variable param : params)
			calleeSaved.add(param.getExpression());
		// overwrite args
		for (int i = 0; i < argument.size(); i++)
			params.get(i).setExpression(argument.get(i));
		// execute
		haveVarRefsPointToRightObjs(log);
		double ret = procedure.execute(log);
		// $sp up
		for (int i = 0; i < calleeSaved.size(); i++)
			params.get(i).setExpression(calleeSaved.get(i));
		return ret;
	}
	
	public List<Variable> getParams() {
		return params;
	}
	
	private void validateInitParamList(CodeBlock params)
			throws SyntacticErrorException {
		this.params = new ArrayList<>();
		for (Executable e : params.getLocalVarRefs()) {
			if ( !(e instanceof Variable) )
				throw new SyntacticErrorException();
			this.params.add( (Variable)e );
		}
		Collections.reverse(this.params);
	}
	
	/**
	 * Only resolve local parameters passed in by caller.
	 * Global Variables are resolved when codeblock is executed
	 * @throws SyntacticErrorException 
	 */
	private void haveVarRefsPointToRightObjs(LogHolder log)
			throws SyntacticErrorException {
		for (Variable var : procedure.getLocalVarRefs()) {
			Variable arg;
			if ( (arg = Utils.listContains(params, var.getName())) != null) {
				var.setExpression(getFinalValue(arg.getExpression(), log));
			} else if ( var.getExpression() == null
					&& (arg = Utils.listContains(procedure.getGlobalVarRefs(), var.getName())) != null) {
				var.setExpression(getFinalValue(arg.getExpression(), log));
			}
		}
	}
	
	private Constant getFinalValue(Executable expr, LogHolder log)
			throws SyntacticErrorException {
		if (expr instanceof Constant)
			return (Constant) expr;
		return new Constant(null, expr.execute(log));
	}
}
