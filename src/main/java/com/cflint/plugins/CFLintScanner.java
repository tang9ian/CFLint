package com.cflint.plugins;

import com.cflint.BugList;

import cfml.parsing.cfscript.CFExpression;
import cfml.parsing.cfscript.script.CFScriptStatement;
import net.htmlparser.jericho.Element;

/**
 * Interface for CFLint Scanners (Plugins).
 * <p>
 * Implementations of this interface define the logic for checking specific rules
 * against the CFML/CFScript code.
 * </p>
 */
public interface CFLintScanner {

    /**
     * Called when a CFExpression is encountered.
     *
     * @param expression The CF expression (e.g., variable, assignment, operation).
     * @param context    The current scanning context.
     * @param bugs       The list to collect found bugs.
     */
    void expression(CFExpression expression, Context context, BugList bugs);

    /**
     * Called when an HTML/CFML Element is encountered.
     *
     * @param element The Jericho Element (tag).
     * @param context The current scanning context.
     * @param bugs    The list to collect found bugs.
     */
    void element(Element element, Context context, BugList bugs);

    /**
     * Called when a CFScriptStatement is encountered.
     *
     * @param expression The script statement (e.g., if, while, function declaration).
     * @param context    The current scanning context.
     * @param bugs       The list to collect found bugs.
     */
    void expression(CFScriptStatement expression, Context context, BugList bugs);

    /**
     * Sets a parameter for the scanner.
     *
     * @param name  The parameter name.
     * @param value The parameter value.
     */
    void setParameter(String name, Object value);

    /**
     * Called at the beginning of scanning a file.
     *
     * @param fileName The name of the file being scanned.
     * @param bugs     The list to collect found bugs.
     */
    void startFile(String fileName, BugList bugs);

    /**
     * Called at the end of scanning a file.
     *
     * @param fileName The name of the file scanned.
     * @param bugs     The list to collect found bugs.
     */
    void endFile(String fileName, BugList bugs);

    /**
     * Called when entering a component (CFC).
     *
     * @param context The current scanning context.
     * @param bugs    The list to collect found bugs.
     */
    void startComponent(Context context, BugList bugs);

    /**
     * Called when exiting a component (CFC).
     *
     * @param context The current scanning context.
     * @param bugs    The list to collect found bugs.
     */
    void endComponent(Context context, BugList bugs);

    /**
     * Called when entering a function.
     *
     * @param context The current scanning context.
     * @param bugs    The list to collect found bugs.
     */
    void startFunction(Context context, BugList bugs);

    /**
     * Called when exiting a function.
     *
     * @param context The current scanning context.
     * @param bugs    The list to collect found bugs.
     */
    void endFunction(Context context, BugList bugs);

}
