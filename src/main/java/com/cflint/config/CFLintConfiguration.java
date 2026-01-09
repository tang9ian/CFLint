package com.cflint.config;

import java.util.Collection;

import com.cflint.config.CFLintPluginInfo.PluginInfoRule;
import com.cflint.config.CFLintPluginInfo.PluginInfoRule.PluginMessage;
import com.cflint.plugins.CFLintScanner;

/**
 * Interface for CFLint configuration.
 * <p>
 * Defines the contract for accessing configuration settings, including
 * rules, includes/excludes, and custom parameters for plugins.
 * </p>
 */
public interface CFLintConfiguration {

    /**
     * Checks if a specific message should be included in the results.
     *
     * @param pluginMessage The message to check.
     * @return true if included, false otherwise.
     */
    boolean includes(PluginMessage pluginMessage);

    /**
     * Checks if a specific message should be excluded from the results.
     *
     * @param pluginMessage The message to check.
     * @return true if excluded, false otherwise.
     */
    boolean excludes(PluginMessage pluginMessage);

    /**
     * Retrieves the rule associated with a specific scanner class.
     *
     * @param clazz The class of the scanner.
     * @return The corresponding rule, or null if not found.
     */
    PluginInfoRule getRuleByClass(Class<?> clazz);

    /**
     * Retrieves the rule associated with a specific plugin instance.
     *
     * @param plugin The plugin instance.
     * @return The corresponding rule.
     */
    PluginInfoRule getRuleForPlugin(CFLintScanner plugin);

    /**
     * Adds a message to the inclusion list.
     *
     * @param pluginMessage The message to include.
     */
    void addInclude(PluginMessage pluginMessage);

    /**
     * Adds a message to the exclusion list.
     *
     * @param pluginMessage The message to exclude.
     */
    void addExclude(PluginMessage pluginMessage);

    /**
     * Gets all configured rules.
     *
     * @return A collection of rules.
     */
    Collection<CFLintPluginInfo.PluginInfoRule> getRules();

    /**
     * get the property from the configuration. This can be overriden with
     * -DcheckerClass.propertyname=value
     *
     * @param linter The scanner instance asking for the parameter.
     * @param name   the name of the parameter
     * @return the value of the parameter
     */
    public String getParameter(CFLintScanner linter, final String name);

    public String getParameterNotNull(CFLintScanner linter, final String name);

    public <E> E getParameter(CFLintScanner linter, final String name, final Class<E> clazz);

    public Object getParameter(final String name);
}
